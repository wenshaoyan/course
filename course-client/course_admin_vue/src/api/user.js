import fetch from '@/utils/fetch'

/**
 * Created by wenshao on 2017/10/22.
 */
export function userQuery(query) {
  return fetch({
    url: '/users',
    method: 'get',
    params: query
  })
}

