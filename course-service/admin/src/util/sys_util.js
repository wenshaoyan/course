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
}
module.exports = SysUtil;