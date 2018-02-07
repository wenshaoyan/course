const {Node, ID} = require('./interfaces');
const {GraphQLObjectType, GraphQLString, GraphQLNonNull,GraphQLInt,GraphQLList} = require('graphql');


const TopicOption = new GraphQLObjectType({
    name: 'TopicOption',
    description: '題目对应的选项',
    interfaces: [Node],
    fields: {
        _sid: {
            type: new GraphQLNonNull(ID),
            resolve(){
                return true;
            }
        },
        to_id: {
            type: GraphQLInt,
        },
        to_context: {
            type: GraphQLString,
        },
        to_topic_id: {
            type: GraphQLInt,
        },
        to_create_time: {
            type: GraphQLString,
        },
        to_update_time: {
            type: GraphQLString,
        }
    }
});
const Topic = new GraphQLObjectType({
    name: 'Topic',
    description: '题目',
    interfaces: [Node],
    fields: {
        _sid: {
            type: new GraphQLNonNull(ID),
            resolve(){
                return true;
            }
        },
        topic_id: {
            type: GraphQLInt
        },
        topic_title: {
            type: GraphQLString
        },
        topicOptions: {
            type: new GraphQLList(TopicOption)
        }
    }
});
const TopicBank = new GraphQLObjectType({
    name: 'TopicBank',
    description: '题库',
    interfaces: [Node],
    fields: {
        _sid: {
            type: new GraphQLNonNull(ID),
            resolve(){
                return true;
            }
        },
        name: {
            type:GraphQLString,
        }
    }
});
module.exports = {Topic, TopicOption, TopicBank};
