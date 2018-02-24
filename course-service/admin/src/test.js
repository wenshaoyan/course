console.log(JSON.stringify({
    "user": {
        "client": {
            "deep": 10,
            "concurrency": 5
        },
        "admin": {
            "deep": 10,
            "concurrency": 5
        },
        "service": {
            "deep": 10,
            "concurrency": 5
        }
    }
}));
a = {
    admin: {
        path: '/1/2/3/4',
        id: '4',
        data: '1',
        nodes: {
            admin: {
                path: '/1/2/3/4',
                id: '4',
                data: '1',
                nodes: {}
            },
        }
    },
    admin1: {}
};
b = {
    "path": "/develop/graphql",
    "data": "",
    "nodes": {
        "admin": {
            "path": "/develop/graphql/admin",
            "id": "admin",
            "data": "{\"user\":{\"client\":{\"deep\":10,\"concurrency\":5},\"admin\":{\"deep\":10,\"concurrency\":5},\"service\":{\"deep\":10,\"concurrency\":10}}}",
            "nodes": {}
        }
    }
}

