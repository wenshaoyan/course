<template>
  <div style="margin: 100px" >
    <div id="root" class="container">
      <button type="button" class="button is-primary" @click="show()">显示</button>
      <div>111</div>
      <div>111</div>
      <div>111</div>
      <div>111</div>
      <div>111</div>
      <modal-select v-if="showModal" @fireclose="showModal = false"></modal-select>
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
      show() {
        this.showModal = true
      }
    }
  }
</script>
