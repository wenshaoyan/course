const router = require('koa-router')();
const bean_types = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const banner_schema = require('../schema/banner_schema');
const routerI = require('../middleware/router_interceptor');

const logger = getLogger();
const apiName = 'banner';
const bannerServiceName =  getServiceConfig().dalName[0];
const clientService =  getServiceConfig().dalName[1];
router.use(async(ctx, next) => {
    const myServer = getThriftServer(`'${bannerServiceName}'`);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        await next();
    }
});

router.post(apiName, routerI({
    key: "banner_insert",
    schema: banner_schema.banner_insert
}), async(ctx, next) => {
    const client = getThriftServer(`'${bannerServiceName}'`).getClient();
    const banner = new bean_types.Banner();
    const params = ctx.request.body;
    SysUtil.copyObjectAttr(banner, params);
    try {
        let result = await client.insert(banner);

        if (result === 0) {
            ctx.error = 'INSERT_FAIL';
        } else {
            ctx.body = {
                id: result
            };
        }
    } catch (e) {
        console.log(e)
        ctx.error = e;
    }

});
router.get(`${apiName}/:id`, routerI({
    key: "banner_id",
    schema: banner_schema.banner_id
}), async(ctx, next) => {
    let id = ctx.params.id;
    const client = getThriftServer(`'${bannerServiceName}'`).getClient();
    try {
        ctx.body = await client.findById(id);
    } catch (e) {
        ctx.error = e;
    }
});
router.put(`${apiName}/:id`, routerI({
    key: "banner_update",
    schema: banner_schema.banner_update
}), async(ctx, next) => {
    const id = ctx.params.id;
    const params = ctx.request.body;
    const client = getThriftServer(`'${bannerServiceName}'`).getClient();
    const banner = new bean_types.Banner();
    banner.image_url = params.image_url;
    banner.redirect_url = params.redirect_url;
    banner.id = id;
    try {
        const result = await client.update(banner);
        if (result === 0) {
            ctx.error = 'UPDATE_FAIL';
        } else {
            ctx.body = {};
        }
    } catch (e) {
        ctx.error = e;
    }
});
router.delete(`${apiName}/:id`, routerI({
    key: "banner_id",
}), async(ctx, next) => {
    let id = ctx.params.id;
    const client = getThriftServer(`'${bannerServiceName}'`).getClient();
    try {
        const result = await client.remove(id);
        if (result === 0) {
            ctx.error = 'DELETE_FAIL';
        } else {
            ctx.body = {};
        }
    } catch (e) {
        ctx.error = e;
    }
});
router.patch(apiName);


module.exports = router;
