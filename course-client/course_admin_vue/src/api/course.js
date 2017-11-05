import fetch from '@/utils/fetch'
/**
 * Created by wenshao on 2017/11/5.
 * 课程
 */
export function courseQuery(query) {
  return fetch({
    url: '/courses',
    method: 'get',
    params: query
  })
}
