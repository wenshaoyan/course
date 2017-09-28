const router = require('koa-router')();
const bean_types = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const logger = getLogger();

router.use(async(ctx, next) => {
    const userServer = getThriftServer('userServer');
    if (userServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.body = "error";
    } else {
        await next();
        if (ctx.body) {
            ctx.body = {
                success: false,
                data: ctx.body,
                message: "success",
                code:0
            };
        } else if (ctx.error) {
            if (ctx.error in errorSource) {
                ctx.body = {
                    success: false,
                    data: [],
                    message: errorSource[ctx.error].message,
                    errorSource: errorSource[ctx.error].errorSource,
                };
            } else if (typeof ctx.error === 'object') {
                ctx.body = {
                    success: false,
                    data: [],
                    message: ctx.error.message,
                    errorSource: ctx.error.errorSource
                };
            }

        }
    }
});
// 登录
router.get('user', async(ctx, next) => {
    const userServer = getThriftServer('userServer');
    const user = new bean_types.User();
    const params = ctx.request.query;
    user.tel = params.tel;
    user.password = params.password;
    const client = userServer.getClient();
    try {
        const result = await client.findUserByLogin(user);
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
router.post('user', async function (ctx, next) {
    const userServer = getThriftServer('userServer');
    const user = new bean_types.User();
    const params = ctx.request.body;
    user.name = params.name;
    user.head = "head";
    user.tel = params.tel;
    user.device_uuid = params.device_uuid;
    user.password = params.password;
    const client = userServer.getClient();
    try {
        const users = await client.findUserByTel(user.tel);
        if (users.length !== 0) {
            ctx.error = 'TEL_EXIST';
            return;
        }
        const insertResult = await client.insert(user);
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
