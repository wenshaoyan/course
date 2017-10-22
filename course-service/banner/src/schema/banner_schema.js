/**
 * Created by yanshaowen on 2017/9/30
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
    "banner_insert":{
        "type": "object",
        "properties":{
            "image_url":schema_public.string225,
            "redirect_url":schema_public.string225,
            "show_client_id":schema_public.id
        },
        "required": ["image_url","redirect_url","show_client_id"]
    },
    "banner_id":{
        "type": "object",
        "properties":{
            "id":schema_public.idString
        },
        "required": ["id"]
    },
    "banner_update":{
        "type": "object",
        "properties":{
            "id":schema_public.idString,
            "image_url":schema_public.string225,
            "redirect_url":schema_public.string225,
        },
        "required": ["id"]
    },
    "banner_filter":{
        "type": "object",
        "properties":{
            "packageName":schema_public.idString,
            "image_url":schema_public.string225,
            "redirect_url":schema_public.string225,
        },
        //"required": []
    }
};