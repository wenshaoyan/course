/**
 * Created by yanshaowen on 2017/9/30
 */
'use strict';
const schema_public = require('./schema_public');
module.exports={
    "banner_insert":{
        "$async": true,
        "type": "object",
        "properties":{
            "image_url":schema_public.string225,
            "redirect_url":schema_public.string225,
            "show_client_id":schema_public.id
        },
        "required": ["image_url","redirect_url","show_client_id"]
    }
};