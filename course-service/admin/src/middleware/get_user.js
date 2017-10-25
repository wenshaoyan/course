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
    let Unchecked = [];
    if ('salt' in opts && typeof opts.salt === 'string') {
        salt = opts.salt;
    }
    if ('Unchecked' in opts && opts.Unchecked instanceof Array) {
        Unchecked = opts.Unchecked;
    } else if ('Unchecked' in opts && typeof opts.Unchecked === 'string') {
        Unchecked = [opts.Unchecked];
    }
    return async (ctx, next) => {
        const url = ctx.url;
        let isSkip = false;
        for (let value of Unchecked) {
            let num = url.indexOf(value);
            if (num !== -1) {
                isSkip = true;
                break;
            }
        }
        if (isSkip) {
            await next();
            return;
        }
        if (NODE_ENV === 'develop') {
            ctx.userInfo = {id: 1, name: 'test'};
            await next();
            return;
        }
        if ('user-info' in ctx.header && 'user-md5' in ctx.header) {
            const md5Result = md5(salt + ctx.header['user-info']);
            if (md5Result === ctx.header['user-md5']) {
                try {
                    ctx.userInfo = JSON.parse(ctx.header['user-info']);
                } catch (e) {
                    ctx.error = 'TOKEN_INFO_EXCEPTION';
                }
                await next();
            } else {
                ctx.error = 'TOKEN_INVALID';
                await next();
            }
        }else{
            ctx.error = 'TOKEN_INFO_LOSE';
            await next();
        }
    }
}

