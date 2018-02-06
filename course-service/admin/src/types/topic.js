const {GraphQLString, GraphQLObjectType, GraphQLNonNull, GraphQLFloat,
GraphQLList,GraphQLInterfaceType,GraphQLScalarType,Kind} = require('graphql');

const OddType = new GraphQLScalarType({
    name: 'Odd',
    serialize: oddValue,
    parseValue: oddValue,
    parseLiteral(ast) {
        if (ast.kind === Kind.INT) {
            return oddValue(parseInt(ast.value, 10));
        }
        return 2;
    }
});
// 只返回奇数
function oddValue(value) {
    return value % 2 === 1 ? value : null;
}
const Queries = {
    Test: {
        type: new GraphQLObjectType({
            name: 'TestResult',
            description:'111',
            fields: {
                a: {
                    type: GraphQLString
                }
            }
        }),
        args: {
            episode: {
                type: new GraphQLNonNull(GraphQLString),
                description:'episode'
            }
        },
        description: '查询某个商品最新的订单信息',
        resolve: (root, args, ctx) => {
            return '1';
        }
    },
    Human: {
        type:new GraphQLList(new GraphQLObjectType({
            name: 'human',
            description: 'human desc',
            fields:{
                name: {
                    type: GraphQLString,
                },
                height: {
                    type: GraphQLFloat,
                    resolve:(root,args,context, info)=> {
                        return 1;
                    }
                },
                v: {
                    type: OddType
                }
            }
        })),
        args:{
            v1: {
                type: OddType
            }
        },
        description: 'Human test',
        resolve: (root, args, context) => {
            //console.log(context)
            return [{
                name: '1',
                height: 1.1,
                v: 11
            },{
                name: '2',
                height: 1.1,
                v: 11
            }]
        }
    }
};
const Mutations = {
    Test1: {
        type: GraphQLString,
        description: '查询某个商品最新的订单信息',
        resolve: (root, args, ctx) => {
            return '1';
        }
    }
};
module.exports = {
    Queries,
    Mutations
};