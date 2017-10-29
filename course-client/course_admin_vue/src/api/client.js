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
export function clientIdVersionQuery(cid, params) {
  return fetch({
    url: '/clients/' + cid + '/versions',
    method: 'get',
    params: params
  })
  // return fetch({
  //   url: '/versions',
  //   method: 'get',
  //   params: params
  // })
}
export function versionQuery(params) {
  return fetch({
    url: '/versions',
    method: 'get',
    params: params
  })
}
export function clientQueryPattern(pid) {
  return fetch({
    url: '/clients/pattern/' + pid,
    method: 'get'
  })
}
export function versionUpdate(params) {
  return fetch({
    url: '/versions/' + params.id,
    method: 'put',
    data: params
  })
}
export function versionInsert(params) {
  return fetch({
    url: '/versions',
    method: 'post',
    data: params
  })
}
