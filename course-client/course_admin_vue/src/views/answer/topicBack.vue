<template>
  <base-table v-bind="table">

  </base-table>
</template>

<script>
  import BaseTable from '@/components/Base/table'
  const typeJson = {
  	'sat_paper': '真题试卷',
    'sim_paper': '模拟试卷',
    'practice': '练习',
  }
  export default {
    components: { BaseTable },
    name: 'hello',
    data () {
      return {
        table: {
          prefix: '/topic-banks',
          listFields: {
            tb_id: {
              name: 'id',
              width: '80',
              types: {
              	search: {
                  show: true,
                },
                sort: {
              		show: true,
                }
              }
            },
            tb_name: {
              name: '题库名称',
              width: 'auth',
              types: {
                search: {
                  show: true,
                },
                sort: {
                  show: true,
                }
              }
            },
            tb_type: {
              name: '题库类型',
              width: '100',
              types: {
                select: {
                  show: true,
                  options: typeJson
                }
              },
              filter: (d, row) => {
                let v = typeJson[d]
                if (v === null || v === undefined) v = '类型错误'
                return v
              }
            },
            tb_create_time: {
              name: '创建时间',
              width: '120',
              filter: 'formatTime',
              types: {
                datetime: {
                  show: true
                }
              }
            },
            tb_update_time: {
              name: '更新时间',
              width: '120',
              filter: 'formatTime',
              types: {
                datetime: {
                  show: true
                }
              }
            },
            _options: {
              name: '选项',
              width: '200',
              buttons: true
            }
          },
          fields: {

          },
          paging: {
          	show: true,

          }
        }
      }
    }
  }
</script>

