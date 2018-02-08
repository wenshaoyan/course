/**
 * Created by wenshao on 2018/02/08
 * topic相关的操作
 */
'use strict';

const {getThrift} = require('thrift-node-core');
const CommonService = 'CommonService';
module.exports = {
    topicBanks(root, args, context) {
        const client = getThrift(CommonService).getProxyClient();
        return client.topicBankSelect();
    },
    topics() {
        const client = getThrift(CommonService).getProxyClient();
        return client.topicSelect();
    },
    topicOptions() {
        const client = getThrift(CommonService).getProxyClient();
        return client.topicOptionSelect();
    }
};
