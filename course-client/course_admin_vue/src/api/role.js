import fetch from '@/utils/fetch'

/**
 * Created by wenshao on 2017/10/22.
 */
export function roleQuery() {
  return fetch({
    url: '/role',
    method: 'get'
  })
}

export function fetchArticle() {
  return fetch({
    url: '/article/detail',
    method: 'get'
  })
}

export function fetchPv(pv) {
  return fetch({
    url: '/article/pv',
    method: 'get',
    params: { pv }
  })
}
