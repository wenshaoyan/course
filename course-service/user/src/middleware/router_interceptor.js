/**
 * Created by yanshaowen on 2017/9/30
 * 参数检查
 */
'use strict';
const Ajv = require('ajv');
const ajv = new Ajv();
const keySet = new Set();



/**
 * 合并参数
 * @param req   请求头
 * @return {}   合并后的json
 */
const joinParams = (ctx)=>{
    const obj = {};
    const query = ctx.query;
    const params =ctx.params;
    const body = ctx.request.body;
    for(let attr in body){
        obj[attr]=body[attr];
    }
    for(let attr in query){
        obj[attr]=query[attr];
    }
    for(let attr in params){
        obj[attr]=params[attr];
    }
    return obj;
};
function func(opt) {
    if (!opt || typeof opt !== 'object') {
        throw new Error('opt not is object');
    }
    if (typeof opt.key !== 'string') {
        throw new Error(`opt.key not is string:${opt.key}`);
    }
    if (keySet.has(opt.key) && opt.schema){
        throw new Error(`opt.key exist:${opt.key}`);
    }
    if (!keySet.has(opt.key) && !opt.schema){
        throw new Error(`opt.schema not exist:${opt.key}`);
    }
    if (opt && typeof opt.schema === 'object') {
        keySet.add(opt.key);
        opt.schema['$async']=true;
        ajv.addSchema(opt.schema, opt.key);
    }
    return async function (ctx, next) {
        const paramsAll = joinParams(ctx);
        let isError = false;
        try {
            await ajv.validate(opt.key, paramsAll);

        }catch (e){
            isError = true;
            ctx.error = 'PARAMS_VALID_ERROR';
        }
        if (!isError) {
            await next();
        }


    }

}

module.exports = func;
