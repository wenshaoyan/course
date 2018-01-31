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

function setLog(logSocket,response, level) {
    logSocket[level]("===================");
    logSocket[level]('\n',response);
    logSocket[level]("===================");


}

function func(opt) {
    let source = {};
    let isSetSuccessLog = false;
    let isSetFailLog = false;
    let isSetUnknownLog = false;
    let isSetErrorLog = false;
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
        if (opt.unknownLog && typeof opt.unknownLog === 'object') {
            isSetUnknownLog = true;
        }
        if (opt.errorLog && typeof opt.errorLog === 'object') {
	        isSetErrorLog = true;
        }
    }

    return async function (ctx, next) {
	    try {
		    await next();
        }catch (e) {
		    ctx.error = e;
        }
        if (!ctx.error && 'body' in ctx && ctx.body !== null && ctx.body !== undefined) {
            const result = {
                success: true,
                data: ctx.body,
                message: "success",
                code: 0,
                error_source:"success",
                en_message:"success"
            };
            ctx.body = result;
            if (isSetSuccessLog) setLog(opt.successLog, result, 'debug');

            return
        }
        if (ctx.error) {
	        if (isSetErrorLog) setLog(opt.errorLog, ctx.error, 'error');
            const messageObject = {
                success: false,
                data: []
            };
            const errorValue = ctx.error;
            if (errorValue in source) {
                copyObjectAttr(messageObject, source[errorValue]);
                ctx.body = messageObject;
                if (isSetFailLog) setLog(opt.failLog,messageObject, 'error');
                return;
            }
            if (typeof errorValue !== 'object' && 'UNKNOWN_ERROR' in source) {
                copyObjectAttr(messageObject, source['UNKNOWN_ERROR']);
                ctx.body = messageObject;
                if (isSetFailLog) setLog(opt.failLog,messageObject, 'error');
                return;
            }
            const name = errorValue.name;
            if (name  === 'TimeoutException' && 'THRIFT_CONNECT_ERROR' in source) {
	            copyObjectAttr(messageObject, source['THRIFT_CONNECT_ERROR']);
	            ctx.body = messageObject;
	            if (isSetFailLog) setLog(opt.failLog,messageObject, 'error');
	            return;
            }
            if ((name === 'RequestException' || name === 'TApplicationException')&& 'THRIFT_ASK_EXCEPTION' in source) {
                copyObjectAttr(messageObject, source['THRIFT_ASK_EXCEPTION']);
                ctx.body = messageObject;
                if (isSetFailLog) setLog(opt.failLog,messageObject, 'error');
                return;
            }
            if (typeof errorValue === 'object'){
                copyObjectAttr(messageObject, source['UNKNOWN_ERROR']);
                ctx.body = messageObject;
                if (isSetUnknownLog) setLog(opt.unknownLog,errorValue, 'error');
            }
        }
    }
}

module.exports = func;
