/**
 * Created by wenshao on 2017/9/9.
 * 工具类
 */
'use strict';

class Utils{
    // 任意对象的的深拷贝 可以复制复杂对象
    static deepCopy(source,target){
        const obj = target || {};
        for (let key in source) {
            const currentObj = source[key];
            if (obj === currentObj) {
                continue;
            }
            if (typeof currentObj === 'object') {
                obj[key] = currentObj instanceof Array ? [] : {};
                this.deepCopy(currentObj,obj[key]);
            } else {
                obj[key] = currentObj;
            }
        }
        return obj;
    }

}
module.exports = Utils;
