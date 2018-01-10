/**
 * Created by yanshaowen on 2017/10/25
 * 题目相关接口
 */
'use strict';
const router = require('koa-router')();
const { Topic } = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const logger = getLogger();
const CommonService = getServiceConfig().dalName.common;
const routerI = require('../middleware/router_interceptor');
const AdminSchema = require('../schema/admin_schema');

router.use(async (ctx, next) => {
    const myServer = getThriftServer(CommonService);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        ctx.poolTag = SysUtil.getUuid();
        await next();
        myServer.release(ctx.poolTag);
    }
});
router.get('/', async (ctx, next) => {
    const params = ctx.query;
    console.log(params);
    try {
        const client = await getThriftServer(CommonService).getClient(ctx.poolTag);
        ctx.body = await client.topicSelect(new Topic());
    } catch (e) {
        console.log(e);
        ctx.error = e;
    }
});


module.exports = router;
