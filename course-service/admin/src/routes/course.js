/**
 * Created by yanshaowen on 2017/11/05
 * 课程服务
 */
'use strict';
const router = require('koa-router')();
const bean_types = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const ArrayUtil = require('../util/array_util');
const logger = getLogger();
const courseService = getServiceConfig().dalName.course;
const routerI = require('../middleware/router_interceptor');
const AdminSchema = require('../schema/admin_schema');

router.use(async(ctx, next) => {
    const myServer = getThriftServer(courseService);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        ctx.poolTag = SysUtil.getUuid();
        await next();
        myServer.release(ctx.poolTag);
    }
});
router.get('/', async(ctx, next) => {
    const params = ctx.query;
    const course = new bean_types.Course(params);
    const query = new bean_types.Query(params);
    const custom = new bean_types.Custom();
    custom.tables = ['t_course_type', 't_course_type_group'];
    try {
        const client = await getThriftServer(courseService).getClient(ctx.poolTag);
        let count = undefined;
        let list = [];
        console.log(await client.courseInsert());
        if (params.action === 'search') {   // 搜索动作 请求总条数
            // 查询满足条件的记录列表
            count = await client.courseCountSelectQuery(course, query);
            if (count !== 0) list = await client.courseSelectQueryCustomNotCache(course, query, custom);
        } else {    // 翻页动作 不请求总条数
            list = await client.courseSelectQueryCustomNotCache(course, query, custom);
        }
        ArrayUtil.valueJoin(list, 'image_url', getServiceConfig().publicServer);
        ctx.body = {list: list, count: count};
    } catch (e) {
        ctx.error = e;
    }
});

module.exports = router;
