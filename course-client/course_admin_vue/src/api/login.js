import fetch from '@/utils/fetch'

export function login(tel, password) {
  const data = {
    tel,
    password
  }
  return fetch({
    url: '/management/login',
    method: 'get',
    params: data
  })
}

export function logout() {
  return fetch({
    url: '/login/logout',
    method: 'post'
  })
}

export function getUserInfo(token) {
  return fetch({
    url: '/management/token',
    method: 'get'
  })
}

