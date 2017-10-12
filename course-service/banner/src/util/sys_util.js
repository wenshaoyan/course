/**
 * Created by wenshao on 2017/9/16.
 * 系统工具类
 */
'use strict';
const crypto = require('crypto');

class SysUtil{
    static md5(str){
        return crypto.createHash('md5').update(str).digest('hex');
    }

    /**
     * 拷贝对象的属性
     * @param des   目标对象
     * @param src   源对象
     */
    static copyObjectAttr(des,src){
        if (typeof des === 'object' && typeof src === 'object'){
            for (let key in src){
                des[key] = src[key];
            }
        }
    }

    /**
     * 递归深拷贝
     * @param target    目标对象
     * @param source    源对象
     * @return {*|{}}
     */
    static deepClone(target,source) {
        const obj = target || {};
        for (let key in source) {
            const currentObj = source[key];
            if (obj === currentObj) {
                continue;
            }
            if (typeof currentObj === 'object') {
                obj[key] = currentObj instanceof Array ? [] : {};
                this.deepClone(currentObj,obj[key]);  //
            } else {
                obj[key] = currentObj;
            }
        }
        return obj;
    }

}
module.exports =SysUtil;