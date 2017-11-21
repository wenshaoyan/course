local upload = require "resty.upload"
local cjson = require "cjson"
local httpUri=ngx.var.uri;

local chunk_size = 40960  
local form = upload:new(chunk_size)  
local file  
local filelen=0


local intDatas = {}
local params = {}
local tempParams 

local osfilepath = "/home/wenshao/data/nginx/image/"
local fileAddr="http://123.207.55.204:8083/image/"
local ext = "png"
if httpUri == "/upload/audio" then
    osfilepath= "/home/wenshao/data/nginx/audio/"
    fileAddr = "http://123.207.55.204:8083/audio/"
    ext = "amr"
elseif httpUri == "/upload/apk" then
    osfilepath= "/home/wenshao/data/nginx/apk/"
    fileAddr = "http://123.207.55.204:8083/apk/"
    ext = "apk"
end

if form == nil then
    ngx.say([[{
        "success":false,
        "message":"请使用post上传",
        "code":310,
        "en_message":"Please use post",
        "error_source":"upload",
        "data":[]
      }]])
    return 
end
form:set_timeout(0) -- 1 sec  



function split(s, delim)
    if type(delim) ~= "string" or string.len(delim) <= 0 then
        return
    end

    local start = 1
    local t = {}
    while true do
    local pos = string.find (s, delim, start, true) -- plain find
        if not pos then
          break
        end

        table.insert (t, string.sub (s, start, pos - 1))
        start = pos + string.len (delim)
    end
    table.insert (t, string.sub (s, start))

    return t
end

-- 获取文件名
function getFileName(str)
    local idx = str:match(".+()%.%w+$")
    if(idx) then
        return str:sub(1, idx-1)
    else
        return str
    end
end

-- 获取扩展名
function getExtension(str)
    return str:match(".+%.(%w+)$")
end


local filename 
local sendCod
-- 获取上传的文件名称
function get_filename(res)
    local filename = ngx.re.match(res,'(.+)filename="(.+)"(.*)')  
    if filename then   
        return filename[2]  
    end  
    
end  
math.randomseed( tonumber(tostring(os.time()):reverse():sub(1,6)) )
-- 生产文件随机名称
function getRandFilename(res) 
    -- 获取文件名称
    local name = ngx.re.match(res,'(.+)filename="(.+)"(.*)')   
   
    -- 生产文件名称随机数
    local rand = math.random();
    local randFileName = os.time().."_"..rand;

    -- 获取和文件名称一起的参数值
    local t= ngx.re.match(res,'(.+); name="(.+)"; (.*)')
    if t and t[2] then 
        local p = split(t[2],"&")
        if p then 
            local j
            for j=1,#p do
                local str = split(p[j],"=")
                if str and #str==2 then 
                    params[str[1]]=str[2]
                end
            end
        end
    end
    if name and #name>1 then
        -- 自动获取文件后缀名
        -- local ext = getExtension(name[2])
        ngx.log(ngx.ALERT,ext)
        if ext then 
            return randFileName.."."..ext
        end
       
    end
    

    return nil
end  
function getParams(res)
    local name = ngx.re.match(res,'(.+)name="(.+)"(.*)')
    if name and #name>2 then
        return name[2]
    end
end


local i=0  
while true do  
    local typ, res, err = form:read()  
    if not typ then  
        ngx.say([[{
            "success":false,
            "message":"文件读取失败",
            "code":311,
            "en_message":"file read error",
            "error_source":"upload",
            "data":[]
          }]])
        return  
    end

    if typ == "header" then  
        if res[1] ~= "Content-Type" then  
            
            filename = getRandFilename(res[2])

            if filename then  
                i=i+1  
                filepath = osfilepath  .. filename  
                file = io.open(filepath,"w+")  
                if not file then  
                    ngx.say([[{
                        "success":false,
                        "message":"文件打开失败",
                        "code":312,
                        "en_message":"failed to open file",
                        "error_source":"upload",
                        "data":[]
                      }]])
                    return  
                end
          
            end  
        end  
    elseif typ == "body" then
        if file then  
            -- ngx.log(ngx.ALERT,i.."---------"..filename)

            filelen= filelen + tonumber(string.len(res))      
            file:write(res) 
        end  
    elseif typ == "part_end" then  
        if file then  
            ngx.log(ngx.ALERT,i.."========"..filename)
            file:close()  
            file = nil
            
            intDatas[i]={}
            intDatas[i]["file_path"]=fileAddr..filename
          
            if params["sendCode"] then 
                intDatas[i]["sendCode"]=params["sendCode"]
            end
                    end  
    elseif typ == "eof" then  
        local result = cjson.decode([[{
            "success":true,
            "message":"success",
            "code":0,
            "en_message":"success ",
            "error_source":"success"
          }]])
        result["data"] = intDatas
        ngx.say(cjson.encode(result))
        return  
    else
    end  
end  
if i==0 then 
    ngx.say([[{
        "success":false,
        "message":"上传异常",
        "code":313,
        "en_message":"upload Exception",
        "error_source":"upload",
        "data":[]
      }]])
    return  
end  