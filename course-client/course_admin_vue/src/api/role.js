import fetch from '@/utils/fetch'

/**
 * Created by wenshao on 2017/10/22.
 */
export function roleQuery() {
  return fetch({
    url: '/roles',
    method: 'get'
  })
}
