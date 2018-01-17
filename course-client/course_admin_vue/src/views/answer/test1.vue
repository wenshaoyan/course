<template>
  <div style="margin: 100px" >
    <table border="1">
      <tr>
        <th>
          <label>姓名</label>
          <div>
            <i @click="showSort">排序</i>
            <i @click="showSearch">搜索</i>
            <i @click="showSelect">过滤</i>
          </div>
        </th>
        <th>
          <label>电话</label>
          <div>
            <i @click="showSort">排序</i>
            <i @click="showSearch">搜索</i>
            <i @click="showSelect">过滤</i>
          </div>
        </th>
        <th>
          <label>电话</label>
          <div>
            <i @click="showSort">排序</i>
            <i @click="showSearch">搜索</i>
            <i @click="showSelect">过滤</i>
          </div>
        </th>
      </tr>
      <tr>
        <td>Bill Gates</td>
        <td>555 77 854</td>
        <td>555 77 855</td>
      </tr>
    </table>
  </div>
</template>

<script>
  import ModalSelect from '@/components/Base/modal_select'

  export default {
    components: { ModalSelect },
    data() {
      return {
        showModal: false
      }
    },
    methods: {
      showSort($event) {
        // this.showModal = true
        // this.$kiko_message('这是一个message');
        this.$kiko_tooltip($event, {
          direction: 'top',
          background: '#13CE66',
          color: '#fff',
          time: -1,
          type: "sort"
        })
      },
      showSearch($event) {
        // this.showModal = true
        // this.$kiko_message('这是一个message');
        this.$kiko_tooltip($event, {
          direction: 'top',
          background: '#13CE66',
          color: '#fff',
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
          direction: 'top',
          background: '#13CE66',
          color: '#fff',
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
