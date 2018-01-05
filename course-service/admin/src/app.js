const Koa = require('koa');
const app = new Koa();
const router = require('koa-router')();
const convert = require('koa-convert');
const json = require('koa-json');
const bodyparser = require('koa-bodyparser')();
const cors = require('koa2-cors');


const user = require('./routes/user');
const management = require('./routes/management');
const role = require('./routes/role');
const banner = require('./routes/banner');
const client = require('./routes/client');
const version = require('./routes/version');
const course = require('./routes/course');

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
// 跨域
app.use(cors({
    allowMethods: ['GET', 'POST', 'DELETE', 'PATCH', 'PUT'],
}));

router.use('/users', user.routes(), user.allowedMethods());
router.use('/managements', management.routes(), management.allowedMethods());
router.use('/roles', role.routes(), role.allowedMethods());
router.use('/banners', banner.routes(), banner.allowedMethods());
router.use('/clients', client.routes(), client.allowedMethods());
router.use('/versions', version.routes(), version.allowedMethods());
router.use('/courses', course.routes(), course.allowedMethods());


app.use(router.routes(), router.allowedMethods());
// response
app.on('error', function (err, ctx) {
    console.log(err);
});
const t = getLogger('zookeeper');
let i = 0;
setInterval(function () {
    t.info(i++);
},300);
module.exports = app;