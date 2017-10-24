/**
 * Created by wenshao on 2017/10/22.
 * 平台用户管理
 */
const router = require('koa-router')();
const bean_types = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const logger = getLogger();
const userService = getServiceConfig().dalName[0];
const routerI = require('../middleware/router_interceptor');
const AdminSchema = require('../schema/admin_schema');
const apiName = '/';

router.use(async(ctx, next) => {
    const myServer = getThriftServer(`'${userService}'`);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        await next();
    }
});
// 查找用户
router.get(`${apiName}`, routerI({
    key: "user_query",
    schema: AdminSchema.user_query
}), async(ctx, next) => {
    const client = getThriftServer(`'${userService}'`).getClient();
    const params = ctx.query;
    const user = new bean_types.User(params);
    const query = new bean_types.Query(params);
    try {
        let userResult = [];
        // 查询满足条件的记录列表
        const i = await client.userCountSelectQuery(user, query);
        if (i !== 0) userResult = await client.userSelectQuery(user, query);
        userResult.map(value => {
            value.head = getServiceConfig().publicServer + value.head;
            return value;
        });
        const roleResult = await client.roleSelectAll();
        ctx.body = {list: userResult, count: i};
    } catch (e) {
        ctx.error = e;
    }
});
// 添加用户
router.post(apiName, routerI({
    key: "user_insert",
    schema: AdminSchema.user_insert
}), async function (ctx, next) {
    const userServer = getThriftServer(`'${userService}'`);
    const user = new bean_types.User();
    const queryUser = new bean_types.User();
    const params = ctx.request.body;
    user.name = params.name;
    user.head = "head";
    user.tel = params.tel;
    user.device_uuid = params.device_uuid;
    user.password = params.password;

    queryUser.tel = user.tel;
    const client = userServer.getClient();
    try {
        const users = await client.userSelect(queryUser);
        if (users.length !== 0) {
            ctx.error = 'TEL_EXIST';
            return;
        }
        const insertResult = await client.userInsert(user);
        if (insertResult === 0) {
            ctx.error = 'INSERT_FAIL';
            return;
        }
        ctx.body = {id: insertResult};

    } catch (e) {
        ctx.error = e;
    }
});
router.get('test', async(ctx, next) => {
    logger.info(ctx, ctx.userInfo);
});

module.exports = router;
