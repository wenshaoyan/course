/**
 * Created by wenshao on 2018/02/09.
 * graphql schema 工具
 */
const {start, normalMergeDirFile, normalMergeDirMethod, baseSchemaString} = require('thrift-node-core');
const {makeExecutableSchema, mergeSchemas} = require('graphql-tools');

const typeDefs = normalMergeDirFile('../graphql-types');
const resolvers = {};
resolvers.Query = normalMergeDirMethod('../graphql-resolvers/queries');
resolvers.Mutation = normalMergeDirMethod('../graphql-resolvers/mutations');
const resolve = normalMergeDirMethod('../graphql-resolvers/resolvers');
Object.keys(resolve).map((key) => {
    if (key in resolvers) throw new Error(`graphql-schema-util:${key} in resolvers is exist`);
    resolvers[key] = resolve[key];
});
const str = baseSchemaString();
const baseSchema = makeExecutableSchema({
    typeDefs: str.typeDefs,
    resolvers: str.resolvers
});
const executableSchema = makeExecutableSchema({
    typeDefs: typeDefs,
    resolvers
});

const mergedSchema = mergeSchemas({
    schemas: [baseSchema, executableSchema]
});

// 找到所有实现了Node接口的对象 为其添加__sid的值

class Schema {
    static getGraphqlSchema() {
        return mergedSchema;
    };
}

module.exports = Schema;