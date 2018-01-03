/**
 * Created by yanshaowen on 2017/10/25
 */
'use strict';
const router = require('koa-router')();
router.post('/:env/:salt/:appender',async (ctx, next) => {
	console.log(ctx.params);
	ctx.body = 'ok';
});

module.exports = router;
