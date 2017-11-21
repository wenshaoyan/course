local redis = require "resty.redis_iresty"
local cjson = require "cjson.safe"
local red = redis:new({
    port = 6379,
    password = "wenshao314106"
})
local sid = ngx.var.sid
local isLogin = ngx.var.isLogin
local info 
local salt = 'wenshao'
-- 判断是否有sid字段
if sid == '' then
	-- 判断是否需要强制登录
	if isLogin == '0' then
		ngx.var.result = '{}'
	else 
	    -- 提示用户重新登录
        ngx.say([[{
        "success":false,
        "message":"信息不存在,请重新登录",
        "code":301,
        "en_message":"Please again login",
        "error_source":"auth",
        "data":[]
      }]])
	end
    return
end
local pingResult = red:ping()
if pingResult == nil then
	-- 连接redis失败
    ngx.say([[{
    "success":false,
    "message":"认证繁忙",
    "code":302,
    "en_message":"server busy!",
    "error_source":"auth",
    "data":[]
  }]])
  return
end

info = red:get('auth:'..sid)
if info == nil then
    ngx.say([[{
        "success":false,
        "message":"身份获取失败,请重新登录",
        "code":303,
        "en_message":"Please again login",
        "error_source":"auth",
		"data":[]
      }]])
      ngx.log(ngx.ALERT,'身份获取失败')
      return
else
    info = cjson.decode(info)
    if info == nil then
        ngx.say([[{
        "success":false,
        "message":"数据解析异常,请重新登录",
        "code":304,
        "en_message":"data error,Please again login",
        "error_source":"auth",
        "data":[]
      }]])
    else
        info = cjson.encode(info)
        ngx.var.result = info
        ngx.var.md5Str = ngx.md5(salt..info)
    end
end
