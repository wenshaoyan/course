/**
 * Created by yanshaowen on 2017/9/29
 * 检查对应的thrift状态和规范响应数据
 */
'use strict';


function copyObjectAttr(des, src) {
    if (typeof des === 'object' && typeof src === 'object') {
        for (let key in src) {
            des[key] = src[key];
        }
    }
}

function setLog(logSocket,response) {
    logSocket.info("===================");
    logSocket.info('\n',response);
    logSocket.info("===================");


}

function func(opt) {
    let source = {};
    let isSetSuccessLog = false;
    let isSetFailLog = false;
    if (opt && typeof opt === 'object') {
        if (opt.jsonFile && typeof opt.jsonFile === 'object') {
            source = opt.jsonFile;
        }
        if (opt.successLog && typeof opt.successLog === 'object') {
            isSetSuccessLog = true;
        }
        if (opt.failLog && typeof opt.failLog === 'object') {
            isSetFailLog = true;
        }
    }

    return async function (ctx, next) {
        await next();
        if ('body' in ctx) {
            const result = {
                success: true,
                data: ctx.body,
                message: "success",
                code: 0,
                error_source:"success",
                en_message:"success"
            };
            ctx.body = result;
            if (isSetSuccessLog) setLog(opt.successLog,result);

            return
        }
        if (ctx.error) {
            const messageObject = {
                success: false,
                data: []
            };
            const errorValue = ctx.error;
            if (errorValue in source) {
                copyObjectAttr(messageObject, source[errorValue]);
                ctx.body = messageObject;
                if (isSetFailLog) setLog(opt.failLog,messageObject);

                return;
            }
            if (typeof errorValue !== 'object' && 'UNKNOWN_ERROR' in source) {
                copyObjectAttr(messageObject, source['UNKNOWN_ERROR']);
                ctx.body = messageObject;
                if (isSetFailLog) setLog(opt.failLog,messageObject);
                return;
            }
            if (errorValue.name === 'TApplicationException' && 'THRIFT_ASK_EXCEPTION' in source) {
                copyObjectAttr(messageObject, source['THRIFT_ASK_EXCEPTION']);
                ctx.body = messageObject;
                if (isSetFailLog) setLog(opt.failLog,messageObject);
                return;
            }
            if (typeof errorValue === 'object'){
                copyObjectAttr(messageObject, source['UNKNOWN_ERROR']);
                ctx.body = messageObject;
                if (isSetFailLog) setLog(opt.failLog,messageObject);
            }


        }
    }
}

module.exports = func;
