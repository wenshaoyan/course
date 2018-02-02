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
const services = require('../services/topic');

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
        ctx.poolTag = SysUtil.getUuid();
        await next();
        myServer.release(ctx.poolTag);
    }
});
router.get('/', async (ctx, next) => {
    const params = new AbstractSqlBean(ctx.querySql);
    const json = {
        data: {
            service: 'selectTopicOptionBanks',
            args: {
                abstractSqlBean: params
            },
            fields: {
                topics: {
                    service: 'selectTopics',
                    fields: {
                        topicOptions: {
                            service: 'selectTopicOptions'
                        }
                    }
                }
            }
        }
    };
    ctx.serviceJson = json;
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
router.use(async (ctx, next) => {
    ctx.body = await createTask({}, ctx.serviceJson, ctx);;
});

/**
 * 创建任务
 * @param root      当前字段的上级对象 第一级为{}
 * @param fields    字段集合
 * @param ctx
 * @return {Promise<{}>}
 */
async function createTask(root, fields, ctx) {
    const resultData = {};
    const list = [];
    const listMap = new Map();
    let i = 0;
    for (const name in fields) {
        listMap.set(i, name);
        list.push(execTask(fields[name], name, root, ctx));
        i++;
    }
    const result = await Promise.all(list);
    for (const j in result) {
        resultData[listMap.get(+j)] = result[j];
    }
    return resultData
}

/**
 * 执行任务
 * @param field     field为对应到的service中的对象
 * @param name      field的名称
 * @param root      当前字段的上级对象 第一级为{}
 * @param ctx
 * @return {Promise<*>}
 */
async function execTask(field, name, root, ctx) {
    let args = {};

    if (!services[field.service]) {
        console.error(field, field.service + ' not found');
        throw new Error(field.service + ' not found');
    }
    const currentService = services[field.service];
    if (currentService.args) {
        for (const k in currentService.args) {
            args[k] = null;
        }
    }
    if (field.args) { // 有参数
        args = field.args;
    }
    const extend = {thrift: {}, http: {}};
    if (typeof currentService === 'string') {
        const v = currentService.thrift;
        if (!getThrift(v)) {
            console.error(`${v} not in thrift `);
            throw new Error(`${v} not in thrift `);
        }
        extend.thrift[v] = await getThrift(v).getClient(ctx.poolTag);
    } else if (currentService.thrift instanceof Array) {
        for (const v of currentService.thrift) {
            if (!getThrift(v)) {
                console.error(`${v} not in thrift `);
                throw new Error(`${v} not in thrift `);
            }
            extend.thrift[v] = await getThrift(v).getClient(ctx.poolTag);
        }
    }
    const newRoot = await currentService.resolve(root, args, extend, ctx);

    for (const parent of newRoot) {
        if (field.fields) {
            const children = await createTask(parent, field.fields, ctx);
            for (const childrenKey in children) {
                parent[childrenKey] = children[childrenKey];
            }
        }
    }
    return newRoot;
}

module.exports = router;