/**
 * Created by yanshaowen on 2017/10/25
 * 题库
 */
'use strict';
const router = require('koa-router')();
const {TopicBank, Custom} = require('../gen-nodejs/bean_types');
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
    const params = ctx.querySql;
    const client = await getThrift(CommonService).getClient(ctx.poolTag);
    ctx.body = await client.topicBankSelect(new AbstractSqlBean(params));

});
router.get('/counts', async (ctx, next) => {
    const params = ctx.querySql;
    // params.mode = 'topic+option';
    try {
        const client = await getThrift(CommonService).getClient(ctx.poolTag);
        const count = await client.topicBankCount(new AbstractSqlBean(params));
        ctx.body = {count};
    } catch (e) {
        ctx.error = e;
    }
});
router.post('/', async (ctx, next) => {
    const params = ctx.request.body;
    try {
        const client = await getThrift(CommonService).getClient(ctx.poolTag);
        ctx.body = await client.topicBankInsert(new TopicBank(params));
    } catch (e) {
        ctx.error = e;
    }
});
// 关联题目
router.patch('/topics', async (ctx, next) => {
    const params = ctx.request.body;
    try {
        const client = await getThrift(CommonService).getClient(ctx.poolTag);
        ctx.body = await client.topicBankAddTopic(params.tb_id, params.topic_id);
    } catch (e) {
        ctx.error = e;
    }
});
// 删除关联
router.delete('/topics', async (ctx, next) => {
    const params = ctx.request.body;
    try {
        const client = await getThrift(CommonService).getClient(ctx.poolTag);
        ctx.body = await client.topicBankRemoveTopic(params.tb_id, params.topic_id);
    } catch (e) {
        ctx.error = e;
    }
});
module.exports = router;
