<template>
  <div style="padding:20px;">
    <div>
      <table>
        <thead>
        <tr>
          <th prop="id">
            <label>序号</label>
            <div class="container">
              <i class="fa fa-sort-amount-asc i-child-3" @click="showSort" ></i>
              <i class="fa fa-search i-child-3" @click="showSearch"></i>
              <i class="fa fa-filter i-child-3" @click="showSelect"></i>
            </div>
          </th>
          <th prop="name">
            <label>用户名</label>
            <div class="container">
              <i class="fa fa-sort-amount-asc i-child-3" @click="showSort"></i>
              <i class="fa fa-search i-child-3" @click="showSearch"></i>
              <i class="fa fa-filter i-child-3" @click="showSelect"></i>
            </div>
          </th>
          <th prop="age">
            <label>年龄</label>
            <div class="container">
              <i class="fa fa-sort-amount-asc"></i>
              <i class="fa fa-search"></i>
              <i class="fa fa-filter"></i>
            </div>
          </th>
          <th prop="school">
            <label>毕业学校</label>
            <div class="container">
              <i class="fa fa-sort-amount-asc"></i>
              <i class="fa fa-search"></i>
              <i class="fa fa-filter"></i>
            </div>
          </th>
          <th prop="_options">
            <label>操作</label>
          </th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(user,index) in users">
          <td width="20%">{{index+1}}</td>
          <td width="10%">{{user.name}}</td>
          <td width="30%">{{user.age}}</td>
          <td width="20%">{{user.school}}</td>
          <td>
            <button @click="remove(index)">remove</button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
  import ModalSelect from '@/components/Base/modal_select'
  export default {
    components: { ModalSelect },
    name: 'hello',
    data () {
      return {
        user: {'name': '', 'age': '', 'school': ''},
        users: [
          {'name': '李磊', 'age': '25', 'school': '洛阳理工'},
          {'name': '张成', 'age': '23', 'school': '桂林电子科技'},
          {'name': '炼心', 'age': '22', 'school': '江西电子科技'}
        ],
        options: [
          { text: 'One', value: 'A' },
          { text: 'Two', value: 'B' },
          { text: 'Three', value: 'C' }
        ],
        showModal: true,
        loading: false,
        loadingFullscreen: false
      }
    },
    methods: {
      insert: function () {
        this.users.push(JSON.parse(JSON.stringify(this.user)))
      },
      remove: function (index) {
        this.users.splice(index, 1)
      },
      clickSort($event) {
        this.$kiko_tooltip($event, {
          content: '<h1>这是一个toolTip</h1>',
          direction: 'bottom',
          background: '#13CE66',
          color: '#fff',
        })
      },
      clickSearch() {
        alert(2)

      },
      clickFilter() {
        alert(3)
      },

      showSort($event) {
        // this.showModal = true
        // this.$kiko_message('这是一个message');
        this.$kiko_tooltip($event, {
          time: -1,
          type: "sort"
        })
      },
      showSearch($event) {
        // this.showModal = true
        // this.$kiko_message('这是一个message');
        this.$kiko_tooltip($event, {
          time: -1,
          type: "search",
          isMultiple: true,
          options: {
            1: 'a',
            2: 'b'
          }
        })
      },
      showSelect($event) {
        // this.showModal = true
        // this.$kiko_message('这是一个message');
        this.$kiko_tooltip($event, {
          time: -1,
          type: "select",
          isMultiple: true,
          options: {
            1: 'a',
            2: 'b'
          }
        })
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
</style>
