/**
 * Created by wenshao on 2017/9/8.
 * 参数检验以及生产标准参数
 */
'use strict';
class Configuration{

    static castError(message){
        throw new Error(message);

    }

    static checkConf(conf){
        if (!("appenders" in conf)) this.castError('appenders not in configure');
        if (!("categories" in conf)) this.castError('categories not in configure');
        if (!("default" in conf.categories)) this.castError('categories not in configure');
    }
    constructor(conf){
        Configuration.checkConf(conf);
    }

}
module.exports = Configuration;
