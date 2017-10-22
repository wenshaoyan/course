import axios from 'axios'
import store from '@/store'
import { getToken } from '@/utils/auth'
import { getAction } from '@/constant/responseCode'
import Vue from 'vue'

// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API, // api的base_url
  timeout: 5000                  // 请求超时时间
})

// request拦截器
service.interceptors.request.use(config => {
  // Do something before request is sent
  if (store.getters.token) {
    config.headers['X-Token'] = getToken() // 让每个请求携带token--['X-Token']为自定义key 请根据实际情况自行修改
  }
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
  response => {
    /**
     * 下面的注释为通过response自定义code来标示请求状态，当code返回如下情况为权限有问题，登出并返回到登录页
     * 如通过xmlhttprequest 状态码标识 逻辑可写在下面error中
     */
    const res = response.data
    if (!res.success) {
      const actionObject = getAction(res.code)
      if (!actionObject || actionObject.action === 'unknown') {
        Vue.prototype.$message({
          message: '请求失败',
          type: 'error',
          duration: 3 * 1000
        })
      } else if (actionObject.action === 'alert') {
        Vue.prototype.$message({
          message: res.message,
          type: 'error',
          duration: 5 * 1000
        })
      } else if (actionObject.action === 'store') {
        store.dispatch(actionObject.name).then(() => {
          // 为了重新实例化vue-router对象 避免bug
          location.reload()
        })
      }

      return Promise.reject('error')
    } else {
      return response.data.data
    }
  },
  error => {
    console.log('err' + error)// for debug
    Vue.prototype.$message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
