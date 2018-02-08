/**
 * Created by wenshao on 2018/02/08
 * topic相关的操作
 */
'use strict';

const {getThrift} = require('thrift-node-core');
const CommonService = 'CommonService';

module.exports = {

    TopicBank: {
        _sid() {
            return '1';
        },
        topics(root, args, context) {
            const client = getThrift(CommonService).getProxyClient();
            return client.topicSelect();
        }
    },
    Topic: {
        _sid() {
            return '2';
        },
        topicOptions(root, args, context) {
            const client = getThrift(CommonService).getProxyClient();
            return client.topicOptionSelect();
        }

    }
};