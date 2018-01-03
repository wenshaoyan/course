/**
 * Created by yanshaowen on 2017/10/25
 */
'use strict';
const router = require('koa-router')();
const Log = require('../schema/log');
const moment = require('moment');
router.post('/:env/:salt/:appender', async (ctx, next) => {
	const params = ctx.params;
	const reqBody = ctx.request.body;
	const dynamicLog = Log.dynamicModel(params.serverName, moment().format('YYYYMM'));
	const log = new dynamicLog(reqBody);
	ctx.body = 'ok';
	log.save().catch(e => {
		console.error(e);
	})
});

module.exports = router;
