module.exports = {
    "node_env": process.env.NODE_ENV,
    "core_log": getLogger('core'),
    "zk": {
        "url": process.env.ZK_URL,
        "register": [
            {
                "path": "/develop/http/admin",
                "id": "127.0.0.1:9000",
                "data": "127.0.0.1:9000"
            }
        ]
    },
    "thriftGlobal": {
        "timeout": 10000,
        "poolMax": 5,
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
        "port": 9000,
        "app": require('../app')
    }
}