<template>
  <div v-if="isShow" id="kiko_tool_tip" @click="divClick" class="kiko-tool-tip" :class="{'left': direction === 'left', 'right': direction === 'right', 'top': direction === 'top', 'bottom': direction === 'bottom'}" :style="{'background-color': background, 'color': color, 'top': top, 'left': left}">
    <div v-if="type === 'select'">
      <el-checkbox-group v-for="(item, key) in options" :key="key" v-model="selectData" @change="selectChange" align="left">
          <!--<input type="checkbox" :value="key" v-model="selectData" class="">
          <label :for="key" class="" >{{item}}</label>-->
        <el-checkbox :label="key">{{item}}</el-checkbox>
      </el-checkbox-group>
      <div style="margin: 5px">
        <el-button type="primary" size="mini" @click="screen">筛选</el-button>
        <el-button type="success" size="mini" @click="save">确定</el-button>
        <el-button type="warning" size="mini" @click="cancel">取消</el-button>
      </div>
    </div>
    <div v-if="type === 'search'">
      <el-input
        placeholder="请输入内容"
        v-model="searchData"
        clearable>
      </el-input>
      <div style="margin: 5px">
        <el-button type="primary" size="mini" @click="screen">筛选</el-button>
        <el-button type="success" size="mini" @click="save">确定</el-button>
        <el-button type="warning" size="mini" @click="cancel">取消</el-button>
      </div>
    </div>
    <div v-if="type === 'sort'">
      <el-radio-group v-model="sortData" @change="sortChange">
        <el-radio label="asc">顺序</el-radio>
        <el-radio label="desc">倒序</el-radio>
      </el-radio-group>
      <div style="margin: 5px">
        <el-button type="primary" size="mini" @click="screen">筛选</el-button>
        <el-button type="success" size="mini" @click="save">确定</el-button>
        <el-button type="warning" size="mini" @click="cancel">取消</el-button>
      </div>
    </div>
    <div v-if="type === 'datetime'">
      <el-date-picker
        v-model="datetimeData"
        type="datetimerange"
        :picker-options="pickerOptions"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        align="right">
      </el-date-picker>
      <div style="margin: 5px">
        <el-button type="primary" size="mini" @click="screen">筛选</el-button>
        <el-button type="success" size="mini" @click="save">确定</el-button>
        <el-button type="warning" size="mini" @click="cancel">取消</el-button>
      </div>
    </div>
    <div class="arrow" :style="arrowStyleObject"></div>
  </div>

</template>

