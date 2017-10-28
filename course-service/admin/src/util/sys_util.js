/**
 * Created by wenshao on 2017/9/16.
 * 系统工具类
 */
'use strict';
const crypto = require('crypto');
var uuid = require('node-uuid');

class SysUtil {
    static md5(str) {
        return crypto.createHash('md5').update(str).digest('hex');
    }

    static getUuid() {
        return uuid.v4().replace(/-/g, '');
    }

    static jsonArraySort(filed, rev, primer) {
        rev = (rev) ? -1 : 1;
        return function (a, b) {
            a = a[filed];
            b = b[filed];
            if (typeof (primer) != 'undefined') {
                a = primer(a);
                b = primer(b);
            }
            if (a < b) {
                return rev * -1;
            }
            if (a > b) {
                return rev * 1;
            }
            return 1;
        }
    }
    static jsonArrayFindItem(list, filed, primer, type) {
        if (list instanceof Array && typeof filed === 'string' && filed.length > 0 && list.length > 0){
            let itemIndex = 0
            let itemV = 0
            list.forEach((value, index) => {
                let tmp = value[filed]
                if (typeof (primer) !== 'undefined') {
                    tmp = primer(value[filed])
                }
                if (index === 0 ) {
                    itemIndex = index;
                    itemV = tmp
                } else {
                    if (itemV < tmp) {
                        itemV = tmp
                        itemIndex = index
                    }
                }
            })
            return itemIndex;
        }
        return null;

    }
}
module.exports = SysUtil;