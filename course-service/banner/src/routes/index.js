const router = require('koa-router')();
const bean_types = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const logger = getLogger();


router.use(async (ctx, next) => {
    const myServer = getThriftServer(`'${getServiceConfig().serverName}'`);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.body = "error";
    } else {
        await next();
        if (ctx.body) {
            ctx.body = {
                success: false,
                data: ctx.body,
                message: "success",
                code: 0
            };
        } else if (ctx.error) {
            if (ctx.error in errorSource) {
                ctx.body = {
                    success: false,
                    message: errorSource[ctx.error].message,
                    errorSource: errorSource[ctx.error].errorSource,
                    code: errorSource[ctx.error].code,
                    data: [],
                };
            } else if (typeof ctx.error === 'object') {
                ctx.body = {
                    success: false,
                    message: ctx.error.message,
                    errorSource: ctx.error.errorSource,
                    code: 1,
                    data: []
                };
            }

        }
    }
});


router.get('test', async (ctx, next) => {
    let banner = new bean_types.Banner();
    banner.id = 2;
    banner.create_time='1506675182000';
    const client = getThriftServer(`'${getServiceConfig().serverName}'`).getClient();
    ctx.body = await client.select(banner);
});

module.exports = router;
