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
const apiName = '/';
const apiVersionName = '/version';

router.use(async (ctx, next) => {
    const myServer = getThriftServer(`'${ClientService}'`);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        await next();
    }
});
router.get(apiName, routerI({
    key: "client_query",
    schema: AdminSchema.client_query
}), async (ctx, next) => {
    const params = ctx.query;
    const clientSide = new bean_types.ClientSide(params);
    const clientSer = getThriftServer(`'${ClientService}'`).getClient();
    try {
        const result = await clientSer.clientSelect(clientSide);
        ctx.body = result;
    }catch (e){
        ctx.error = e;
    }
});
router.get(apiVersionName, routerI({
    key: "client_query",

}), async (ctx, next) => {
    const params = ctx.query;
    const version = new bean_types.Version(params);
    const clientSer = getThriftServer(ClientService).getClient();
    try {
        const result = await clientSer.clientSelect(clientSide);
        ctx.body = result;
    }catch (e){
        ctx.error = e;
    }
});
module.exports = router;

