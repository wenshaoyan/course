<template>
  <div class="app-container calendar-list-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleSearch" style="width: 200px;" class="filter-item" placeholder="id" v-model="beanQuery.id" @change="queryParamChange('id')">
      </el-input>

      <el-select clearable class="filter-item" style="width: 130px" v-model="beanQuery.client_id" placeholder="客户端列表" @clear="clientClear" :loading="listClientLoading" loading-text="加载中..">
        <el-option v-for="item in clientList" :key="item.id" :label="item.name" :value="item.id">
        </el-option>
      </el-select>

      <el-select @change='handleSearch' style="width: 120px" class="filter-item" v-model="filterQuery.sort" placeholder="排序">
        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key">
        </el-option>
      </el-select>

      <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleSearch">搜索</el-button>
      <el-button class="filter-item" type="primary" v-waves icon="edit" @click="handleCreate">添加</el-button>
      <el-button class="filter-item" type="primary" v-waves  @click="handlePostLocation" :disabled="isLocationChange">提交位置修改</el-button>
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
          <span>{{getClientName(scope.row.client_id)}}</span>
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
      <el-form class="small-space" :model="dialogRowData" :rules="rules" ref="dialogRowData" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="id" v-show="dialogStatus === 'update'">
          <span>{{dialogRowData.id}}</span>
        </el-form-item>
        <el-form-item label="跳转地址" prop="redirect_url">
          <el-input v-model="dialogRowData.redirect_url"></el-input>
        </el-form-item>
        <el-form-item label="图片地址" prop="image_url">
          <el-upload
            action="http://123.207.55.204:8083/upload/image"
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
        <el-form-item label="客户端" prop="client_id">
          <span>{{getClientName(dialogRowData.client_id)}}</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='create' && !dialogRowData.id" type="primary" @click="insertData">确 定</el-button>
        <el-button v-else type="primary" @click="updateData">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { clientQueryPattern, versionUpdate } from '@/api/client'
  import { bannerQuery, bannerPatch, bannerInsert } from '@/api/banner'
  import waves from '@/directive/waves/index.js' // 水波纹指令
  import Sortable from 'sortablejs'
  export default {
    name: 'table_demo',
    directives: {
      waves
    },
    data() {
      return {
        ruleForm: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        list: null,
        newList: [],
        total: null,
        listLoading: true,
        listClientLoading: true,
        filterQuery: {
          sort_by: 'location',
          sort: '+location',
          action: 'search',
          order: 'desc'
        },
        beanQuery: {
          client_id: undefined,
          id: undefined
        },
        bindPage: 1,
        clientList: [],
        clientMap: null,
        dialogRowData: {
          id: undefined,
          redirect_url: undefined,
          client_id: undefined,
          image_url: undefined
        },
        textMap: {
          update: '编辑',
          create: '创建'
        },
        dialogStatus: '',
        dialogFormVisible: false,
        rules: {
          image_url: [
            { required: true, message: '上传图片', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          redirect_url: [
            { required: true, message: '跳转地区必填', trigger: 'blur' }
          ],
          client_id: [
            { required: true, message: '请输入活动名称', trigger: 'blur' }
          ]
        },
        sortOptions: [
          { label: '按位置升序', key: '+location' },
          { label: '按位置降序', key: '-location' },
          { label: '按创建时间升序', key: '+create_time' },
          { label: '按创建时间降序', key: '-create_time' }],
        // pending: 上传中 success:成功 error:失败 await:等待上传
        updateImageStatus: 'await',
        uploadInfo: '上传轮播图图片',
        isLocationChange: true

      }
    },
    watch: {
      'filterQuery.sort': 'watchSort',
      'beanQuery.client_id': 'clientIdChange'
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
        this.beanQuery.client_id = temp ? +temp : temp
      },
      getClientName(id) {
        return this.clientMap ? this.clientMap.get(id) : ''
      },
      queryParamChange(keyName) {
        if (keyName in this.beanQuery && this.beanQuery[keyName] === '') {
          this.beanQuery[keyName] = undefined
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
        this.$route.query.client_id = this.beanQuery.client_id
        // alert(this.$route.query.client_id)
      },
      getList() {
        this.listLoading = true
        let query = Object.assign({}, this.filterQuery)
        query = Object.assign(query, this.beanQuery)
        bannerQuery(query).then(data => {
          this.list = data
          if ('count' in data) this.total = data.count
          this.listLoading = false
          // 拖拽数据初始化
          this.dragDataInit()
        }).catch(e => {
          this.listLoading = false
        })
      },
      dragDataInit() {
        if (!this.listLoading) {
          this.newList = this.list.map(v => v.id)
          this.$nextTick(() => {
            this.setSort()
          })
        }
      },
      setSort() {
        const el = document.querySelectorAll('.el-table__body-wrapper > table > tbody')[0]
        this.sortable = Sortable.create(el, {
          onEnd: evt => {
            this.isLocationChange = false
            const tempIndex = this.newList.splice(evt.oldIndex, 1)[0]
            this.newList.splice(evt.newIndex, 0, tempIndex)
          }
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
          this.uploadInfo = row.image_url
          this.dialogFormVisible = true
        } else {
          this.$message({ message: '获取客户端id失败', type: 'error' })
        }
      },
      handleCreate() {
        if (this.beanQuery.client_id) {
          this.resetDialogRow()
          this.dialogRowData.client_id = this.beanQuery.client_id
          this.dialogStatus = 'create'
          this.dialogFormVisible = true
          this.uploadInfo = '上传轮播图图片'
        } else {
          this.$message({ message: '请选择客户端后再添加', type: 'error' })
        }
      },
      resetDialogRow() {
        this.dialogRowData = {
          id: undefined,
          image_url: undefined,
          client_id: undefined
        }
        console.log(this.$refs.dialogRowData)
      },
      handleSearch() {
        this.filterQuery.action = 'search'
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
        this.$refs.dialogRowData.validate((valid) => {
          if (valid) {
            bannerInsert(this.dialogRowData).then(data => {
              this.getList()
              this.resetForm()
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
            return false
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
            this.updateImageStatus = 'success'
            this.dialogRowData.image_url = url.pathname
            this.uploadInfo = path
          } catch (e) {
            this.$message.error('数据解析异常')
          }
        } else {
          this.$message.error(response.message)
        }
      },
      fileUploadError(e, file, fileList) {
        this.updateImageStatus = 'error'
        this.$message.error('上传失败')
      },
      beforeAvatarUpload(file) {
        const isAPK = file.type === 'image/jpeg'
        if (!isAPK) {
          this.$message.error('只能上传图片')
        } else {
          this.updateImageStatus = 'pending'
        }
        return isAPK
      },
      fileUploadProgress(event, file, fileList) {
        console.log('================')
        console.log(event)
      },
      clientClear() {
        this.beanQuery.client_id = undefined
      },
      handlePostLocation() {
        // 对比新旧数组修改的地方
        // 提交位置修改请求
        const changeList = []
        this.newList.forEach((value, index) => {
          changeList.push(bannerPatch(value, { location: index + 1 }))
        })
        if (changeList.length !== 0) {
          this.listLoading = true
          Promise.all(changeList).then(result => {
            this.isLocationChange = true
            this.listLoading = false
            this.$message.info('修改成功')
          }).catch(e => {
            this.listLoading = false
          })
        } else {
          this.$message.info('没有位置修改')
        }
      },
      resetForm() {
        this.$refs.dialogRowData.resetFields()
      }
    }
  }
</script>
<style>
  .drag-handler{
    width: 30px;
    height: 30px;
    cursor: pointer;
  }
</style>
