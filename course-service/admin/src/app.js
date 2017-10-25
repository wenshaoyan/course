const Koa = require('koa');
const app = new Koa();
const router = require('koa-router')();
const convert = require('koa-convert');
const json = require('koa-json');
const bodyparser = require('koa-bodyparser')();
const cors = require('koa-cors');


const user = require('./routes/user');
const management = require('./routes/management');
const role = require('./routes/role');
const banner = require('./routes/banner');
const client = require('./routes/client');

const router_log = require('./middleware/router_log');
const token = require('./middleware/token');
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
    //successLog:getLogger('resSuccess'),
    failLog: getLogger('resFail')
}));

// 跨域
app.use(cors());

router.use('/user', user.routes(), user.allowedMethods());
router.use('/management', management.routes(), management.allowedMethods());
router.use('/role', role.routes(), role.allowedMethods());
router.use('/banner', banner.routes(), banner.allowedMethods());
router.use('/client', client.routes(), client.allowedMethods());

app.use(router.routes(), router.allowedMethods());
// response

app.on('error', function (err, ctx) {
    console.log(err);
});

module.exports = app;