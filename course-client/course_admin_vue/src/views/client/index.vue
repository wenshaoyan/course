<template>
  <div class="app-container calendar-list-container">
    <div class="filter-container">
    <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="edit">添加</el-button>
    </div>
    <el-table  :data="list" v-loading="listLoading" element-loading-text="给我一点时间" border fit highlight-current-row >

      <el-table-column align="center" label="id" width="95">
        <template scope="scope">
          <span>{{scope.row.id}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="名称">
        <template scope="scope">
          <span>{{scope.row.name}}</span>
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
      <el-table-column align="center" label="包名" >
        <template scope="scope">
          <span>{{scope.row.package_name}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="新版本名称" width="150" >
        <template scope="scope">
          <span>{{scope.row.new_version.version_name}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="80">
        <template scope="scope">
          <el-button  size="small" type="success" @click="handleEditRow(scope.row)">编辑
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="dialogRowData" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="id">
          <span v-model="dialogRowData.id"></span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="insertData">确 定</el-button>
        <el-button v-else type="primary" @click="updateData">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import { clientQuery } from '@/api/client'
  import waves from '@/directive/waves/index.js' // 水波纹指令

  export default {
    name: 'client',
    directives: {
      waves
    },
    data() {
      return {
        list: null,
        total: null,
        listLoading: true,
        textMap: {
          update: '编辑',
          create: '创建'
        },
        dialogStatus: '',
        dialogRowData: {
          id: undefined,
          name: undefined,
          create_time: undefined,
          update_time: undefined,
          package_name: undefined
        },
        dialogFormVisible: false
      }
    },
    created() {
      this.queryData()
    },
    methods: {
      // 查询数据
      queryData() {
        this.listLoading = true
        clientQuery().then(data => {
          this.list = data.list
          this.listLoading = false
        }).catch(e => {
          this.listLoading = false
        })
      },
      // 插入数据
      insertData() {

      },
      // 更新数据
      updateData() {

      },
      handleEditRow(row) {
        this.dialogRowData = Object.assign({}, row)
        this.dialogFormVisible = true
      },
      handleCreate() {
        this.resetDialogRow()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
      },
      handleUpdate(row) {
        this.dialogRowData = Object.assign({}, row)
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
      },
      resetDialogRow() {
        this.dialogRowData = {
          id: undefined,
          name: undefined,
          create_time: undefined,
          update_time: undefined,
          package_name: undefined
        }
      },
      isEditStatus() {
        console.log(this.dialogStatus)
        return this.dialogStatus === 'update'
      }

    }
  }
</script>
