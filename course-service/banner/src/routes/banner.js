const router = require('koa-router')();
const bean_types = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const banner_schema = require('../schema/banner_schema');
const routerI = require('../middleware/router_interceptor');

const logger = getLogger();
const bannerServiceName = getServiceConfig().dalName.banner;
const clientService = getServiceConfig().dalName.client;
router.use(async (ctx, next) => {
    const myServer = getThriftServer(bannerServiceName);
    const cMyServer = getThriftServer(clientService);
    if (myServer.connectionStatus !== 1 && cMyServer.connectionStatus !==1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        ctx.poolTag = SysUtil.getUuid();
        await next();
        myServer.release(ctx.poolTag);
        cMyServer.release(ctx.poolTag);
    }
});

router.post('/', routerI({
    key: "banner_insert",
    schema: banner_schema.banner_insert
}), async (ctx, next) => {
    const banner = new bean_types.Banner();
    const params = ctx.request.body;
    SysUtil.copyObjectAttr(banner, params);
    try {
        const client = await getThriftServer(bannerServiceName).getClient(ctx.poolTag);
        let result = await client.insert(banner);

        if (result === 0) {
            ctx.error = 'INSERT_FAIL';
        } else {
            ctx.body = {
                id: result
            };
        }
    } catch (e) {
        ctx.error = e;
    }

});
router.get('/:id', routerI({
    key: "banner_id",
    schema: banner_schema.banner_id
}), async (ctx, next) => {
    let id = ctx.params.id;
    try {
        const client = await getThriftServer(bannerServiceName).getClient(ctx.poolTag);
        ctx.body = await client.findById(id);
    } catch (e) {
        ctx.error = e;
    }
});
router.put('/:id', routerI({
    key: "banner_update",
    schema: banner_schema.banner_update
}), async (ctx, next) => {
    const id = ctx.params.id;
    const params = ctx.request.body;
    const banner = new bean_types.Banner();
    banner.image_url = params.image_url;
    banner.redirect_url = params.redirect_url;
    banner.id = id;
    try {
        const client = await getThriftServer(bannerServiceName).getClient(ctx.poolTag);
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
router.delete('/:id', routerI({
    key: "banner_id",
}), async (ctx, next) => {
    let id = ctx.params.id;
    try {
        const client = await getThriftServer(bannerServiceName).getClient(ctx.poolTag);
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
router.get('/', routerI({
    key: "banner_filter",
    schema: banner_schema.banner_filter
}), async (ctx, next) => {
    const params = ctx.query;
    const banner = new bean_types.Banner();
    const clientSide = new bean_types.ClientSide();
    clientSide.package_name = params.package_name;
    SysUtil.copyObjectAttr(banner, params);
    try {
        const bClient = await getThriftServer(bannerServiceName).getClient(ctx.poolTag);
        const cClient = await getThriftServer(clientService).getClient(ctx.poolTag);

        let result = await cClient.clientSelect(clientSide);
        if (result.length !== 1) {
            ctx.error = 'PACKAGE_NAME_ERROR';
        } else {
            banner.show_client_id = result[0].id;
            let bannerResults = await bClient.select(banner);
            bannerResults.forEach((value) => {
                value.image_url = getServiceConfig().publicServer + value.image_url;
            });
            ctx.body = bannerResults;
        }
    } catch (e) {
        console.log(e)
        ctx.error = e;
    }
});


module.exports = router;
