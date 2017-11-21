local request_method = ngx.var.request_method
local args =nil
local defaultConfig='{"code":0,"service":"http://192.168.1.103:3000","webSocket":"ws://192.168.1.103:3001"}'

local dev_addr='{"code":0,"service":"http://192.168.1.103:3000","webSocket":"ws://192.168.1.103:3001"}'
local pro_addr='{"code":0,"service":"http://123.207.55.204:3000","webSocket":"ws://123.207.55.204:3001"}'

local conf={["com.wenshao.chat:1.0.0"] = pro_addr,["com.wenshao.chat:0.0.1"] = dev_addr}

local function respone(config)
    if type(config) == "string" then 
        ngx.say(config)
    else
        ngx.say(defaultConfig)
    end
end


local redis = require "resty.redis_iresty"
local red = redis:new({
    port = 6379,
    password = "wenshao314106"
})
local pingResult = red:ping()
if pingResult == nil then
    respone(defaultConfig)
    return;
end



if "GET" == request_method then
    args = ngx.req.get_uri_args()
elseif "POST" == request_method then
    ngx.req.read_body()
    args = ngx.req.get_post_args()
end

local package = args.package
local version = args.version


if type(package) ~= "string" or type(version) ~= "string" then
    respone(defaultConfig)
    return
end
local key = package..":"..version

if key == nil then
    respone(defaultConfig)
elseif conf[key] == nil then
    respone(defaultConfig)
else 
    respone(conf[key])
end

  


--[[  
local ok, err = red:get("dog")
if not ok then
    ngx.say("failed to set dog: ", err)
    return
end
ngx.say(ok)
--]] 
