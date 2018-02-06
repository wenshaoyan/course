const Koa = require('koa');
const app = new Koa();
const router = require('koa-router')();
const convert = require('koa-convert');
const json = require('koa-json');
const bodyparser = require('koa-bodyparser')();
const cors = require('koa2-cors');
const path = require('path');
const {routerLog, response, formatQuery, methodQuery} = require('thrift-node-core');
const {graphqlKoa, graphiqlKoa} = require('apollo-server-koa');
const {
    GraphQLSchema,
    GraphQLObjectType,
} = require('graphql');
/*const user = require('./routes/user');
 const management = require('./routes/management');
 const role = require('./routes/role');
 const banner = require('./routes/banner');
 const client = require('./routes/client');
 const version = require('./routes/version');
 const course = require('./routes/course');
const topicBank = require('./routes/topic_bank');
const topic = require('./routes/topic');
const topicOption = require('./routes/topic_option');
*/

const getUser = require('./middleware/get_user');
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
app.use(routerLog());

/*app.use(response({
    jsonFile: errorSource,
    // successLog: getLogger('resSuccess'),
    failLog: getLogger('resFail'),
    unknownLog: getLogger('resUnknown'),
    errorLog: getLogger('errorLog'),

}));*/

// 跨域
app.use(cors({
    allowMethods: ['GET', 'POST', 'DELETE', 'PATCH', 'PUT'],
}));
app.use(formatQuery({
    suffixNotUseLike: '_id'
}));

/*router.use('/users', user.routes(), user.allowedMethods());
 router.use('/managements', management.routes(), management.allowedMethods());
 router.use('/roles', role.routes(), role.allowedMethods());
 router.use('/banners', banner.routes(), banner.allowedMethods());
 router.use('/clients', client.routes(), client.allowedMethods());
 router.use('/versions', version.routes(), version.allowedMethods());
 router.use('/courses', course.routes(), course.allowedMethods());
router.use('/topic-banks', topicBank.routes(), topicBank.allowedMethods());
router.use('/topics', topic.routes(), topic.allowedMethods());
router.use('/topic-options', topicOption.routes(), topicOption.allowedMethods());
*/
const SysUtil = require('./util/sys_util');
const {Queries, Mutations} = SysUtil.mergeDirSchema('../types');
const schema = new GraphQLSchema({
    query: new GraphQLObjectType({
        name: 'Query',
        description: `查询微服务`,
        fields: Queries
    }),
    mutation: new GraphQLObjectType({
        name: 'Mutation',
        description: '修改微服务',
        fields: Mutations
    })
});
function next123() {
    return async(ctx, next) => {
        /*console.log(ctx)
        graphqlKoa({
            schema,
            context: {
                ctx: ctx,
                // user: this.state && this.state.user,
                // status: this.service.util.status.bind(this.service.util),
            }
        });*/
        //ctx.body ='1'
        console.log('=========')
        // return next1234();
        await next();
    }
}
function next1234() {
    return async(ctx, next) => {
        graphqlKoa({
            schema,
            context: {
                ctx: ctx,
                // user: this.state && this.state.user,
                // status: this.service.util.status.bind(this.service.util),
            },
            tracing: true
        })(ctx);
        // await next();
    }
}
router.post('/graphql',next1234());
router.get('/graphql', graphqlKoa({
    schema
}));
router.get('/graphiql', graphiqlKoa({ endpointURL: '/graphql' }));

app.use(router.routes(), router.allowedMethods());

// response
app.on('error', function (err, ctx) {
    console.log(err);
});
module.exports = app;