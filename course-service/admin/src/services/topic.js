const {TopicBank} = require('../gen-nodejs/bean_types');
const {AbstractSqlBean, getThrift} = require('thrift-node-core');
module.exports = {
    selectTopicOptionBanks: {
        type: TopicBank,
        description: '按规则获取题库',
        args: {
            abstractSqlBean: {
                type: AbstractSqlBean
            }
        },
        thrift: ['CommonService'],
        resolve: function (root, args, extend, ctx) {
            return extend.thrift.CommonService.topicBankSelect(args.abstractSqlBean);
        }
    },
    selectTopicOptions: {
        type: TopicBank,
        description: '按规则获取选项',
        args: {
            abstractSqlBean: {
                type: AbstractSqlBean
            }
        },
        thrift: ['CommonService'],
        resolve: function (root, args, extend, ctx) {
            return extend.thrift.CommonService.topicOptionSelect();
        }
    },
    selectTopics: {
        type: TopicBank,
        description: '按规则获取题目',
        args: {
            abstractSqlBean: {
                type: AbstractSqlBean
            }
        },
        thrift: ['CommonService'],
        resolve: function (root, args, extend, ctx) {
            return extend.thrift.CommonService.topicSelect();
        }
    }
};