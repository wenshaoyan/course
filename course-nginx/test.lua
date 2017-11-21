local cjson = require "cjson"
local resmsg ={}
local intDatas = {};
intDatas[1]={}
intDatas[2]={}
intDatas[1]["a"]="1"
intDatas[2]["b"]="2"
resmsg["int_datas"]=intDatas

ngx.say(cjson.encode(resmsg))
