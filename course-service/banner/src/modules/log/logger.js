/**
 * Created by wenshao on 2017/9/8.
 */
'use strict';


function format (now,fmt) { //author: meizz
    const o = {
        "M+": now.getMonth() + 1, //月份
        "d+": now.getDate(), //日
        "h+": now.getHours(), //小时
        "m+": now.getMinutes(), //分
        "s+": now.getSeconds(), //秒
        "q+": Math.floor((now.getMonth() + 3) / 3), //季度
        "S": now.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (now.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (let k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
const dateFormat = "[yyyy-MM-dd-hh:mm:ss.S]";
/**
 * 格式化输出日志
 * @param args      用户需要输出参数 arguments
 * @param level     级别
 * @return Array.<T>      数组
 */
const formatTing = (args,level)=>{
    let arr = Array.prototype.slice.call(args);
    arr.unshift(format(new Date(),dateFormat),`[${level.toLocaleUpperCase()}]`,'[default]','-');
    return arr;
};
class Logger{
    // 调式级别
    static trace(){
        console.log(...formatTing(arguments,'trace'));
    }
    // 调式级别
    static debug(){
        console.log(...formatTing(arguments,'debug'));
    }
    // 输出级别
    static info(){
        console.log(...formatTing(arguments,'info'));
    }
    // 警告级别
    static warn(){
        console.log(...formatTing(arguments,'warn'));
    }
    // 错误级别
    static error(){
        console.log(...formatTing(arguments,'error'));
    }
    // 重大错误级别
    static fatal(){
        console.log(...formatTing(arguments,'fatal'));
    }
}
module.exports = Logger;