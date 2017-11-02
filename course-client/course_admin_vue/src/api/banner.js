import fetch from '@/utils/fetch'

/**
 * Created by wenshao on 2017/10/22.
 * 轮播图相关接口
 */
export function bannerQuery(query) {
  return fetch({
    url: '/banners',
    method: 'get',
    params: query
  })
}

export function bannerInsert(data) {
  return fetch({
    url: '/banners',
    method: 'post',
    data: data
  })
}
export function bannerPut(id, data) {
  return fetch({
    url: '/banners' + id,
    method: 'put',
    data: data
  })
}
export function bannerPatch(id, data) {
  return fetch({
    url: '/banners/' + id,
    method: 'patch',
    params: data
  })
}
