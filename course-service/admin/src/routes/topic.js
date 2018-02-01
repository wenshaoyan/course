/**
 * Created by yanshaowen on 2017/10/25
 * 题目相关接口
 */
'use strict';
const router = require('koa-router')();
const { Topic } = require('../gen-nodejs/bean_types');
const {AbstractSqlBean, getThrift} = require('thrift-node-core');
const SysUtil = require('../util/sys_util');
const CommonService = 'CommonService';
const routerI = require('../middleware/router_interceptor');
const AdminSchema = require('../schema/admin_schema');

router.use(async (ctx, next) => {
    const myServer = getThrift(CommonService);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        ctx.poolTag = SysUtil.getUuid();
        await next();
        myServer.release(ctx.poolTag);
    }
});
router.get('/', async (ctx, next) => {
    try {
        const client = await getThrift(CommonService).getClient(ctx.poolTag);
        ctx.body = await client.topicSelect(new AbstractSqlBean(ctx.querySql));
    } catch (e) {
        console.log(e);
        ctx.error = e;
    }
});
router.get('/counts', async (ctx, next) => {
    const params = ctx.query;
    try {
        const client = await getThrift(CommonService).getClient(ctx.poolTag);
        ctx.body = await client.topicSelect(new AbstractSqlBean({

        }));
    } catch (e) {
        console.log(e);
        ctx.error = e;
    }
});

router.post('/', async (ctx, next) => {
    const params = ctx.request.body;
    try {
        const client = await getThrift(CommonService).getClient(ctx.poolTag);
        ctx.body = await client.topicInsert(new Topic(params));
    } catch (e) {
        console.log(e);
        ctx.error = e;
    }
});

module.exports = router;