<script type="text/javascript">
  export default {
    name: 'kikoToolTip',
    data() {
      return {
        isShow: true,
        time: 3000,
        content: '',
        direction: 'right',
        background: '#fff',
        color: '#000',
        arrowStyleObject: '',
        options: {},
        type: 'text',
        isMultiple: false,
        context: '1',
        selectData: [],
        searchData: '',
        sortData: '',
        datetimeData: '',
        pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        callback: null,
        default: null,
        initData: ''  // 打开弹窗前的初始化数据
      }
    },
    beforeMount() {
      let node = document.querySelector('#kiko_tool_tip')
      if (node && node.parentNode) {
        node.parentNode.removeChild(node)
      }
    },
    computed: {
      top() {
        switch (this.direction) {
          case 'top':
            return (this.rect.top - 12) + 'px'
          case 'bottom':
            return (this.rect.top - 12) + 'px'
          case 'left':
            return (this.rect.top + this.rect.height / 2) + 'px'
          case 'right':
            return (this.rect.top + this.rect.height / 2) + 'px'
        }
      },
      left() {
        switch (this.direction) {
          case 'top':
            return (this.rect.left + this.rect.width / 2) + 'px'
          case 'bottom':
            return (this.rect.left + this.rect.width / 2) + 'px'
          case 'left':
            return (this.rect.left - 12) + 'px'
          case 'right':
            return (this.rect.left + this.rect.width + 12) + 'px'
        }
      }
    },
    mounted() {
      this.initColor()
      this.initData = JSON.stringify({
        selectData: this.selectData,
        searchData: this.searchData,
        sortData: this.sortData
      })
      if (this.time !== -1) this.hidden()
      // 点击其他地方隐藏
      document.onclick = () => {
        this.hidden()
      }
      if (this.default !== null && this.default !== undefined) {
      	this.selectData = this.default
        this.searchData = this.default
        this.sortData = this.default
        this.datetimeData = this.default
      }
    },
    methods: {
      initColor() {
        switch (this.direction.toLowerCase()) {
          case 'left':
            this.arrowStyleObject = {
              borderLeftColor: this.background
            }
            break;
          case 'right':
            this.arrowStyleObject = {
              borderRightColor: this.background
            }
            break;
          case 'top':
            this.arrowStyleObject = {
              borderTopColor: this.background
            }
            break;
          case 'bottom':
            this.arrowStyleObject = {
              borderBottomColor: this.background
            }
            break;
        }

      },
      hidden() {
        let that = this
        window.setTimeout(function(){
          that.isShow = false
        }, this.time)
      },
      divClick($event) {
        // 停止事件冒泡
        $event.stopPropagation();
      },
      selectChange() {},
      sortChange() {},
      screen() {
        this.change()
        this.hidden()
      },
      save() {
        this.change()
        this.hidden()
      },
      change() {
        if (this.callback && this.callback instanceof Function) {
        	let data = null
          if (this.type === 'select') {
            data = this.selectData
          } else if (this.type === 'search') {
            data = this.searchData
          } else if (this.type === 'sort') {
            data = this.sortData
          } else if (this.type === 'datetime') {
            data = this.datetimeData
          }
          if (data) {
            this.callback(data)
          }
        }
      },
      cancel() {
      	// 还原修改前的数据
        const parse = JSON.parse(this.initData)
        this.selectData = parse.selectData
        this.sortData = parse.sortData
        this.searchData = parse.searchData
        this.hidden()
      }
    }
  }
</script>

<style type="text/css">
  .kiko-tool-tip {
    box-shadow: 3px 3px 3px 3px #888888;
    display: block;
    position: absolute;
    position: fixed;
    padding: 10px 10px;
    border-radius: 5px;
    color: #fff;
    white-space: nowrap;
/*    word-wrap: break-word;*/
    z-index: 99999999;
    font-family: Helvetica Neue,Helvetica,PingFang SC,Hiragino Sans GB,Microsoft YaHei,SimSun,sans-serif
  }
  .kiko-tool-tip.left {
    transform: translate(-100%, -50%);
  }
  .kiko-tool-tip.right {
    transform: translate(0, -50%);
  }
  .kiko-tool-tip.top {
    transform: translate(-50%, -100%);
  }
  .kiko-tool-tip.bottom {
    transform: translate(-50%, 100%);
  }
  .kiko-tool-tip.right .arrow {
    display: inline-block;
    position: absolute;
    content: '';
    width: 0;
    height: 0;
    top: 50%;
    left: -10px;
    border-top: 10px solid transparent;
    /*border-left: 10px solid yellow;*/
    border-right: 15px solid #3695CC;
    border-bottom: 10px solid transparent;
    transform: translate(0, -50%);
  }
  .kiko-tool-tip.left .arrow {
    display: inline-block;
    position: absolute;
    content: '';
    width: 0;
    height: 0;
    top: 50%;
    right: -10px;
    border-top: 10px solid transparent;
    border-left: 15px solid #3695CC;
    border-bottom: 10px solid transparent;
    transform: translate(0, -50%);
  }
  .kiko-tool-tip.top .arrow {
    display: inline-block;
    position: absolute;
    content: '';
    width: 0;
    height: 0;
    left: 50%;
    bottom: -10px;
    border-top: 15px solid #3695CC;
    border-left: 10px solid transparent;
    border-right: 10px solid transparent;
    transform: translate(-50%, 0);
  }
  .kiko-tool-tip.bottom .arrow {
    display: inline-block;
    position: absolute;
    content: '';
    width: 0;
    height: 0;
    left: 50%;
    top: -10px;
    border-bottom: 15px solid #3695CC;
    border-left: 10px solid transparent;
    border-right: 10px solid transparent;
    transform: translate(-50%, 0);
  }

</style>


