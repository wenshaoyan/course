const {
    GraphQLString,
    GraphQLObjectType,
    GraphQLNonNull,
    GraphQLFloat,
    GraphQLList,
    GraphQLInterfaceType,
    GraphQLScalarType,
    Kind
} = require('graphql');
const {Topic, TopicBank, TopicOption} = require('../types/objects');
const {getThrift} = require('thrift-node-core');

const Queries = {
    topics:{
        name:'topics',
        type: new GraphQLList(Topic),
        description: '获取题目列表',
        resolve:async (root, args, {ctx}) => {
            // const CommonClient = getThrift('CommonService').getProxyClient();
            // const result = await CommonClient.topicSelect();
            // console.log(result);
            return  [ { topic_id: 6,
                topic_title: '1',
                topic_type: '1',
                topic_analysis: '1',
                topic_correct_answer: '1',
                topic_score: 1,
                topic_create_time: '2018-01-20 17:05:08',
                topic_update_time: '2018-01-20 17:05:11',
                topicOptions: [] },
                { topic_id: 7,
                    topic_title: '1',
                    topic_type: '1',
                    topic_analysis: '1',
                    topic_correct_answer: '1',
                    topic_score: 1,
                    topic_create_time: '2018-01-20 17:05:08',
                    topic_update_time: '2018-01-20 17:05:11',
                    topicOptions: [] } ];
        }
    }
};
const Mutations = {
    topics1: {
        name: 'topics1',
        type: Topic,
        description: '获取题目列表',
        resolve: (root, args, {ctx}) => {
            return {};
        }
    }
};
module.exports = {Queries,Mutations};