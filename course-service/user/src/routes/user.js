const router = require('koa-router')();
const bean_types = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const logger = getLogger();
const userService = getServiceConfig().dalName.user;
const routerI = require('../middleware/router_interceptor');
const UserSchema = require('../schema/user_schema');
const apiName = '/';
const ioredis = require('ioredis');
let redis = new ioredis(require('../config/redis.json'));
router.use(async(ctx, next) => {
    const myServer = getThriftServer(userService);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        ctx.poolTag = SysUtil.getUuid();
        await next();
        myServer.release(ctx.poolTag);
    }
});
// 登录
router.get(apiName, routerI({
    key: "user_login",
    schema: UserSchema.user_login
}), async(ctx, next) => {
    const user = new bean_types.User();

    const params = ctx.query;

    user.tel = params.tel;
    user.password = params.password;
    try {
        const client = await getThriftServer(userService).getClient(ctx.poolTag);
        const result = await client.userSelect(user);
        if (result.length === 0) {
            ctx.error = 'TEL_ERROR_OR_PASSWORD_ERROR';
            return;
        } else if (result.length > 1) {
            ctx.error = 'DATA_ERROR';
            return;
        }
        let userResult = result[0];
        let roleResult = await client.roleFindById(userResult.role_id);
        if (!roleResult.id) {
            ctx.error = 'USER_ROLE_NOT_FOUND';
            return;
        }
        userResult.head = getServiceConfig().publicServer + userResult.head;
        userResult.permission = JSON.parse(roleResult.permission);
        const key = `token:${SysUtil.getUuid()}`;
        userResult.tokenID = key;
        redis.set(key, JSON.stringify(userResult), 'EX', getServiceConfig().tokenTime);
        ctx.body = userResult;
    } catch (e) {
        console.log(e);
        ctx.error = e;
    }
});
// 注册
router.post(apiName, routerI({
    key: "user_insert",
    schema: UserSchema.user_insert
}), async function (ctx, next) {
    const user = new bean_types.User();
    const queryUser = new bean_types.User();
    const params = ctx.request.body;
    user.name = params.name;
    user.head = '/head/0130391F45572E36CC55343619A1D84A02D00E-0E65-4211-D270-4C6CB4071F28.png';
    user.tel = params.tel;
    user.device_uuid = params.device_uuid;
    user.password = params.password;
    user.role_id = 100;
    queryUser.tel = user.tel;
    try {
        const client = await getThriftServer(userService).getClient(ctx.poolTag);
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
