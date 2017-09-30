const router = require('koa-router')();
const bean_types = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const banner_schema = require('../schema/banner_schema');
const routerI = require('../middleware/router_interceptor');

const logger = getLogger();
const apiName = 'banner';

router.use(async (ctx, next) => {
    const myServer = getThriftServer(`'${getServiceConfig().serverName}'`);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        await next();
    }
});

router.post(apiName, routerI({
    schema: banner_schema.banner_insert,
    key:"banner_insert"
}), async (ctx, next) => {
    const client = getThriftServer(`'${getServiceConfig().serverName}'`).getClient();
    const banner = new bean_types.Banner();
    const params = ctx.request.body;
    SysUtil.copyObjectAttr(banner, params);
    try {
        let result = await client.insert(banner);
        ctx.body = {
            id: result
        };
    } catch (e) {
        ctx.error = e;
    }

});
router.get(apiName, async (ctx, next) => {
    ctx.body = 1;
});
router.put(apiName);
router.delete(apiName);
router.patch(apiName);


router.get('test', async (ctx, next) => {
    let banner = new bean_types.Banner();
    banner.id = 2;
    banner.create_time = '1506675182000';
    const client = getThriftServer(`'${getServiceConfig().serverName}'`).getClient();
    ctx.body = await client.select(banner);
});

module.exports = router;
