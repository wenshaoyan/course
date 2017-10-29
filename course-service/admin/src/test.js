/**
 * Created by yanshaowen on 2017/10/27
 */
'use strict';
const MultiWayTree = require('./lib/multi_way_tree');
let result = require('../assets/test.json');
let multiWayTree = new MultiWayTree("code");
result.RECORDS.forEach((value) => {
    console.log(value)
    multiWayTree.insert(value);
});
let toArray = multiWayTree.toArray((value) => {
    value.role = [
        {
            "name": value.user_title,
            "code": value.user_id,
            "fathername": value.code
        }
    ];
    delete value.user_title;
    delete value.user_id;
});
console.log(JSON.stringify(toArray))

