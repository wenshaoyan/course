/**
 * Created by wenshao on 2017/9/16.
 * 系统工具类
 */
'use strict';
const crypto = require('crypto');
const uuid = require('node-uuid');
const fs = require('fs');
const path = require('path');
class SysUtil {
    static md5(str) {
        return crypto.createHash('md5').update(str).digest('hex');
    }

    static getUuid() {
        return uuid.v4().replace(/-/g, '');
    }
    /**
     *
     * @param startPath  起始目录文件夹路径
     * @returns {Array}
     */
    static loadDirFiles(startPath) {
        let result = [];

        function finder(p) {
            let files = fs.readdirSync(p);
            files.forEach((val, index) => {
                let fPath = path.join(p, val);
                let stats = fs.statSync(fPath);
                if (stats.isDirectory()) finder(fPath);
                if (stats.isFile()) result.push(path.resolve(fPath));
            });
        }

        finder(startPath);
        return result;
    }

    /**
     * 正常合并目录下的js文件 不分先后
     * @param dir
     */
    static normalMergeDirMethod(dir) {
        const list = SysUtil.loadDirFiles(dir);
        const data = {};
        for (const file of list) {
            const f = require(file);
            if (f && typeof f === 'object') {
                Object.keys(f).forEach(key => {
                    if (key in data) {
                        throw new Error(`normalMergeDirMethod:${key} is exist`);
                    }
                    data[key] = f[key];
                })
            }
        }
        return data;
    }

    /**
     * 正常合并目录下所有文件为一个文件 不分先后
     * @param dir
     * @return {string}
     */
    static normalMergeDirFile(dir) {
        const list = SysUtil.loadDirFiles(dir);
        let data = '';
        for (const file of list) {
            data += fs.readFileSync(file, 'utf8')

        }
        return data;
    }
}
module.exports = SysUtil;