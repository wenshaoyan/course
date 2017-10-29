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
    "limit": schema_public.limit,
    "sort_by": schema_public.sort_by,
    "order": schema_public.order
};
const extend = function (d1, d2) {
    const obj = {};
    for (let k in d2) {
        obj[k] = d2[k];
    }
    for (let k in d1) {
        obj[k] = d1[k];
    }
    return obj;
};
module.exports = {
    "userQuery": {
        "type": "object",
        "properties": extend({
            "tel": schema_public.tel,
            "id": schema_public.idString,
            "name": schema_public.string225
        }, query)
    },
    "userInsert": {
        "type": "object",
        "properties": {
            "name": schema_public.name,
            "tel": schema_public.tel,
            "device_uuid": schema_public.string225,
            "password": schema_public.string225,
        },
        "required": ["name", "tel", "device_uuid", "password"]
    },
    "managementLogin": {
        "type": "object",
        "properties": {
            "tel": schema_public.tel,
            "password": schema_public.string225,
        },
        "required": ["tel", "password"]
    },
    "clientQuery": {
        "type": "object",
        "properties": extend({
            "id": schema_public.idString,
            "package_name": schema_public.string225
        }, query)
    },
    "bannerQuery": {
        "type": "object",
        "properties": extend({
            "id": schema_public.idString,
            "package_name": schema_public.string225
        }, query)
    },
    "bannerInsert": {
        "type": "object",
        "properties": extend({
            "id": schema_public.idString,
            "image_url": schema_public.string225,
            "redirect_url": schema_public.string225,
            "show_client_id": schema_public.num
        }, query)
    },
    "clientInsert": {
        "type": "object",
        "properties": {
            "name": schema_public.string225NotNull,
            "package_name": schema_public.string225NotNull
        },
        "required": ["name", "package_name"]
    },
    "clientUpdate": {
        "type": "object",
        "properties": {
            "name": schema_public.string225NotNull,
            "package_name": schema_public.string225NotNull,
            "id": schema_public.idString
        },
        "required": ["name", "package_name"]
    },
    "versionQuery": {
        "type": "object",
        "properties": extend({
            "id": schema_public.idString,
            "version_name": schema_public.string225NotNull,
            "client_id": schema_public.idString
        }, query)
    },
    "versionInsert": {
        "type": "object",
        "properties": {
            "version_name": schema_public.string225NotNull,
            "version_number": schema_public.num,
            "download_url": schema_public.string225NotNull,
            "client_id": schema_public.id,
            "description": schema_public.string225
        },
        "required": ["version_name", "version_number", "download_url", "client_id"]
    },
    "versionUpdate": {
        "type": "object",
        "properties": {
            "version_name": schema_public.string225NotNull,
            "version_number": schema_public.num,
            "download_url": schema_public.string225NotNull,
            "client_id": schema_public.id,
            "description": schema_public.string225
        },
        "required": ["client_id"]
    }
};