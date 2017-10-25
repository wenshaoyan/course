/**
 * Created by yanshaowen on 2017/8/29.
 * 参数值类型定义
 *
 */
const publicUtil = {
    tel: {            // 电话
        type: "string",
        pattern: "^1[0-9]+$",
        maxLength: 11,
        minLength: 11
    },
    password: {       // 密码
        type: "string",
        pattern: "\\w+",
        minLength: 6,
        maxLength: 12
    },
    id: {           // 纯数字id
        type: "number",
        pattern: "^[1-9][0-9]*$",
    },
    idString: {           // 纯数字id 字符串型
        type: "string",
        pattern: "^[1-9][0-9]*$",
    },
    uuid: {          // uuid
        type: "string",
    },
    name: {         // 用户名称
        type: "string",
        maxLength: 12
    },
    http_url: {      // url地址
        type: "string",
        Formats: "url",
    },
    id_card: {       // 省份证号码
        type: "string",
        pattern: "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)"
    },
    area: {          // 地区
        type: "string",
        minLength: 1,
        maxLength: 50
    },
    string225: {     // 225长度字符串
        type: "string",
        maxLength: 255
    },
    string500: {     // 500长度字符串
        type: "string",
        maxLength: 500
    },
    userId: {        // 用户id
        type: "string",
        maxLength: 50
    },
    status: {        // 状态
        type: "number"
    },
    arrayByString: { // 字符串元素的数组
        type: "array"
    },
    num: {           // number
        type: "number",
    },
    stringOrNull: {  // 字符串或者null
        type: ["string", "null"]
    },
    eventStatus: {   // 事件的状态
        "enum": ["await", "proceed", "end"],
    },
    bool: {          // 布尔值
        type: "boolean"
    },
    date: {          // 时期
        type: "string",
        pattern: "^\\d{4}-\\d{2}$"
    },
    appType: {           // App类型
        "enum": ["app"],
    },
    deviceType: {        // 设置类型
        "enum": ["android", "ios"],
    },
    text: {
        type: "string"
    },
    limit: {
        type: "string",
        pattern: "^[1-9][0-9]*$",
    },
    offset: {
        type: "string",
        pattern: "^[1-9][0-9]*$",
    },
    page: {
        type: "string",
        pattern: "^(0|[1-9][0-9]*)$",
    },
    limit: {
        type: "string",
        pattern: "^[1-9][0-9]*$",
    },
    sort_by: {
        type: "string",
        minLength: 1,
        maxLength: 100
    },
    order: {
        "enum": ["asc", "desc"],
    },
    action:{
        "enum": ["paging", "search"],
    }

};
module.exports = publicUtil;