const {TopicBank} = require('../gen-nodejs/bean_types');
const {AbstractSqlBean} = require('thrift-node-core');
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
        thrift: ['CommonService'],
        resolve: function (root, args, extend, ctx) {
            return extend.thrift.CommonService.topicOptionSelect(new AbstractSqlBean({
                where: {
                    to_topic_id: {eq: root.topic_id, type: 'number'}
                }
            }));
        }
    },
    selectTopics: {
        type: TopicBank,
        description: '按规则获取题目',
        thrift: ['CommonService'],
        resolve: function (root, args, extend, ctx) {
            return extend.thrift.CommonService.topicSelect(new AbstractSqlBean({
                where: {
                    tr_tb_id: {eq: root.tb_id, type: 'number'}
                },
                mode: 'topic_relation'
            }));
        }
    }
};