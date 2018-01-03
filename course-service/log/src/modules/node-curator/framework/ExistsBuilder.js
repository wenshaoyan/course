/**
 * Created by wenshao on 2017/9/10.
 */
'use strict';
const BasicBuilder = require('./BasicBuilder');
const api = require('./../Api');

class ExistsBuilder extends BasicBuilder{
    constructor(conn,namespace){
        super(conn,namespace);
    }

    forPath(path){
        return api.exists(this.conn,this.isNamespace?this.namespace+path:path);
    }


}
module.exports = ExistsBuilder;
