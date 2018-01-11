<template>
  <base-list :listFields="list_fields" :fields="fields"></base-list>
</template>
<script>
  import BaseList from '@/components/Base/list'
  export default {
    components: { BaseList },
    data() {
      return {
        list_fields: {
          a: 1
        },
        fields: {
          b: 1
        }
      }
    }
  }
</script>
