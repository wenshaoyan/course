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
router.get('/', routerI({
    key: 'versionQuery',
}), async(ctx, next) => {
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
        list.forEach(value => value.download_url = getServiceConfig().publicServer + value.download_url);
        ctx.body = {list: list, count: count}
    } catch (e) {
        ctx.error = e;
    }
});
router.post('/',routerI({
    key: 'versionInsert',
    schema:AdminSchema.versionInsert
}),  async(ctx, next) => {
    const params = ctx.request.body;
    const version = new bean_types.Version(params);
    const clientSer = getThriftServer(clientService).getClient();
    try {
        console.log(version)

        let id = await clientSer.versionInsert(version);
        ctx.body = {
            id: id
        }
    } catch (e) {
        console.log(e)
        ctx.error = e;

    }
});
router.put('/:id', routerI({
    key: 'versionUpdate',
    schema:AdminSchema.versionUpdate
}),async(ctx, next) => {
    const params = ctx.request.body;
    const version = new bean_types.Version(params);
    version.id = ctx.params.id;
    const clientSer = getThriftServer(clientService).getClient();
    try {
        console.log(version)
        let id = await clientSer.versionUpdate(version);
        ctx.body = {
            id: version.id
        }
    } catch (e) {
        ctx.error = e;

    }
});
module.exports = router;
