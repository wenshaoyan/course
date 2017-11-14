/**
 * Created by yanshaowen on 2017/10/25
 * 轮播图管理
 */
'use strict';
const router = require('koa-router')();
const bean_types = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const ArrayUtil = require('../util/array_util');
const logger = getLogger();
const bannerService = getServiceConfig().dalName.banner;
const routerI = require('../middleware/router_interceptor');
const AdminSchema = require('../schema/admin_schema');
const apiName = '/';

router.use(async (ctx, next) => {
    const myServer = getThriftServer(bannerService);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        ctx.poolTag = SysUtil.getUuid();
        await next();
        myServer.release(ctx.poolTag);
    }
});
router.get('/', async (ctx, next) => {
    const params = ctx.query;
    const banner = new bean_types.Banner(params);
    const query = new bean_types.Query(params);
    try {
        const client =   getThriftServer(bannerService).getClient(ctx.poolTag);
        const result = await client.selectQueryNoCache(banner, query);
        ArrayUtil.valueJoin(result, 'image_url', getServiceConfig().publicServer);
        ctx.body = result;
    } catch (e) {
        ctx.error = e;
    }

});
router.post('/', async (ctx, next) => {
    const params = ctx.request.body;
    const banner = new bean_types.Banner(params);
    banner.client_id = params.client_id;
    try {
        const client = await getThriftServer(bannerService).getClient(ctx.poolTag);
        const id = await client.insert(banner);
        ctx.body = {
            id: id
        };
    } catch (e) {
        ctx.error = e;
    }

});
router.put('/:id', async (ctx, next) => {
    const params = ctx.request.body;
    const banner = new bean_types.Banner(params);
    banner.id = ctx.params.id;
    try {
        const client = await getThriftServer(bannerService).getClient(ctx.poolTag);
        const result = await client.update(banner);
        if (result !== 1) {
            ctx.error = 'UPDATE_FAIL';
            return;
        }
        ctx.body = {
            id: banner.id
        };
    } catch (e) {
        ctx.error = e;
    }

});
// 修改位置
router.patch('/:id', async (ctx, next) => {
    const params = ctx.query;
    const banner = new bean_types.Banner();
    banner.id = ctx.params.id;
    banner.location = params.location;
    try {
        const client = await getThriftServer(bannerService).getClient(ctx.poolTag);
        const result = await client.update(banner);
        if (result !== 1) {
            ctx.error = 'UPDATE_FAIL';
            return;
        }
        ctx.body = {
            id: banner.id
        };
    } catch (e) {
        ctx.error = e;
    }
});
router.delete('/:id', async (ctx, next) => {
    const params = ctx.params;
    const banner = new bean_types.Banner();
    banner.id = params.id;
    try {
        const client = await getThriftServer(bannerService).getClient(ctx.poolTag);
        const result = await client.remove(banner);
        if (result !== 1) {
            ctx.error = 'DELETE_FAIL';
            return;
        }
        ctx.body = {
            id: params.id
        };
    } catch (e) {
        ctx.error = e;
    }
});
module.exports = router;
