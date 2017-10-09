/**
 * Created by yanshaowen on 2017/9/30
 */
'use strict';
const schema_public = require('./schema_public');
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