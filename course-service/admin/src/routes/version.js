/**
 * Created by wenshao on 2017/10/22.
 * 版本管理
 */
'use strict';
const router = require('koa-router')();
const bean_types = require('../gen-nodejs/bean_types');
const SysUtil = require('../util/sys_util');
const logger = getLogger();
const clientService = getServiceConfig().dalName.client;
const routerI = require('../middleware/router_interceptor');
const AdminSchema = require('../schema/admin_schema');
const apiName = '/';
// /versions/id
// /client/:id1/versions/:id2
router.get('/:id', async(ctx, next) => {
    console.log(ctx.params)
    console.log(ctx.query);
    ctx.body = {a:1}
});

module.exports = router;
