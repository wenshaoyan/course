<template>
  <div class="app-container calendar-list-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleSearch" style="width: 200px;" class="filter-item" placeholder="id" v-model="versionQuery.id" @change="queryParamChange('id')">
      </el-input>

      <el-input @keyup.enter.native="handleSearch" style="width: 200px;" class="filter-item" placeholder="名称" v-model="versionQuery.version_name" @change="queryParamChange('name')">
      </el-input>

      <el-select clearable class="filter-item" style="width: 130px" v-model="versionQuery.client_id" placeholder="客户端列表" @clear="clientClear" :loading="listClientLoading" loading-text="加载中..">
        <el-option v-for="item in clientList" :key="item.id" :label="item.name" :value="item.id">
        </el-option>
      </el-select>

      <el-select @change='handleSearch' style="width: 120px" class="filter-item" v-model="filterQuery.sort" placeholder="排序">
        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key">
        </el-option>
      </el-select>

      <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleSearch">搜索</el-button>
      <el-button class="filter-item" type="primary" v-waves icon="edit" @click="handleCreate">添加</el-button>
    </div>

    <el-table  :data="list" v-loading="listLoading" element-loading-text="给我一点时间" border fit highlight-current-row >

      <el-table-column align="center" label="id" width="80">
        <template scope="scope">
          <span>{{scope.row.id}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建时间" width="150">
        <template scope="scope">
          <span>{{scope.row.create_time | parseTime('{y}-{m}-{d} {h}:{i}')}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="更新时间" width="150">
        <template scope="scope">
          <span>{{scope.row.update_time | parseTime('{y}-{m}-{d} {h}:{i}')}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="跳转" width="100">
        <template scope="scope">
          <span>{{scope.row.redirect_url}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="客户端名称" width="150">
        <template scope="scope">
          <span>{{getClientName(scope.row.show_client_id)}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="图片" >
        <template scope="scope">
          <img :src="scope.row.image_url" height="200" width="400"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="80">
        <template scope="scope">
          <el-button  size="small" type="success" @click="handleEditRow(scope.row)">编辑
          </el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" label="拖拽" width="95">
        <template scope="scope">
          <icon-svg class='drag-handler' icon-class="drag"></icon-svg>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="dialogRowData" :rules="versionRule" ref="dialogRowData" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="id" v-show="dialogStatus === 'update'">
          <span>{{dialogRowData.id}}</span>
        </el-form-item>
        <el-form-item label="版本名称">
          <el-input v-model="dialogRowData.version_name"></el-input>
        </el-form-item>
        <el-form-item label="版本号">
          <el-input v-model.number="dialogRowData.version_number" type="number"></el-input>
        </el-form-item>
        <el-form-item label="安装文件">
          <!--<el-input v-model="dialogRowData.download_url"></el-input>-->
          <el-upload
            action="http://123.207.55.204:8083/upload/apk"
            name="file"
            :show-file-list="false"
            :on-success="fileUploadSuccess"
            :on-error="fileUploadError"
            :on-progress="fileUploadProgress"
            :before-upload="beforeAvatarUpload">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">{{this.uploadInfo}}</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="更新描述">
          <el-input v-model="dialogRowData.description"></el-input>
        </el-form-item>
        <el-form-item label="客户端">
          <span>{{getClientName(dialogRowData.client_id)}}</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='create' && !dialogRowData.id" type="primary" @click="insertData">确 定</el-button>
        <el-button v-else type="primary" @click="updateData">确 定</el-button>
      </div>
    </el-dialog>

    <div v-show="!listLoading" class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="bindPage"
                     :page-sizes="[10,20,30, 50]" :page-size="filterQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>


  </div>
</template>

<script>
  import { clientQueryPattern, versionUpdate, versionInsert } from '@/api/client'
  import { bannerQuery } from '@/api/banner'
  import waves from '@/directive/waves/index.js' // 水波纹指令

  export default {
    name: 'table_demo',
    directives: {
      waves
    },
    data() {
      const validateVersionName = (rule, value, callback) => {
        if (value.length === 0) {
          callback(new Error('名称必填'))
        } else {
          callback()
        }
      }
      const validateVersionNumber = (rule, value, callback) => {
        if (!Number.isInteger(value)) {
          callback(new Error('输入整数'))
        } else {
          callback()
        }
      }
      return {
        list: null,
        total: null,
        listLoading: true,
        listClientLoading: true,
        filterQuery: {
          page: 1,
          limit: 10,
          sort_by: 'version_number',
          sort: '-version_number',
          action: 'search',
          order: 'desc'
        },
        versionQuery: {
          client_id: undefined,
          id: undefined,
          version_name: undefined
        },
        bindPage: 1,
        clientList: [],
        clientMap: null,
        dialogRowData: {
          id: undefined,
          version_name: undefined,
          version_number: undefined,
          download_url: undefined,
          description: undefined,
          client_id: undefined
        },
        textMap: {
          update: '编辑',
          create: '创建'
        },
        dialogStatus: '',
        dialogFormVisible: false,
        versionRule: {
          version_name: [{ validator: validateVersionName }],
          version_number: [{ validator: validateVersionNumber }]
        },
        sortOptions: [
          { label: '按创建时间升序', key: '+create_time' },
          { label: '按创建时间降序', key: '-create_time' },
          { label: '按版本号升序', key: '+version_number' },
          { label: '按版本号降序', key: '-version_number' }],
        // pending: 上传中 success:成功 error:失败 await:等待上传
        updateApkStatus: 'await',
        uploadInfo: '上传apk'
      }
    },
    watch: {
      'filterQuery.sort': 'watchSort',
      'versionQuery.client_id': 'clientIdChange'
    },
    created() {
      // 初始化数据
      this.initData()
      // 获取客户端下拉列表
      this.getClientAll()
      // 获取版本数据列表
      this.getList()
    },
    methods: {
      initData() {
        const temp = this.$route.query.client_id
        this.versionQuery.client_id = temp ? +temp : temp
      },
      getClientName(id) {
        return this.clientMap ? this.clientMap.get(id) : ''
      },
      queryParamChange(keyName) {
        if (keyName in this.versionQuery && this.versionQuery[keyName] === '') {
          this.versionQuery[keyName] = undefined
        }
      },
      watchSort() {
        const sort = this.filterQuery.sort
        if (sort[0] === '+') {
          this.filterQuery.order = 'asc'
          this.filterQuery.sort_by = sort.slice(1, sort.length)
        } else if (sort[0] === '-') {
          this.filterQuery.order = 'desc'
          this.filterQuery.sort_by = sort.slice(1, sort.length)
        }
      },
      clientIdChange() {
        // 修改没有生效
        this.$route.query.client_id = this.versionQuery.client_id
        // alert(this.$route.query.client_id)
      },
      getList() {
        this.listLoading = true
        bannerQuery().then(data => {
          this.list = data
          if ('count' in data) this.total = data.count
          this.listLoading = false
        }).catch(e => {
          this.listLoading = false
        })
      },
      getClientAll() {
        clientQueryPattern(1).then(data => {
          this.clientList = data.list
          const map = new Map()
          data.list.forEach(value => map.set(value.id, value.name))
          this.clientMap = map
          this.listClientLoading = false
        })
      },
      handleEditRow(row) {
        if (row && row.client_id) {
          this.resetDialogRow(row.client_id)
          this.dialogRowData = Object.assign({}, row)
          this.dialogStatus = 'update'
          this.uploadInfo = row.download_url
          this.dialogFormVisible = true
        } else {
          this.$message({ message: '获取客户端id失败', type: 'error' })
        }
      },
      handleCreate() {
        if (this.versionQuery.client_id) {
          this.resetDialogRow(this.versionQuery.client_id)
          this.dialogRowData.client_id = this.versionQuery.client_id
          this.dialogStatus = 'create'
          this.dialogFormVisible = true
          this.uploadInfo = '上传apk'
        } else {
          this.$message({ message: '请选择客户端后再添加', type: 'error' })
        }
      },
      resetDialogRow() {
        this.dialogRowData = {
          id: undefined,
          version_name: undefined,
          version_number: undefined,
          download_url: undefined,
          description: undefined,
          client_id: undefined
        }
      },
      handleSearch() {
        this.filterQuery.page = 1
        this.filterQuery.action = 'search'
        this.getList()
      },
      handleSizeChange(val) {
        this.filterQuery.action = 'search'
        this.filterQuery.limit = val
        this.filterQuery.page = 1
        this.getList()
      },
      handleCurrentChange(val) {
        this.filterQuery.action = 'paging'
        this.filterQuery.page = val
        this.bindPage = val
        this.getList()
      },
      // 插入数据
      insertData() {
        this.$refs.dialogRowData.validate(valid => {
          console.log(this.dialogRowData)
          if (valid) {
            versionInsert(this.dialogRowData).then(data => {
              this.getList()
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '添加成功',
                type: 'success',
                duration: 2000
              })
            }).catch(e => {
              console.log(e)
            })
          } else {
            console.log('=========2')
            // throw new Error('1')
          }
        })
      },
      // 更新数据
      updateData() {
        this.$refs.dialogRowData.validate(valid => {
          if (valid) {
            versionUpdate(this.dialogRowData).then(data => {
              this.getList()
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '更新成功',
                type: 'success',
                duration: 2000
              })
            }).catch(e => {
              console.log(e)
            })
          } else {
            return false
          }
        })
      },
      fileUploadSuccess(response, file, fileList) {
        if (response.success) {
          try {
            const path = response.data[0].file_path
            const url = new URL(path)
            this.updateApkStatus = 'success'
            this.dialogRowData.download_url = url.pathname
            this.uploadInfo = path
          } catch (e) {
            this.$message.error('数据解析异常')
          }
        } else {
          this.$message.error(response.message)
        }
      },
      fileUploadError(e, file, fileList) {
        this.updateApkStatus = 'error'
        this.$message.error('上传失败')
      },
      beforeAvatarUpload(file) {
        const isAPK = file.type === 'application/vnd.android.package-archive'
        if (!isAPK) {
          this.$message.error('只能上传apk文件')
        } else {
          this.updateApkStatus = 'pending'
        }
        return isAPK
      },
      fileUploadProgress(event, file, fileList) {
        console.log('================')
        console.log(event)
      },
      clientClear() {
        this.versionQuery.client_id = undefined
      }
    }
  }
</script>
<style scoped>
  .drag-handler{
    width: 30px;
    height: 30px;
    cursor: pointer;
  }
</style>
