const Koa = require('koa');
const app = new Koa();
const router = require('koa-router')();
const convert = require('koa-convert');
const json = require('koa-json');
const bodyparser = require('koa-bodyparser')();
const cors = require('koa-cors');


const user = require('./routes/user');
const router_log = require('./middleware/router_log');
const getUser = require('./middleware/get_user');
const response = require('./middleware/response');
const errorSource = require('./config/error_source.json');

// middlewares
app.use(convert(bodyparser));
app.use(convert(json()));
app.proxy = true;



// logger
app.use(router_log());

app.use(response({
    jsonFile:errorSource,
    successLog:getLogger('resSuccess'),
    failLog:getLogger('resFail')
}));

// 跨域
// app.use(cors());
// 获取用户信息
// app.use(getUser());
router.use('/user', user.routes(),user.allowedMethods());

app.use(router.routes(), router.allowedMethods());
// response

app.on('error', function (err, ctx) {
    console.log(err);
});


module.exports = app;