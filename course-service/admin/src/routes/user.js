/**
 * Created by wenshao on 2017/10/22.
 * 平台用户管理
 */
'use strict';
const router = require('koa-router')();
const bean_types = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const logger = getLogger();
const userService = getServiceConfig().dalName.user;
const routerI = require('../middleware/router_interceptor');
const AdminSchema = require('../schema/admin_schema');

const userList = async (poolTag) => {
    const list = [];
    for (let i = 0; i<1;i++){
        const client = await getThriftServer(userService).getClient(poolTag);
        list.push(client.userSelectAll());
    }
    return Promise.all(list);

};


router.use(async (ctx, next) => {
    const myServer = getThriftServer(userService);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        ctx.poolTag = SysUtil.getUuid();
        await next();
        //myServer.release(ctx.poolTag);
    }
});

// 查找用户
router.get('/', routerI({
    key: "userQuery",
    schema: AdminSchema.userQuery
}), async (ctx, next) => {
    const params = ctx.query;
    const user = new bean_types.User(params);
    const query = new bean_types.Query(params);
    try {
        const client = await getThriftServer(userService).getClient(ctx.poolTag);
        let userResult = [];
        let count = undefined;
        if (params.action === 'search') {   // 搜索动作 请求总条数
            // 查询满足条件的记录列表
            count = await client.userCountSelectQuery(user, query);
            if (count !== 0) userResult = await client.userSelectQuery(user, query);
        } else {    // 翻页动作 不请求总条数
            const r = await userList(ctx.poolTag);
            console.log(r)
            userResult = await client.userSelectQuery(user, query);
        }
        userResult.map(value => {
            value.head = getServiceConfig().publicServer + value.head;
            return value;
        });

        ctx.body = {list: userResult, count: count};
    } catch (e) {
        ctx.error = e;
    }
});
// 添加用户
router.post('/', routerI({
    key: "userInsert",
    schema: AdminSchema.userInsert
}), async function (ctx, next) {
    const userServer = getThriftServer(userService);
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
router.get('test', async (ctx, next) => {
    logger.info(ctx, ctx.userInfo);
});

module.exports = router;
