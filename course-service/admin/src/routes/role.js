/**
 * Created by wenshao on 2017/10/22.
 */
'use strict';
const router = require('koa-router')();
const bean_types = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const logger = getLogger();
const userService = getServiceConfig().dalName.user;
const routerI = require('../middleware/router_interceptor');
const AdminSchema = require('../schema/admin_schema');
const apiName = '/';
router.use(async(ctx, next) => {
    const myServer = getThriftServer(userService);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        ctx.poolTag = SysUtil.getUuid();
        await next();
        myServer.release(ctx.poolTag);
    }
});
router.get(apiName,async(ctx, next) => {
    try {
        const client = await getThriftServer(userService).getClient(ctx.poolTag);
        const result = await client.roleSelectAll();
        result.map(value=>{
            value.permission = JSON.parse(value.permission);
            return value;
        });
        ctx.body = result;
    }catch (e){
        console.log(e)
        ctx.error = e;
    }
});

module.exports = router;
