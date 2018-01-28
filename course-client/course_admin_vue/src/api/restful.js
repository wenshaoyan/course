import fetch from '@/utils/fetch'

/**
 * Created by wenshao on 2017/10/22.
 * restful api标准接口
 */

const getFetch = (url, data, method) => {
  const requestData = {
    url: url,
    method: method
  }
  if (method === 'get' || method === 'GET') {
    requestData.params = data
  } else {
    requestData.data = data
  }
  return fetch(requestData)
}
class restful {
  constructor(prefix) {
    this._prefix = prefix
  }

  get prefix() {
    return this._prefix
  }

  set prefix(value) {
    this._prefix = value
  }
  post(data) {
    return getFetch(this.prefix, data, 'post')
  }
  query(data) {
    return getFetch(this.prefix, data, 'get')
  }
  count(data) {
    return getFetch(this.prefix + '/counts', data, 'get')
  }
  remove(id, data) {
    return getFetch(this.prefix + '/' + id, data, 'delete')
  }
  patch(id, data) {
    return getFetch(this.prefix + '/' + id, data, 'patch')
  }
  put(id, data) {
    return getFetch(this.prefix + '/' + id, data, 'put')
  }

}
export default restful
