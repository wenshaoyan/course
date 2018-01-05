/**
 * Created by yanshaowen on 2017/10/25
 */
'use strict';
const router = require('koa-router')();
const Log = require('../schema/log');
const collList = ['adminhttp'];
const moment = require('moment');
const formatTime = 'YYYY-MM-DD HH:mm:ss';
router.get('/', async (ctx, next) => {

    await ctx.render('index', {
        serviceList: collList,
        startTime: moment().startOf('hour').format(formatTime),
        endTime: moment().format(formatTime),
    });
});
router.get('/search', async (ctx, next) => {
    const params = ctx.query;
    // 参数检查

    if (params.isTail === '1') {
        params.startTime = moment().subtract(5, 'seconds').format(formatTime);
        params.endTime = moment().format(formatTime);
    }


    const dynamicLog = Log.dynamicModel(params.serverName,
        moment(params.startTime).format('YYYYMM'));
    const findRule = {
        t: {$gt: new Date(params.startTime), $lt: new Date(params.endTime)}
    };
    if (params.searchText && params.searchText.length > 0) {
        const re = new RegExp(params.searchText,'g');
        findRule.m = {$regex:re}
    }
    ctx.compress = true;
    ctx.body = await dynamicLog.find(findRule);
});

module.exports = router;
