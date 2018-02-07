const {GraphQLInterfaceType, GraphQLScalarType, GraphQLNonNull} = require('graphql');
const getID = function () {
    return Math.random().toString(36).substr(2);
};
const ID = new GraphQLScalarType({
    name: 'ID',
    serialize: getID,
    parseValue: getID,
    parseLiteral:getID
});

const Node = new GraphQLInterfaceType({
    name: 'Node',
    description: 'An object with an ID',
    fields: {
        _sid: {
            type: new GraphQLNonNull(ID)
        }
    }
});
module.exports = {Node, ID};