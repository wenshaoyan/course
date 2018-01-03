/**
 * Created by wenshao on 2017/9/8.
 */
'use strict';
const logger = require('./logger');
const Configuration = require('./configuration');

// appenders [file,dateFile
const defaultConf = {
    appenders: {
        console: { type: 'console' }
    },
    categories: {
        default: { appenders: ['console'], level: 'trace' }
    }
};
let configuration = new Configuration(defaultConf);

class Log{

    static getLogger(name){
        return logger;
    }
    static configure(conf) {
        //if (typeof conf !== 'object') conf = defaultConf;
        configuration = new Configuration(defaultConf);

    }
}

module.exports = Log;