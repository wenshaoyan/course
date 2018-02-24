const Koa = require('koa');
const app = new Koa();
const router = require('koa-router')();
const convert = require('koa-convert');
const json = require('koa-json');
const bodyparser = require('koa-bodyparser')();
const cors = require('koa2-cors');
const {formatQuery, methodQuery, graphqlKoa} = require('thrift-node-core');

const getUser = require('./middleware/get_user');
const errorSource = require('./config/error_source.json');
const {getGraphqlSchema} = require('./util/graphql-schema-util');
// middlewares
app.use(convert(bodyparser));
app.use(convert(json()));
app.proxy = true;

// 从请求头中获取user-info
app.use(getUser({
    "Unchecked": ['/user'],
    "salt": "wenshao"
}));

app.use(async(ctx,next)=>{
    console.log(ctx.url,ctx.method,ctx.headers);
    await next();
})
// 跨域
app.use(cors({
    allowMethods: ['GET', 'POST', 'DELETE', 'PATCH', 'PUT'],
}));
app.use(formatQuery({
    suffixNotUseLike: '_id'
}));

router.post('/graphql', graphqlKoa({
    schema:getGraphqlSchema(),
    log:getLogger('graphql')
}));
/*router.get('/graphql', graphqlKoa({
    schema:getGraphqlSchema(),
    log:getLogger('graphql')
}));*/

app.use(router.routes(), router.allowedMethods());

// response
app.on('error', function (err, ctx) {
    console.log(err);
});
module.exports = app;