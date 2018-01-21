<template>
  <base-list :listFields="list_fields" :fields="fields" prefix="/topic-banks"></base-list>
</template>
<script>
  import BaseList from '@/components/Base/list'
  const typeMap = new Map()
  typeMap.set('sat_paper', '真题试卷')
  typeMap.set('sim_paper', '模拟试卷')
  typeMap.set('practice', '练习')
  export default {
    components: { BaseList },
    data() {
      return {
        list_fields: {
          id: {
            name: 'id',
            width: 'auto',
            types: {
            	search: {
            		show: true,
              },
              order: {
                show: true,
              }
            }
          },
          name: {
            name: '题库名称',
            width: 'auto',
            types: {
              search: {
                show: true,
              },
              order: {
                show: true,
              }
            }
          },
          type: {
            name: '题库类型',
            width: 'auto',
            types: {
              search: {
                show: true,
              },
              order: {
                show: true,
              },
              select: {
                show: true,
              }
            },
            filter: (d, row) => {
              let v = typeMap.get(d)
              if (v === null || v === undefined) v = '类型错误'
              return v
            }
          },
          create_time: {
            name: '创建时间',
            width: 'auto',
            filter: 'formatTime'
          },
          update_time: {
            name: '更新时间',
            width: 'auto',
            filter: 'formatTime'
          }
        },
        fields: {
          b: 1
        }
      }
    }
  }
</script>
