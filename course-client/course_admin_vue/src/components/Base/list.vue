<template>
  <div>111</div>
</template>

<script>
  /**
   * listFields : [{
   *  name:"id",
   * }]
   */
  export default {
    props: {
      listFields: {
        type: Object,
        required: false
      },
      fields: {
        type: Object,
        required: false
      }
    },
    data() {
      return {}
    },
    mounted() {
      // this.initChart()
      console.log(this.listFields, this.fields)
    },
    methods: {

    }
  }
</script>
<style>

</style>
