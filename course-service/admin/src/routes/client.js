/**
 * Created by yanshaowen on 2017/10/25
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
// 查询客户端列表和版本列表
router.get('/', routerI({
    key:'clientQuery',
    schema:AdminSchema.clientQuery
}),async(ctx, next) => {
    const params = ctx.query;
    const clientSide = new bean_types.ClientSide(params);
    const custom = new bean_types.Custom();
    custom.tables = ['t_client_version'];
    const clientSer = getThriftServer(clientService).getClient();
    try {
        const result = await clientSer.clientSelectCustom(clientSide,custom);
        ctx.body = {
            list: result
        };
    } catch (e) {
        ctx.error = e;
    }
});
// 按模式查找 pid---  1:只查询客户端列表的数据 2 查询客户端和版本列表的数据
router.get('/pattern/:pid',routerI({
    key:'clientQuery'
}), async(ctx, next) => {
    const params = ctx.query;
    const clientSide = new bean_types.ClientSide(params);
    const clientSer = getThriftServer(clientService).getClient();
    try {
        let result;
        if (ctx.params.pid === '2'){
            const custom = new bean_types.Custom();
            custom.tables = ['t_client_version'];
            result = await clientSer.clientSelectCustom(clientSide,custom);
        } else{
            result = await clientSer.clientSelect(clientSide);
        }
        ctx.body = {
            list: result
        };
    } catch (e) {
        ctx.error = e;
    }
});
router.post('/',routerI({
    key:'clientInsert',
    schema:AdminSchema.clientInsert
}), async(ctx, next) => {
    const params = ctx.request.body;
    const clientSide = new bean_types.ClientSide(params);
    const clientSer = getThriftServer(clientService).getClient();
    try {
        let id = await clientSer.clientInsert(clientSide);
        if (id === 0) ctx.error = 'INSERT_FAIL';
        else ctx.body = {id: id};
    } catch (e) {
        ctx.error = e;
    }

});
router.put('/:id',routerI({
    key:'clientUpdate',
    schema:AdminSchema.clientUpdate
}), async(ctx, next) => {
    const params = ctx.request.body;
    const clientSide = new bean_types.ClientSide(params);
    clientSide.id = ctx.params.id;
    const clientSer = getThriftServer(clientService).getClient();
    try {
        let rowNumber = await clientSer.clientUpdate(clientSide);
        if (rowNumber === 0) ctx.error = 'UPDATE_FAIL';
        else ctx.body = {id: clientSide.id};
    } catch (e) {
        ctx.error = e;
    }
});
router.get('/:cid/versions',routerI({
    key:'versionQuery',
    schema:AdminSchema.versionQuery
}),  async(ctx, next) => {
    const params = ctx.query;
    const cid = ctx.params.cid;
    const version = new bean_types.Version();
    const query = new bean_types.Query(params);
    version.client_id = cid;
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
        ctx.body = {list:list,count:count}
    } catch (e) {
        ctx.error = e;
    }
});
module.exports = router;

