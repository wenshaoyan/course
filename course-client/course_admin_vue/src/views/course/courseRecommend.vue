<template>
  <div>222</div>
</template>
<script>
  export default {
    props: {
      type: {
        type: String,
        default: 'CN'
      }
    },
    data() {
      return {
        a: 1
      }
    },
    created() {
      this.$emit('create')
    }
  }
</script>
