<template>
  <div v-if="isShow" id="kiko_tool_tip" @click="divClick" class="kiko-tool-tip" :class="{'left': direction === 'left', 'right': direction === 'right', 'top': direction === 'top', 'bottom': direction === 'bottom'}" :style="{'background-color': background, 'color': color, 'top': top, 'left': left}">
    <div v-if="type === 'select'" v-for="(item, key) in options">
        <input type="checkbox" :value="key" v-model="selectData" class="">
        <label :for="key" class="" >{{item}}</label>
    </div>
    <div v-if="type === 'search'">
      <input v-model="searchData">
    </div>
    <div v-if="type === 'sort'">
      <div class="radio-div">
        <input type="radio" value="asc" v-model="sortData" class="">
        <label class="" >asc</label>
        <input type="radio" value="desc" v-model="sortData" class="">
        <label class="" >desc</label>
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
        direction: 'top',
        background: '#fff',
        color: '#000',
        arrowStyleObject: '',
        options: {},
        type: 'text',
        isMultiple: false,
        context: '1',
        selectData: [],
        searchData: '',
        sortData: ''
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
            return (this.rect.top + 12) + 'px'
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
      if (this.time !== -1) this.hidden()
      // 点击其他地方隐藏
      document.onclick = () => {
        this.hidden()
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
      }
    },
    watch: {
      sortData(o, n) {
        console.log(o, n)
      },
      selectData(o, n) {
        console.log(o, n)
      }
    }
  }
</script>

<style type="text/css">
  .kiko-tool-tip {
    display: block;
    position: absolute;
    position: fixed;
    background-color: #3695CC;
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
  .my-label {
    display: inline-block;
    cursor: pointer;
    position: relative;
    padding-left: 25px;
    margin-right: 15px;
    font-size: 13px;
  }

  .my-label:before {
    content: "";
    display: inline-block;

    width: 16px;
    height: 16px;

    margin-right: 10px;
    position: absolute;
    left: 0;
    bottom: 1px;
    background-color: #aaa;
    box-shadow: inset 0px 2px 3px 0px rgba(0, 0, 0, .3), 0px 1px 0px 0px rgba(255, 255, 255, .8);
  }

  .radio-div label:before {
    border-radius: 8px;
  }
  .my-checkbox label:before {
    border-radius: 3px;
  }


  .my-radio,
  .my-checkbox {
    display: none;
  }

  .my-radio + label:before {
    content: "\2022";
    color: #f3f3f3;
    font-size: 30px;
    text-align: center;
    line-height: 16px;
  }
  .my-checkbox + label:before {
    content: "\2713";
    text-shadow: 1px 1px 1px rgba(0, 0, 0, .2);
    font-size: 15px;
    color: #f3f3f3;
    text-align: center;
    line-height: 18px;
  }

</style>


<!--
<html>
	<head>
		<style>
			label {
    display: inline-block;
    cursor: pointer;
    position: relative;
    padding-left: 25px;
    margin-right: 15px;
    font-size: 13px;
}

label:before {
    content: "";
    display: inline-block;

    width: 16px;
    height: 16px;

    margin-right: 10px;
    position: absolute;
    left: 0;
    bottom: 1px;
    background-color: #aaa;
    box-shadow: inset 0px 2px 3px 0px rgba(0, 0, 0, .3), 0px 1px 0px 0px rgba(255, 255, 255, .8);
}

.radio label:before {
    border-radius: 8px;
}
.checkbox label:before {
    border-radius: 3px;
}


input[type=radio],
input[type=checkbox] {
    display: none;
}

input[type=radio]:checked + label:before {
    content: "\2022";
    color: #f3f3f3;
    font-size: 30px;
    text-align: center;
    line-height: 13px;
}
input[type=checkbox]:checked + label:before {
    content: "\2713";
    text-shadow: 1px 1px 1px rgba(0, 0, 0, .2);
    font-size: 15px;
    color: #f3f3f3;
    text-align: center;
    line-height: 15px;
}

		</style>
	</head>
	<body>
		<div class="radio">
    <input id="male" type="radio" name="gender" value="male">
    <label for="male">Male</label>
    <input id="female" type="radio" name="gender" value="female">
    <label for="female">Female</label>
</div>


// checkbox input
<div class="checkbox">
    <input id="check1" type="checkbox" name="check" value="check1">
    <label for="check1">Checkbox No. 1</label>

    <input id="check2" type="checkbox" name="check" value="check2">
    <label for="check2">Checkbox No. 2</label>
</div>
	</body>
</html>
-->
