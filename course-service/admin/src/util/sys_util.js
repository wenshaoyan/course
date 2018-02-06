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
    static mergeDirSchema(dir) {
        const list = SysUtil.loadDirFiles(dir);
        const Queries = {}, Mutations = {};
        for (const file of list) {
            const f = require(file);
            if (f.Queries && typeof f.Queries === 'object') {
                Object.keys(f.Queries).forEach(key => {
                    if (key in Queries) {
                        throw new Error(`mergeDirSchema:${key} is exist`);
                    }
                    Queries[key] = f.Queries[key];
                })
            }
            if (f.Mutations && typeof f.Mutations === 'object') {
                Object.keys(f.Mutations).forEach(key => {
                    if (key in Mutations) {
                        throw new Error(`mergeDirSchema:${key} is exist`);
                    }
                    Mutations[key] = f.Mutations[key];
                })
            }


        }
        return {Queries, Mutations};
    }
}
module.exports = SysUtil;