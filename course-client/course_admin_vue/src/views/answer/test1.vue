<template>
  <div style="margin: 100px" >
    <input v-model="money" @keydown.enter="test">
    <div>111</div>
    <div>111</div>
    <div>111</div>
    <div>111</div>
    <div>111</div>
    <div>111</div>
    <div>111</div>
    <div>111</div>
    <span>111</span>
    <span>111</span>
    <span>111</span>
    <span>111</span>
    <div v-show="showModal">
      <div class="modal-header" slot="modal-header">确认</div>
      <div class="modal-body" slot="modal-body">确定转移 {{ money }} 元 ？</div>
      <a class="modal-foot" slot="modal-foot" @click="transfer">确定</a>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        money: 0,
        showModal: false
      }
    },
    methods: {
      transfer() {
        console.log('==============')
      },
      test() {
        this.showModal = true
      }
    }
  }
</script>
