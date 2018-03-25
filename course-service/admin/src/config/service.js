module.exports = {
    "node_env": NODE_ENV,
    "core_log": getLogger('core'),
    "zk": {
        "url": process.env.ZK_URL,
        "register": [
            {
                "path": `/${NODE_ENV}/graphql/admin`,
                "id": `${process.env.APP_IP}:${process.env.APP_PORT}`,
                "data": {

                }
            },
            {
                "path": `/${NODE_ENV}/restful/admin`,
                "id": `${process.env.APP_IP}:${process.env.APP_PORT}`,
                "data": {

                }
            },
        ]
    },
    "thrift": {
        "timeout": 10000,
        "poolMax": 2,
        "poolMin": 1,
        "log": getLogger('thrift'),
        "rootPath": "/develop/thrift",
        "tree": {
            "UserService": {
                "object": require('../gen-nodejs/UserService')
            },
            "BannerService": {
                "object": require('../gen-nodejs/BannerService')
            },
            "ClientService": {
                "object": require('../gen-nodejs/ClientService')
            },
            "CourseService": {
                "object": require('../gen-nodejs/CourseService')
            },
            "CommonService": {
                "object": require('../gen-nodejs/CommonService')
            }
        }
    },
    "web": {
        "port": process.env.APP_PORT,
        "app": require('../app')
    },
    "amq": {    // 可选
        "mail": {
            "topic": "basis-mail",
            "host": process.env.KAFKA_URL,
            "type": "kafka"
        }
    }
};