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
        await next();
    }
});
router.get('/', async (ctx, next) => {
    const params = ctx.query;
    const banner = new bean_types.Banner(params);
    const query = new bean_types.Query(params);
    const client = getThriftServer(bannerService).getClient();
    try {
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
    banner.show_client_id = params.client_id;
    const client = getThriftServer(bannerService).getClient();
    try {
        console.log(banner)
        const id = await client.insert(banner);
        ctx.body = {
            id: id
        };
    } catch (e) {
        ctx.error = e;
    }

});
router.put('/:id', async (ctx, next) => {

});
// 修改位置
router.patch('/:id', async (ctx, next) => {
    const params = ctx.query;
    const banner = new bean_types.Banner();
    banner.id = ctx.params.id;
    banner.location = params.location;
    const client = getThriftServer(bannerService).getClient();
    try {
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

});
module.exports = router;
