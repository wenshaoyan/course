/**
 * Created by yanshaowen on 2017/10/25
 * 模板
 */
'use strict';
const router = require('koa-router')();
const { TopicOption, AbstractSql } = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const logger = getLogger();
const CommonService = getServiceConfig().dalName.common;
const routerI = require('../middleware/router_interceptor');
const AdminSchema = require('../schema/admin_schema');
const apiName = '/';

router.use(async(ctx, next) => {
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
    try {
        const client = await getThriftServer(CommonService).getClient(ctx.poolTag);
        ctx.body = await client.topicOptionSelect(new AbstractSql({
            where: {
                to_id: {
                    eq: '5'
                }
            }
        }));
    } catch (e) {
        console.log(e);
        ctx.error = e;
    }
});
router.post('/', async (ctx, next) => {
    const params = ctx.request.body;
    try {
        const client = await getThriftServer(CommonService).getClient(ctx.poolTag);
        ctx.body = await client.topicOptionInsert(new TopicOption(params));
    } catch (e) {
        console.log(e);
        ctx.error = e;
    }
});
module.exports = router;
