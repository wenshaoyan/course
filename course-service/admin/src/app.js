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
const router_log = require('./middleware/router_log');
const token = require('./middleware/token');
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
    //successLog:getLogger('resSuccess'),
    failLog:getLogger('resFail')
}));

// 跨域
app.use(cors());
// 操作user表
router.use('/user', user.routes(),user.allowedMethods());
router.use('/management', management.routes(),management.allowedMethods());
router.use('/role', role.routes(),role.allowedMethods());

app.use(router.routes(), router.allowedMethods());
// response

app.on('error', function (err, ctx) {
    console.log(err);
});

module.exports = app;