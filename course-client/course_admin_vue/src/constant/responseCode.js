/**
 * Created by wenshao on 2017/10/21.
 * 响应code对应的动作
 */

// 301:没有token 302:token数据库连接失败 303:Token 过期了
// 304:token数据异常 305:非法的token 306:其他客户端登录了307:token信息丢失
const storeFedLogOut = {
  action: 'store',
  name: 'FedLogOut'
}
const unknown = {
  action: 'unknown',
  name: 'unknown'
}
const alert = {
  action: 'alert',
  name: ''
}

const codeObject = {
  301: storeFedLogOut,
  303: storeFedLogOut,
  304: storeFedLogOut,
  305: storeFedLogOut,
  306: storeFedLogOut,
  307: storeFedLogOut,
  4: alert,
  102: alert,
  211: alert,
  105: alert

  // 301:没有token 302:token数据库连接失败 303:Token 过期了
  // 304:token数据异常 305:非法的token 306:其他客户端登录了
}

export function getAction(code) {
  if (code in codeObject) {
    return codeObject[code]
  } else {
    return unknown
  }
}

