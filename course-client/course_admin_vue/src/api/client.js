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
export function clientInsert(params) {
  return fetch({
    url: '/clients',
    method: 'post',
    data: params
  })
}
export function clientUpdate(params) {
  return fetch({
    url: '/clients/' + params.id,
    method: 'put',
    data: params
  })
}
export function clientIdVersionQuery(cid, query) {
  return fetch({
    url: `/clients/${cid}/versions`,
    method: 'get',
    query: query
  })
}
export function versionQuery(query) {
  return fetch({
    url: '/versions',
    method: 'get',
    query: query
  })
}
