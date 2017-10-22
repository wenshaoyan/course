/**
 * Created by yanshaowen on 2017/10/17
 * 用户路由参数验证
 */
'use strict';
const schema_public = require('./schema_public');
const query = {
    "create_time_start": schema_public.idString,
    "create_time_end": schema_public.idString,
    "update_time_start": schema_public.idString,
    "update_time_end": schema_public.idString,
    "page": schema_public.page,
    "per_page": schema_public.per_page,
    "sortby": schema_public.sortby,
    "order": schema_public.order
};
const extend = function (d1, d2) {
    const obj = {};
    for (let k in d2){
        obj[k] = d2[k];
    }
    for (let k in d1){
        obj[k] = d1[k];
    }
    return obj;
};
module.exports={
    "user_login":{
        "type": "object",
        "properties":{
            "tel":schema_public.tel,
            "password":schema_public.string225,
        },
        "required": ["tel","password"]
    },
    "user_insert":{
        "type": "object",
        "properties":{
            "name":schema_public.name,
            "tel":schema_public.tel,
            "device_uuid":schema_public.string225,
            "password":schema_public.string225,
        },
        "required": ["name","tel","device_uuid","password"]
    }
};