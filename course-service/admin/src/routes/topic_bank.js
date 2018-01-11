/**
 * Created by yanshaowen on 2017/10/25
 * 题库
 */
'use strict';
const router = require('koa-router')();
const { TopicBank, Custom } = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const logger = getLogger();
const CommonService = getServiceConfig().dalName.common;
const routerI = require('../middleware/router_interceptor');
const AdminSchema = require('../schema/admin_schema');

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
        ctx.body = await client.topicBankSelectCustom(new TopicBank(),new Custom({tables: ['topic']}));
    } catch (e) {
        console.log(e);
        ctx.error = e;
    }
});
router.post('/', async (ctx, next) => {
    const params = ctx.request.body;
    try {
        const client = await getThriftServer(CommonService).getClient(ctx.poolTag);
        ctx.body = await client.topicBankInsert(new TopicBank(params));
    } catch (e) {
        console.log(e);
        ctx.error = e;
    }
});
// 关联题目
router.patch('/topics', async (ctx, next) => {
    const params = ctx.request.body;
    try {
        const client = await getThriftServer(CommonService).getClient(ctx.poolTag);
        ctx.body = await client.topicBankAddTopic(params.tb_id,params.topic_id);
    } catch (e) {
        console.log(e);
        ctx.error = e;
    }
});
// 删除关联
router.delete('/topics', async (ctx, next) => {
    const params = ctx.request.body;
    console.log(params)
    try {
        const client = await getThriftServer(CommonService).getClient(ctx.poolTag);
        ctx.body = await client.topicBankRemoveTopic(params.tb_id,params.topic_id);
    } catch (e) {
        console.log(e);
        ctx.error = e;
    }
});
module.exports = router;
