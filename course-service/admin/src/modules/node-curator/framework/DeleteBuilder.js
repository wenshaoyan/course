/**
 * Created by wenshao on 2017/9/10.
 */
'use strict';
const BasicBuilder = require('./BasicBuilder');
const api = require('./../Api');

class DeleteBuilder extends BasicBuilder{
    constructor(conn,namespace){
        super(conn,namespace);
    }

    forPath(path){
        return api.remove(this.conn,this.isNamespace?this.namespace+path:path);
    }


}
module.exports = DeleteBuilder;
