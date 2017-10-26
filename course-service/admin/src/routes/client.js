/**
 * Created by yanshaowen on 2017/10/25
 */
'use strict';
const router = require('koa-router')();
const bean_types = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const logger = getLogger();
const ClientService = getServiceConfig().dalName.client;
const routerI = require('../middleware/router_interceptor');
const AdminSchema = require('../schema/admin_schema');

router.use(async(ctx, next) => {
    const myServer = getThriftServer(ClientService);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        await next();
    }
});
router.get('/', async(ctx, next) => {
    const params = ctx.query;
    const clientSide = new bean_types.ClientSide(params);
    const clientSer = getThriftServer(ClientService).getClient();
    try {
        const result = await clientSer.clientSelect(clientSide);
        const query = new bean_types.Query();
        query.sort_by = 'cv_version_number';
        query.order = 'desc';
        query.limit = 1;
        for (let value of result) {
            const version = new bean_types.Version();
            version.client_id = value.id;
            const versionResult = await clientSer.versionSelectQuery(version, query);
            value.new_version = versionResult.length === 1 ? versionResult[0] : {};
        }

        ctx.body = {
            list: result
        };
    } catch (e) {
        ctx.error = e;
    }
});
router.post('/', async(ctx, next) => {
    const params = ctx.request.body;
    const clientSide = new bean_types.ClientSide(params);
    const clientSer = getThriftServer(ClientService).getClient();
    try {
        let id = await clientSer.clientInsert(clientSide);
        if (id === 0) ctx.error = 'INSERT_FAIL';
        else ctx.body = {id: id};
    } catch (e) {
        ctx.error = e;
    }

});
router.put('/:id', async(ctx, next) => {
    const params = ctx.request.body;
    const clientSide = new bean_types.ClientSide(params);
    clientSide.id = ctx.params.id;
    const clientSer = getThriftServer(ClientService).getClient();
    try {
        let rowNumber = await clientSer.clientUpdate(clientSide);
        if (rowNumber === 0) ctx.error = 'UPDATE_FAIL';
        else ctx.body = {id: clientSide.id};
    } catch (e) {
        ctx.error = e;
    }
});
router.get('/:cid/versions', async(ctx, next) => {
    const params = ctx.query;
    const cid = ctx.params.cid;
    const version = new bean_types.Version();
    const query = new bean_types.Query(params);
    version.clent_id = cid;
    const clientSer = getThriftServer(ClientService).getClient();
    try {
        let result = await clientSer.versionSelectQuery(version, query);
        console.log(result);
        ctx.body = {
            a: 1
        }
    } catch (e) {
        ctx.error = e;
    }
});
module.exports = router;

