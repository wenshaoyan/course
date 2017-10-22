/**
 * Created by yanshaowen on 2017/10/17
 * 用户路由参数验证
 */
'use strict';
const schema_public = require('./schema_public');
module.exports = {
    "user_query": {
        "type": "object",
        "properties": {
            "tel": schema_public.tel,
            "id": schema_public.string225,
            "name": schema_public.idString,
            "register_time": schema_public.idString

        }
    },
    "user_insert": {
        "type": "object",
        "properties": {
            "name": schema_public.name,
            "tel": schema_public.tel,
            "device_uuid": schema_public.string225,
            "password": schema_public.string225,
        },
        "required": ["name", "tel", "device_uuid", "password"]
    },
    "management_login": {
        "type": "object",
        "properties": {
            "tel": schema_public.tel,
            "password": schema_public.string225,
        },
        "required": ["tel", "password"]
    }
};