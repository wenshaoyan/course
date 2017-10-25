import fetch from '@/utils/fetch'

/**
 * Created by wenshao on 2017/10/22.
 */
export function clientQuery() {
  return fetch({
    url: '/clients',
    method: 'get'
  })
}
export function versionQuery(query) {
  return fetch({
    url: '/versions',
    method: 'get',
    query: query
  })
}
