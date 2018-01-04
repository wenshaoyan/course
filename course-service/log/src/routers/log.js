/**
 * Created by yanshaowen on 2017/10/25
 */
'use strict';
const router = require('koa-router')();
const Log = require('../schema/log');
router.get('/', async (ctx, next) => {

    await ctx.render('index');
});

module.exports = router;
