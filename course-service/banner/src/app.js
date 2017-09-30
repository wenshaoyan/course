const Koa = require('koa');
const app = new Koa();
const router = require('koa-router')();
const convert = require('koa-convert');
const json = require('koa-json');
const bodyparser = require('koa-bodyparser')();

const index = require('./routes/index');
const router_log = require('./middleware/router_log');
const token = require('./middleware/token');
const getUser = require('./middleware/get_user');
const response = require('./middleware/response');
// middlewares
app.use(convert(bodyparser));
app.use(convert(json()));
app.proxy = true;



// logger
app.use(router_log());
/*app.use(token({
        "maxAge":1000*60*60*24,
        "redisStore":{
            port: 6379,          // Redis port
            host: '123.207.55.204',   // Redis host
            family: 4,           // 4 (IPv4) or 6 (IPv6)
            password: 'wenshao314106',
            db: 0
        }
    })
);*/
// 获取用户信息
// app.use(getUser());
// 规范响应头
app.use(response({
    jsonFile:errorSource,
    successLog:getLogger('resSuccess'),
    failLog:getLogger('resFail')

}));
router.use('/', index.routes(),index.allowedMethods());

app.use(router.routes(), router.allowedMethods());
// response

app.on('error', function (err, ctx) {
    console.log(err);
});

module.exports = app;