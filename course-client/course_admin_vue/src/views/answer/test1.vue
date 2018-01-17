<template>
  <div style="margin: 100px" >
    <table border="1">
      <tr>
        <th @click="show">姓名</th>
        <th @click="show">电话</th>
        <th @click="show">电话</th>
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
      show($event) {
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
            1: 'a'
          }
        })
      }
    }
  }
</script>
