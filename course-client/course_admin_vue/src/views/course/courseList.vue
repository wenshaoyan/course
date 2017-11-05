<template>
  <div class="app-container calendar-list-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleSearch" style="width: 200px;" class="filter-item" placeholder="id" v-model="beanQuery.id" @change="queryParamChange('id')">
      </el-input>
      <el-select @change='handleSearch' style="width: 120px" class="filter-item" v-model="filterQuery.sort" placeholder="排序">
        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key">
        </el-option>
      </el-select>
      <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleSearch">搜索</el-button>
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
      <el-table-column align="center" label="标题" width="150">
        <template scope="scope">
          <span>{{scope.row.title}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="价格" width="80">
        <template scope="scope">
          <span>{{scope.row.price}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="描述">
        <template scope="scope">
          <span>{{scope.row.describe}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="分类" width="80">
        <template scope="scope">
          <span>{{scope.row.courseTypeAll.name}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="150">
        <template scope="scope">
          <el-button  size="small" type="success" @click="handleEditRow(scope.row)">编辑
          </el-button>
          <el-button  size="small" type="danger" @click="handleDeleteRow(scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div v-show="!listLoading" class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="bindPage"
                     :page-sizes="[10,20,30, 50]" :page-size="filterQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>

  </div>
</template>

<script>
  import { courseQuery } from '@/api/course'
  import waves from '@/directive/waves/index.js' // 水波纹指令

  export default {
    directives: {
      waves
    },
    data() {
      return {
        list: null,
        total: null,
        listLoading: true,
        filterQuery: {
          sort_by: 'create_time',
          sort: '+create_time',
          action: 'search',
          order: 'desc',
          limit: 10,
          page: 1
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
        sortOptions: [
          { label: '按创建时间升序', key: '+create_time' },
          { label: '按创建时间降序', key: '-create_time' }]

      }
    },
    watch: {
      'filterQuery.sort': 'watchSort'
    },
    created() {
      this.$emit('create')
      // 获取课程数据列表
      this.getList()
    },
    methods: {
      getClientName(id) {
        // console.log('==============')
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
      getList() {
        this.listLoading = true
        let query = Object.assign({}, this.filterQuery)
        query = Object.assign(query, this.beanQuery)
        courseQuery(query).then(data => {
          this.list = data.list
          if ('count' in data) this.total = data.count
          this.listLoading = false
        }).catch(e => {
          this.listLoading = false
        })
      },
      handleSearch() {
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
      }
    }
  }
</script>

