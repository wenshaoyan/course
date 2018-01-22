<template>
  <div style="padding:20px;">
    <el-collapse>
      <el-collapse-item title="过滤参数">
        <div>search:{{searchData}}</div>
        <div>sort:{{sortData}}</div>
        <div>select:{{selectData}}</div>
        <div>datetime:{{datetimeData}}</div>
      </el-collapse-item>
    </el-collapse>

    <table style="width: 100%">
      <thead>
      <tr  align="top">
        <th v-for="(item,key) in listFields" :key="key" prop="key" :width="item.width ? item.width : 'auth'" >
          <label>{{item.name}}</label>
          <div  v-if="typeof item.types === 'object'" class="container">
            <el-tag
              v-if="sortData[key]"
              size="small"
              type="success"
              closable
              :disable-transitions="false"
              @close="handleClose(key,'sort')">
              {{sub(sortData[key])}}
            </el-tag>
            <i v-if="!sortData[key] && 'sort' in item.types && item.types.sort && item.types.sort.show" class="fa fa-sort-amount-asc i-child-3" @click="showSort($event,key,item.sort)" ></i>
            <el-tag
              v-if="searchData[key]"
              size="small"
              type="warning"
              closable
              :disable-transitions="false"
              @close="handleClose(key,'search')">
              {{sub(searchData[key])}}
            </el-tag>
            <i v-if="!searchData[key] && 'search' in item.types && item.types.search && item.types.search.show" class="fa fa-search i-child-3" @click="showSearch($event,key,item.search)"></i>
            <el-tag
              v-if="selectData[key]"
              size="small"
              type="danger"
              closable
              :disable-transitions="false"
              @close="handleClose(key,'select')">
              {{sub(getSelectValue(key))}}
            </el-tag>
            <i v-if="!selectData[key]  && 'select' in item.types && item.types.select && item.types.select.show" class="fa fa-filter i-child-3" @click="showSelect($event,key,item.types.select)"></i>
            <el-tag
              v-if="datetimeData[key]"
              size="small"
              type="danger"
              closable
              :disable-transitions="false"
              @close="handleClose(key,'datetime')">
              {{sub(datetimeData[key])}}
            </el-tag>
            <i v-if="!datetimeData[key]  && 'datetime' in item.types && item.types.datetime && item.types.datetime.show" class="fa fa-calendar i-child-3" @click="showDatetime($event,key,item.types.datetime)"></i>
          </div>
        </th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(item,index) in data" :key="index">
        <td v-for="v in keys" :key="'td'+v" >
          <span v-if="v !== '_options'">{{filter(listFields[v], item, v)}}</span>
          <div v-if="v === '_options' && optionsButtons">
            <el-button v-if="optionsButtons.edit && optionsButtons.edit.show" type="success">{{optionsButtons.edit.text}}</el-button>
            <el-button v-if="optionsButtons.remove && optionsButtons.remove.show" type="danger">{{optionsButtons.edit.text}}</el-button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>

    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pages.currentPage"
        :page-sizes="[20, 30, 50]"
        :page-size="20"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pages.total">
      </el-pagination>
    </div>
  </div>
</template>

