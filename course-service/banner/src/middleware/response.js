/**
 * Created by yanshaowen on 2017/9/29
 * 检查对应的thrift状态和规范响应数据
 */
'use strict';

function routerLog(opt) {
    let source = {};
    if (typeof opt === 'object') source = opt;
    return async function (ctx, next) {
        await next();
        if (ctx.body) {
            ctx.body = {
                success: true,
                data: ctx.body,
                message: "success",
                code: 0
            };
        } else if (ctx.error) {
            if (ctx.error in source) {
                ctx.body = {
                    success: false,
                    message: source[ctx.error].message,
                    source: source[ctx.error].source,
                    code: source[ctx.error].code,
                    data: [],
                };
            } else if (typeof ctx.error === 'object') {
                ctx.body = {
                    success: false,
                    message: ctx.error.message,
                    source: ctx.error.source,
                    code: 1,
                    data: []
                };
            }

        }
    }
}

module.exports = routerLog;
