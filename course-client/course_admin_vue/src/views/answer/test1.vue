<template>
  <div style="margin: 100px" >
    <div id="root" class="container">
      <button type="button" class="button is-primary" @click="show">显示</button>
    </div>
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
