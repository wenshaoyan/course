const router = require('koa-router')();
const bean_types = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const logger = getLogger();
const userService = getServiceConfig().dalName[0];
const routerI = require('../middleware/router_interceptor');
const user_schema = require('../schema/user_schema');
const apiName = '/';
router.use(async (ctx, next) => {
    const myServer = getThriftServer(`'${userService}'`);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        await next();
    }
});
// 登录
router.get(apiName,routerI({
    key: "user_login",
    schema: user_schema.user_login
}), async(ctx, next) => {
    const client = getThriftServer(`'${userService}'`).getClient();
    const user = new bean_types.User();
    const params = ctx.query;
    user.tel = params.tel;
    user.password = params.password;
    try {
        const result = await client.userSelect(user);
        if (result.length === 0) {
            ctx.error = 'TEL_ERROR_OR_PASSWORD_ERROR';
        } else if (result.length > 1) {
            ctx.error = 'DATA_ERROR';
        } else {
            ctx.body = result[0];
        }
    } catch (e) {
        console.log(e);
        ctx.error = e;
    }
});
// 注册
router.post(apiName,routerI({
    key: "user_insert",
    schema: user_schema.user_login
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
router.get('test',async (ctx,next)=>{
    logger.info(ctx,ctx.userInfo);
});

module.exports = router;
