<template>
  <div class="app-container calendar-list-container">

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
    </el-table>

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
        listLoading: true
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        clientQuery().then(data => {
          this.list = data.list
          this.listLoading = false
        }).catch(e => {
          this.listLoading = false
        })
      }
    }
  }
</script>
