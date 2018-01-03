/**
 * Created by yanshaowen on 2018/01/03
 */
'use strict';

const Koa = require('koa');
const app = new Koa();
const router = require('koa-router')();
const convert = require('koa-convert');
const bodyparser = require('koa-bodyparser')();
const json = require('koa-json');

const write = require('./routers/write');

const router_log = require('./middleware/router_log');
const getUser = require('./middleware/get_user');
const response = require('./middleware/response');
const errorSource = require('./config/error_source.json');

// middlewares
app.use(convert(bodyparser));
app.use(convert(json()));
app.proxy = true;

// 从请求头中获取user-info
app.use(getUser({
	"Unchecked": ['/user'],
	"salt": "wenshao"
}));
// logger
app.use(router_log());

app.use(response({
	jsonFile: errorSource,
	successLog: getLogger('resSuccess'),
	failLog: getLogger('resFail'),
	unknownLog:getLogger('resUnknown')
}));

router.use('/AdminHttp', write.routes(), write.allowedMethods());

app.use(router.routes(), router.allowedMethods());
// response
app.on('error', function (err, ctx) {
	console.log(err);
});

module.exports = app;