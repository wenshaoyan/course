/**
 * Created by wenshao on 2018/1/27.
 * 格式化查询参数
 */
'use strict';
const queryField = require('../config/query-field.json');
const thriftField = require('../config/thrift-field.json');

/**
 * 把字符对象转换为对象
 * @params d          转换的字符对象
 * @return {boolean}  转换失败为false  成功是该对象
 */
const jsonParse = (d) => {
    try {
        return JSON.parse(d);
    } catch (e) {
        return false;
    }
};
const format = (params, opts) => {
    const data = {};
    const where = {};
    const reLastStr = /,$/;
    for (const key in queryField) {
        if (key in params) {
            const result = jsonParse(params[key]);
            let operator = queryField[key]['operator'];
            if (!result) continue;
            if (thriftField[operator]['parent'] === 'where') {    // where对象
                for (const field in result) {
                    if (field.lastIndexOf(opts.suffixNotUseLike) !== -1 && key === '_search') {
                        operator = 'eq';
                    }
                    if (field in where) {
                        where[field][operator] = result[field];
                    } else {
                        where[field] = {};
                        where[field][operator] = result[field];
                    }
                }
            } else if (thriftField[operator]['parent'] === 'bean') {  // 根对象  order group limit
                if (operator === 'order') { // result={ "tb_id": 1, "tb_name": 3 } value越小 优先级越高 并且value为奇数为asc 偶数 desc
                    let order = '';
                    const list = [];
                    for (const field in result) {   // 排序后list=[ , 'tb_id', , 'tb_name' ]
                        if (!isNaN(result[field])) {
                            list[+(result[field])] = field;
                        }
                    }
                    for (const i in list) {
                        const field = list[i];
                        order += ` ${field}  ${i % 2 ? 'asc' : 'desc'} ,`;  // 拼接 order sql
                    }
                    order = order.replace(reLastStr, '');    // 去除结尾的,
                    if (order.length > 0) data.order = order;
                } else if (operator === 'group') {

                } else if (operator === 'limit') {
                    data.limit = result;
                }
            }
        }
    }
    data.where = where;
    return data;
};
function formatQuery(opts) {
    let suffixNotUseLike = '_id';
    if (!opts.suffixNotUseLike || typeof opts.suffixNotUseLike !== 'string') {
        opts.suffixNotUseLike = suffixNotUseLike;
    }

    return async(ctx, next) => {
        let data = {};
        if (ctx.query) {
            data = Object.assign(data, format(ctx.query, opts));
        }
        ctx.querySql = data;
        await next();
    }

}
module.exports = formatQuery;