/**
 * Created by yanshaowen on 2017/10/25
 * 题库
 */
'use strict';
const router = require('koa-router')();
const {TopicBank, Custom} = require('../gen-nodejs/bean_types');
const {AbstractSqlBean, getThrift} = require('thrift-node-core');
const SysUtil = require('../util/sys_util');
const CommonService = 'CommonService';
const services = require('./topic');

function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

function p(str, data) {
    console.log(new Date().getTime());
    str = trim(str);
    //console.error(str);
    let KS = 0; // 返回值key名称的开始位置
    let SS = 0; // 对应的service名称的开始位置
    let CA = 0; // 服务对应的args的

    let kN = null; // 返回值当前的key名称
    let SN = null; // 对应的当前service名称
    let r = {};
    for (let i = 0; i < str.length; i++) {
        const v = str[i];
        if (v === ':') {
            kN = str.slice(KS, i);
            KS = i;
        }
        if (v === '(') {
            SN = str.slice(SS, i);
            SS = i;
            if (!kN) kN = SN;
            // 找到下一个右括号
            let j;
            let o = {};
            let ak = '';    // args的key
            let av = '';    // args的value

            let isTrue = false;
            for (j = i + 1; j < str.length; j++) {
                let cv = str[j];

                let isKey = true;
                if (cv === ')') {
                    isTrue = true;
                    break;
                } else if (cv === ',') {
                    isKey = true;
                    ak = '';
                    if (/^'.*'&/.test(av)) {
                        o[ak] = av;
                    } else if (!isNaN(av)) {
                        o[ak] = +av;
                    } else if (data[av]) {
                        o[ak] = data[av];
                    } else {
                        console.error('data not found ' + av);
                    }
                } else if (cv === ':') {
                    isKey = false;
                    av = '';
                } else {
                    console.log(cv)
                    if (isKey) ak += cv;
                    else av += cv;
                }
            }
            console.log(av, av)
            if (ak !== '' && av !== '') {
                if (/^'.*'&/.test(av)) {
                    o[ak] = av;
                } else if (!isNaN(av)) {
                    o[ak] = +av;
                } else if (data[av]) {
                    o[ak] = data[av];
                } else {
                    console.error('data not found ' + av);
                }
            }
            if (!isTrue) {
                console.error('没有右括号');
            }
            i++;
            if (j > i) {
                // str.slice(i, j);
                console.log(o)
            } else {
                console.error('空的小括号');
            }
            i = j + 1;
        } else if (v === ')') {
            console.error("=======");
        } else if (v === '{' && str[str.length - 1] === '}') {   // 下一个服务

        }
    }
    console.log(new Date().getTime());

}

/**
 * TEST(`
 b:selectTopicOptionBanks(abstractSqlBean:a){
                selectTopics{
                    selectTopicOptions
                }
            }
 c:selectTopicOptionBanks(abstractSqlBean:a){
                selectTopics{
                    selectTopicOptions
                }
            }
 `, {a})
 * @param a
 * @param data
 * @return {Promise<void>}
 * @constructor
 */
async function TEST(a, data) {
    p(a, data);
    /*if (!(a instanceof  services.selectOptions.args.type)) {
        console.error('services.selectOptions.args.type error');
        ctx.error = 'SERVICE_ERROR';
    } else if (!(services.selectOptions.resolve instanceof Function)) {
        ctx.error = 'SERVICE_ERROR';
    } else {
        const extend = {thrift: {}, http: {}};
        if (typeof services.selectOptions.thrift === 'string') {
            const v = services.selectOptions.thrift;
            if (!getThrift(v)) {
                console.error(`${v} not in thrift `);
                throw new Error(`${v} not in thrift `)
            }
            extend.thrift[v] = await getThrift(v).getClient(ctx.poolTag);
        } else if (services.selectOptions.thrift instanceof Array) {
            for (const v of services.selectOptions.thrift) {
                if (!getThrift(v)) {
                    console.error(`${v} not in thrift `);
                    throw new Error(`${v} not in thrift `)
                }
                extend.thrift[v] = await getThrift(v).getClient(ctx.poolTag);
            }
        }
        return await services.selectOptions.resolve({}, a, extend , ctx);
    }*/
}

router.use(async (ctx, next) => {
    const myServer = getThrift(CommonService);
    if (myServer.connectionStatus !== 1) {   // 检查thrift连接状态
        ctx.error = 'THRIFT_CONNECT_ERROR';
    } else {
        await next();
    }
});
router.get('/', async (ctx, next) => {
    const params = new AbstractSqlBean(ctx.querySql);
    if (!(params.limit instanceof Array) || params.limit.length === 0) {
        params.limit = [2];
    }
    ctx.methodJson = {
        data: {
            method: 'selectTopicOptionBanks',
            args: {
                abstractSqlBean: params
            },
            fields: {
                topics: {
                    method: 'selectTopics',
                    fields: {
                        topicOptions: {
                            method: 'selectTopicOptions'
                        }
                    }
                }
            }
        }
    };
    await next();
});
router.get('/counts', async (ctx, next) => {
    const params = ctx.querySql;
    // params.mode = 'topic+option';
    try {
        const client = await getThrift(CommonService).getClient(ctx.poolTag);
        const count = await client.topicBankCount(new AbstractSqlBean(params));
        ctx.body = {count};
    } catch (e) {
        ctx.error = e;
    }
});
router.post('/', async (ctx, next) => {
    const params = ctx.request.body;
    try {
        const client = await getThrift(CommonService).getClient(ctx.poolTag);
        ctx.body = await client.topicBankInsert(new TopicBank(params));
    } catch (e) {
        ctx.error = e;
    }
});
// 关联题目
router.patch('/topics', async (ctx, next) => {
    const params = ctx.request.body;
    try {
        const client = await getThrift(CommonService).getClient(ctx.poolTag);
        ctx.body = await client.topicBankAddTopic(params.tb_id, params.topic_id);
    } catch (e) {
        ctx.error = e;
    }
});
// 删除关联
router.delete('/topics', async (ctx, next) => {
    const params = ctx.request.body;
    try {
        const client = await getThrift(CommonService).getClient(ctx.poolTag);
        ctx.body = await client.topicBankRemoveTopic(params.tb_id, params.topic_id);
    } catch (e) {
        ctx.error = e;
    }
});
module.exports = router;