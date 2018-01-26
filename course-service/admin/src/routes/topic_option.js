/**
 * Created by yanshaowen on 2017/10/25
 * 模板
 */
'use strict';
const router = require('koa-router')();
const { TopicOption } = require('../gen-nodejs/bean_types');
const AbstractSqlBean = require('../bean/AbstractSqlBean');
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
        ctx.body = await client.topicOptionSelect(new AbstractSqlBean({
            where: {
	            to_id: {
		            between: ['1','10'],
                },
                to_create_time: {
	                between: ['2018-01-24 16:20:21.000','2018-01-25 16:44:21.000']
                },
                to_topic_id: {
	                noAny: ['1', '2', '5']
                },
	            to_context: {
	            	notLike: "12"
	            }
            },
            includes: [{
	            association: 'topic',
	            required: false,
	            includes:[{
		            association: 'topic_option',
		            required: false,
	            }]
            }],
            order: 'to_create_time DESC',
            group: 'to_id',
            limit: [10, 0]
        }));
    } catch (e) {
        console.log(e.fullMessage)
        ctx.error = e;
    }
});
router.post('/', async (ctx, next) => {
    const params = ctx.request.body;
    try {
        const client = await getThriftServer(CommonService).getClient(ctx.poolTag);
        ctx.body = await client.topicOptionInsert(new TopicOption(params));
    } catch (e) {
        ctx.error = e;
    }
});
module.exports = router;
