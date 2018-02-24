module.exports = {
    "node_env": process.env.NODE_ENV,
    "core_log": getLogger('core'),
    "zk": {
        "url": process.env.ZK_URL,
        "register": [
            {
                "path": "/develop/graphql/admin",
                "id": "127.0.0.1:9000",
                "data": {

                }
            }
        ]
    },
    "thriftGlobal": {
        "timeout": 10000,
        "poolMax": 50,
        "poolMin": 1,
        "log": getLogger('thrift')
    },
    "thrift": {
        "UserService": {
            "path": "/develop/thrift/UserService",
            "object": require('../gen-nodejs/UserService')
        },
        "BannerService": {
            "path": "/develop/thrift/BannerService",
            "object": require('../gen-nodejs/BannerService')
        },
        "ClientService": {
            "path": "/develop/thrift/ClientService",
            "object": require('../gen-nodejs/ClientService')
        },
        "CourseService": {
            "path": "/develop/thrift/CourseService",
            "object": require('../gen-nodejs/CourseService')
        },
        "CommonService": {
            "path": "/develop/thrift/CommonService",
            "object": require('../gen-nodejs/CommonService')
        }
    },
    "http": {},
    "web": {
        "port": process.env.APP_PORT,
        "app": require('../app')
    }
}