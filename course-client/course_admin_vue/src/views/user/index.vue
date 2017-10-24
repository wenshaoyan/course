<template>
  <div class="app-container calendar-list-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleSearch" style="width: 200px;" class="filter-item" placeholder="id" v-model="listQuery.id" @change="queryParamChange('id')">
      </el-input>

      <el-input @keyup.enter.native="handleSearch" style="width: 200px;" class="filter-item" placeholder="名称" v-model="listQuery.name" @change="queryParamChange('name')">
      </el-input>

      <el-select clearable class="filter-item" style="width: 130px" v-model="listQuery.role_id" placeholder="角色列表">
        <el-option v-for="item in roleList" :key="item.id" :label="item.name" :value="item.id">
        </el-option>
      </el-select>

      <el-select @change='handleSearch' style="width: 120px" class="filter-item" v-model="listQuery.sort" placeholder="排序">
        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key">
        </el-option>
      </el-select>

      <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleSearch">搜索</el-button>
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
      <el-table-column align="center" label="电话" width="150">
        <template scope="scope">
          <span>{{scope.row.tel}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="注册时间" width="150">
        <template scope="scope">
          <span>{{scope.row.create_time | parseTime('{y}-{m}-{d} {h}:{i}')}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="更新时间" width="150">
        <template scope="scope">
          <span>{{scope.row.update_time | parseTime('{y}-{m}-{d} {h}:{i}')}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="角色id" >
        <template scope="scope">
          <span>{{getRoleName(scope.row.role_id)}}</span>
        </template>
      </el-table-column>
    </el-table>

    <div v-show="!listLoading" class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="bindPage"
                     :page-sizes="[10,20,30, 50]" :page-size="listQuery.per_page" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>



  </div>
</template>

<script>
  import { userQuery } from '@/api/user'
  import { roleQuery } from '@/api/role'
  import waves from '@/directive/waves/index.js' // 水波纹指令

  export default {
    name: 'table_demo',
    directives: {
      waves
    },
    data() {
      return {
        list: null,
        total: null,
        listLoading: true,
        listQuery: {
          page: 1,
          per_page: 10,
          name: undefined,
          id: undefined,
          role_id: undefined,
          sortBy: 'user_create_time',
          order: 'desc',
          type: undefined,
          sort: '-user_create_time',
          action: 'search'
        },
        bindPage: 1,
        roleList: [],
        roleMap: null,
        sortOptions: [
          { label: '按ID升序', key: '+user_id' },
          { label: '按ID降序', key: '-user_id' },
          { label: '按注册时间升序', key: '+user_create_time' },
          { label: '按注册时间降序', key: '-user_create_time' }]
      }
    },
    watch: {
      'listQuery.sort': 'watchSort'
    },
    created() {
      this.getRoleAll()
      this.getList()
    },
    methods: {
      getRoleName(id) {
        return this.roleMap ? this.roleMap.get(id) : ''
      },
      queryParamChange(keyName) {
        if (keyName in this.listQuery && this.listQuery[keyName] === '') {
          this.listQuery[keyName] = undefined
        }
      },
      watchSort() {
        const sort = this.listQuery.sort
        if (sort[0] === '+') {
          this.listQuery.order = 'asc'
          this.listQuery.sortBy = sort.slice(1, sort.length)
        } else if (sort[0] === '-') {
          this.listQuery.order = 'desc'
          this.listQuery.sortBy = sort.slice(1, sort.length)
        }
      },
      getList() {
        this.listLoading = true
        userQuery(this.listQuery).then(data => {
          this.list = data.list
          if ('count' in data) this.total = data.count
          this.listLoading = false
        }).catch(e => {
          this.listLoading = false
        })
      },
      getRoleAll() {
        roleQuery().then(data => {
          this.roleList = data
          const map = new Map()
          data.forEach(value => map.set(value.id, value.name))
          this.roleMap = map
        })
      },
      handleSearch() {
        this.listQuery.page = 1
        this.listQuery.action = 'search'
        this.getList()
      },
      handleSizeChange(val) {
        this.listQuery.action = 'search'
        this.listQuery.per_page = val
        this.listQuery.page = 1
        this.getList()
      },
      handleCurrentChange(val) {
        this.listQuery.action = 'paging'
        this.listQuery.page = val
        this.bindPage = val
        this.getList()
      }
    }
  }
</script>
