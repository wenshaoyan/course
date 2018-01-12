<!-- :filters="[{ text: '家', value: '家' }, { text: '公司', value: '公司' }]"-->
<template>
  <div class="app-container calendar-list-container">
    <el-table  :data="list" v-loading="listLoading" element-loading-text="给我一点时间" border fit highlight-current-row
                @sort-change="sortChange" >
      <el-table-column v-for="(item,key) in listFields" align="center"
                       :key="key" :label="item.name" :width="item.width" :prop="key"
                       :filters="item.type === 'select' ? item.options : null"
                       :sortable="item.order ? 'custom' : false"
      >
        <template scope="scope">
          <span>{{filter(item, scope.row, key)}}</span>
        </template>
      </el-table-column>
    </el-table>
  </div>

</template>

<script>
  /**
   * listFields : {
   *  id:{
   *    name:'',
   *    width:'30',
   *   type:'select', // search:搜索框 select:下拉
   *   options:{a:'1'}  // type为select时候才生效
   *    default:'1',
   *    filter:function(d,val){ // 过滤器
   *
   *    },
   *    sort:true
   *  }
   * }
   * queryData{
   *  order[field]= 1|2   单数为asc 双数为desc  值越小 优先按该字段排序
   *  search[field]=value
   * }
   */
  import waves from '@/directive/waves/index.js' // 水波纹指令
  import moment from 'moment'
  const formatTime = (d, row) => {
    const f = 'YYYY-MM-DD HH:mm:s'
    const m = moment(Number(d))
    return m.format(f)
  }
  import Restful from '@/api/restful'
  const reOrder = /^order\[.*\]/
  // const reSearch = /^search\[.*\]/
  export default {
    directives: {
      waves
    },
    props: {
      prefix: {
        type: String,
        required: true
      },
      listFields: {
        type: Object,
        required: false
      },
      fields: {
        type: Object,
        required: false
      },
      type: {
        type: String,
        default: 'null'
      },
      options: {
        type: Array
      },
      order: {
        type: Boolean,
        default: false
      }

    },
    data() {
      return {
        list: [],
        listLoading: true,
        restful: new Restful(this.prefix),
        queryData: {}
      }
    },
    mounted() {
      // this.initChart()
      this.getList()
      console.log(this.$route.query)
    },
    methods: {
      getInitSort() {
        const result = {
          prop: 'id',
          order: 'ascending'
        }
        for (const key in this.$route.query) {
          if (reOrder.test(key)) {
            result.prop = key.substr(6, key.length - 7)
            result.order = this.queryData[key] % 2 ? 'ascending' : 'descending'
          }
        }
        return result
      },
      renderHeader(createElement, { _self }) {
        return 'b'
      },
      sortChange(obj) {
        let maxOrderIndex = 0
        let orderIndex = 0
        for (const key in this.queryData) {
          if (reOrder.test(key)) {
            if (this.queryData[key] > maxOrderIndex)maxOrderIndex = this.queryData[key]
            delete this.queryData[key]  // 如果需要多个字段排序 需要删除该代码
          }
        }
        if (obj.order === 'ascending') {
          if (maxOrderIndex % 2) {  // 奇数
            orderIndex = maxOrderIndex + 2
          } else {  // 偶数
            orderIndex = maxOrderIndex + 1
          }
        } else {
          if (maxOrderIndex % 2) {  // 奇数
            orderIndex = maxOrderIndex + 3
          } else {  // 偶数
            orderIndex = maxOrderIndex + 2
          }
        }
        this.queryData[`order[${obj.prop}]`] = orderIndex % 2 ? 1 : 2
        // 如果需要多个字段排序 需要替换为该行代码
        // this.queryData[`order[${obj.prop}]`] = orderIndex
        this.reset()
      },
      reset() {
        // this.$route.query = { b: 2 }
        this.$router.replace({ path: this.$router.path, query: this.queryData })
      },
      getList() {
        this.listLoading = true
        const params = {}
        this.restful.query(params).then(data => {
          this.list = data
          this.listLoading = false
        }).catch(e => {
          this.listLoading = false
        })
      },
      filter(c, row, key) {
        if (c.filter) {
          if (c.filter === 'formatTime') c.filter = formatTime
          if (c.filter instanceof Function) {
            return c.filter(row[key], row)
          }
        }
        return row[key]
      }
    }
  }
</script>
<style>

</style>
