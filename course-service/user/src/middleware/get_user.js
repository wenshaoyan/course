/**
 * Created by wenshao on 2017/9/23.
 * 从请求头获取用户信息
 */
'use strict';
const crypto = require('crypto');
const md5 = (str) => {
    return crypto.createHash('md5').update(str).digest('hex');
};
module.exports = user;

function user(opts) {
    let salt = 'wenshao';
    return async(ctx, next) => {
        if ('user-info' in ctx.header && 'user-md5' in ctx.header) {
            const md5Result = md5(salt + ctx.header['user-info']);
            if (md5Result === ctx.header['user-md5']) {
                try {
                    ctx.userInfo = JSON.parse(ctx.header['user-info']);
                } catch (e) {
                    ctx.body = {
                        "message": "身份获取失败,请重新登录",
                        "code": 305,
                        "en_message": "Please again login",
                        "error_source": "getUser"
                    };
                    return;
                }
                next();
            } else {
                ctx.body = {
                    "message": "身份获取失败,请重新登录",
                    "code": 306,
                    "en_message": "Please again login",
                    "error_source": "getUser"
                };
            }
        } else {
            if (global.NODE_ENV && global.NODE_ENV === 'develop'){
                ctx.userInfo = {id:1,name:'test'};
            }
            next();
        }
    }
}

