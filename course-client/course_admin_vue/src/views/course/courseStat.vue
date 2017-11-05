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
      <el-table-column align="center" label="操作" width="150">
        <template scope="scope">
          <el-button  size="small" type="success" @click="handleEditRow(scope.row)">编辑
          </el-button>
          <el-button  size="small" type="danger" @click="handleDeleteRow(scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" label="拖拽" width="95">
        <template scope="scope">
          <icon-svg class='drag-handler' icon-class="drag"></icon-svg>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
  import { bannerQuery } from '@/api/banner'
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
        sortOptions: [
          { label: '按位置升序', key: '+location' },
          { label: '按位置降序', key: '-location' },
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
        bannerQuery(query).then(data => {
          this.list = data
          if ('count' in data) this.total = data.count
          this.listLoading = false
        }).catch(e => {
          this.listLoading = false
        })
      },
      handleSearch() {
        this.filterQuery.action = 'search'
        this.getList()
      }
    }
  }
</script>

