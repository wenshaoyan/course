/**
 * Created by wenshao on 2017/10/22.
 * 版本管理
 */
'use strict';
const router = require('koa-router')();
const bean_types = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const logger = getLogger();
const clientService = getServiceConfig().dalName.client;
const routerI = require('../middleware/router_interceptor');
const AdminSchema = require('../schema/admin_schema');
router.use(async(ctx, next) => {
    const myServer = getThriftServer(clientService);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        await next();
    }
});
router.get('/', async(ctx, next) => {
    const params = ctx.query;
    const version = new bean_types.Version(params);
    const query = new bean_types.Query(params);
    const clientSer = getThriftServer(clientService).getClient();
    try {
        let count = undefined;
        let list = [];
        if (params.action === 'search') {   // 搜索动作 请求总条数
            // 查询满足条件的记录列表
            count = await clientSer.versionCountSelectQuery(version, query);
            if (count !== 0) list = await clientSer.versionSelectQuery(version, query);
        } else {    // 翻页动作 不请求总条数
            list = await clientSer.versionSelectQuery(version, query);
        }
        ctx.body = {list: list, count: count}
    } catch (e) {
        ctx.error = e;
    }
});
router.post('/test', async(ctx, next) => {
    console.log('==============')
    await p();
    ctx.body = {a:1}
})

const p =  ()=> {
    return new Promise((resolve,reject)=>{
        setTimeout(()=>{
            resolve(1)
        },10000)
    })
}

module.exports = router;
