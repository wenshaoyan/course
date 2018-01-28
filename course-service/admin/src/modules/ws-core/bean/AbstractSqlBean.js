/**
 * 继承AbstractSql 主要为把容易编写的json查询对象 转换为thrift查询
 * 1 为每个查询字段创建where对象  如 {id:{like:'1'}} => Where{id:{like:'1'}}
 * 2 规范数据类型 通过thrift查询的值只能为string类型(ne eq 等) 或者数组的string类型(like between)
 */
'use strict';
const {Where, AbstractSql} = require('../gen-nodejs/bean_types');
const field = require('../config/thrift-field.json');
class AbstractSqlBean extends AbstractSql {
    constructor(args) {
        // args.where = {field1: { eq: '1'}}
        if (args && args.where && typeof args.where === 'object') {
            // key=field1
            for (const key in args.where) {
                // 类型转换
                for (const type in args.where[key]) {
                    if (!(type in field)) {
                        continue;
                    }
                    const o = field[type];
                    let v = args.where[key][type];
                    if (o.type === 'string' && !o.isList) { // 只是字符串类型
                        if (typeof v === 'number') {
                            v += '';
                        } else if (typeof v === 'string') {

                        } else {
                        }
                    } else if (o.type === 'string' && o.isList) {   // 数组的字符串类型
                        if (v instanceof Array){
                            const newValue = [];
                            for (const value of v) {
                                if (typeof value === 'number') {
                                    newValue.push(value + '');
                                } else if (typeof value === 'string') {
                                    newValue.push(value);
                                } else {
                                    throw new Error(`AbstractSqlBean.where.${key}.${type} type error, type only for number | string`);
                                }
                            }
                            v = newValue;
                        } else if (typeof v === 'number') {
                            v += '';
                            v = [v];
                        } else if (typeof v === 'string') {
                            v = [v];
                        } else {
                            throw new Error(`AbstractSqlBean.where.${key}.${type} type error, type only for number | string | list.number | list.string`);
                        }
                    } else if (o.type === 'number' && !o.isList) { // 只是数字类型
                        if (isNaN(v)) {
                            throw new Error(`AbstractSqlBean.where.${key}.${type} type error, type only for number | number(string) `);
                        }
                        v = +v;
                    } else if (o.type === 'number' && !o.isList) { // 数组的数字类型
                        if (v instanceof Array){
                            const newValue = [];
                            for (const value of v) {
                                if (typeof value === 'number') {
                                    newValue.push(value);
                                } else if (typeof value === 'string' && !isNaN(v)) {
                                    newValue.push(+value);
                                } else {
                                    throw new Error(`AbstractSqlBean.where.${key}.${type} type error, type only for number | string`);
                                }
                            }
                            v = newValue;
                        } else if (typeof v === 'number') {
                            v = [v];
                        } else if (typeof v === 'string' && !isNaN(v)) {
                            v = [+v];
                        } else {
                            throw new Error(`AbstractSqlBean.where.${key}.${type} type error, type only for number | string | list.number | list.string`);
                        }
                    }
                    args.where[key][type] = v;


                }
                // 将json对象转Where对象
                args.where[key] = new Where(args.where[key]);

            }
        }
        super(args);
    }
}
module.exports = AbstractSqlBean;