/**
 * Created by yanshaowen on 2017/10/25
 */
'use strict';
const router = require('koa-router')();
const bean_types = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const logger = getLogger();
const bannerService = getServiceConfig().dalName.banner;
const routerI = require('../middleware/router_interceptor');
const AdminSchema = require('../schema/admin_schema');
const apiName = '/';

router.use(async(ctx, next) => {
    const myServer = getThriftServer(`'${bannerService}'`);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        await next();
    }
});
/*router.get(apiName,routerI({

}))*/
module.exports = router;