<script>

  import Restful from '@/api/restful'
  import moment from 'moment'
  const formatTime = (d, row) => {
    const f = 'YYYY-MM-DD HH:mm:ss.SSS'
    const m = moment(Number(d))
    return m.format(f)
  }
  export default {
    name: 'baseTable',
    props: {
      prefix: {
        type: String,
        required: true
      },
      listFields: {
        type: Object,
        required: true
      },
      fields: {
        type: Object,
        required: false,
        default: {}
      }
    },
    data () {
      const searchFields = {}
      const sortFields = {}
      const selectFields = {}
      const datetimeFields = {}
      for (let key in this.listFields) {
        searchFields[key] = undefined;
        sortFields[key] = undefined;
        selectFields[key] = undefined;
        datetimeFields[key] = undefined;
      }
      return {
        restful: new Restful(this.prefix),
        keys: [],
        loading: false,
        searchData: searchFields,
        sortData: sortFields,
        selectData: selectFields,
        datetimeData: datetimeFields,
        pages: {
          currentPage: 0,
          total: 100,
        },
        data: [],
        optionsButtons: false
      }
    },
    mounted() {
      // this.initChart()
      this.getList()
      for (const key in this.listFields) {
      	this.keys.push(key);
      }
      if (typeof this.listFields._options === 'object') {
      	if (typeof this.listFields._options.buttons === 'object') this.optionsButtons =  this.listFields._options.buttons
        else if (this.listFields._options.buttons === true) this.optionsButtons = {
          edit: {
            show: true,
            text: '编辑'
          },
          remove: {
            show: true,
            text: '删除'
          }
        }
      }
    },
    methods: {
      getList() {
        this.listLoading = true
        const params = {}
        this.restful.query(params).then(data => {
          this.data = data
          this.listLoading = false
        }).catch(e => {
          this.listLoading = false
        })
      },
      showSort($event, prop, $c) {
        this.$kiko_tooltip($event, {
          time: -1,
          type: "sort",
          options: {
            asc: '顺序',
            desc: '倒序'
          },
          default: this.sortData[prop],
          callback: (data) => {
            this.changeData(prop, 'sort', data)
          }
        })
      },
      showSearch($event, prop, $c) {
        this.$kiko_tooltip($event, {
          time: -1,
          type: "search",
          isMultiple: true,
          default: this.searchData[prop],
          callback: (data) => {
            this.changeData(prop, 'search', data)
          }
        })
      },
      showSelect($event, prop, $c) {
        this.$kiko_tooltip($event, {
          time: -1,
          type: "select",
          isMultiple: true,
          options: $c.options,
          default: this.selectData[prop],
          callback: (data) => {
            this.changeData(prop, 'select', data)
          }
        })
      },
      showDatetime($event, prop, $c) {
        this.$kiko_tooltip($event, {
          time: -1,
          type: "datetime",
          default: this.datetimeData[prop],
          callback: (data) => {
            data[0] = moment(data[0]).format('YYYY-MM-DD HH:mm:ss')
            data[1] = moment(data[1]).format('YYYY-MM-DD HH:mm:ss')
            this.changeData(prop, 'datetime', data)
          }
        })
      },
      getActionData(action) {
        if (action === 'select') return this.selectData
        else if (action === 'search') return this.searchData
        else if (action === 'sort') return this.sortData
        else if (action === 'datetime') return this.datetimeData
      },
      changeData(prop, action, _data) {
        const data = this.getActionData(action);
      	if (data) data[prop] = _data
      },
      handleClose(prop, action) {
        const data = this.getActionData(action);
        if (data) data[prop] = undefined
      },
      filter(c, row, key) {
        if (c.filter) {
          if (c.filter === 'formatTime') c.filter = formatTime
          if (c.filter instanceof Function) {
            return c.filter(row[key], row)
          }
        }
        return row[key]
      },
      getSelectValue(key) {
      	const list = []
        const options = this.listFields[key].types.select.options
        for (let v of this.selectData[key]) {
        	list.push(options[v])
        }
        return list.toString()
      },
      sub(str) {
      	if (str instanceof Array) {
          const d1 = moment(str[0]).format('YYYY-MM-DD');
          const d2 = moment(str[1]).format('YYYY-MM-DD');
          return d1 + '至' + d2
        }
        if(str.length > 6){
          return str.substring(0,6)+"...";
        }
        return str
      },
      // page
      handleSizeChange() {

      },
      handleCurrentChange() {

      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

  /* Border styles */

  table {
    width: 100%;
    min-height: 25px;
    line-height: 25px;
    text-align: center;
    border-collapse: collapse;
    padding: 2px;
  }

  table, table tr th, table tr td {
    border: 1px solid #CCCCCC;
  }

  /* Padding and font style */
  table td, table th {
    padding: 5px 10px;
    font-size: 12px;
  }

  /* Alternating background colors */
  table tr:nth-child(even) {
    background: rgb(230, 238, 214)
  }

  table tr:nth-child(odd) {
    background: #FFF
  }
  table th {
    background: #ebeef5;
  }


  .container{
    width: 100%;
    white-space: nowrap;
  }

  .i-child-3{
    width: 30%;
  }
  .i-child-2{
    width: 30%;
  }
  .i-child-1{
    width: 90%;
  }
  .block{
    padding: 30px 24px;
    border-bottom: 1px solid #eff2f6;
  }
</style>
