/**
 * Created by wenshao on 2017/9/9.
 */
'use strict';
const os = require('os');


const localIp = ()=>{
    const interfaces = os.networkInterfaces();
    for(let devName in interfaces){
        let iface = interfaces[devName];
        for(let i=0;i<iface.length;i++){
            let alias = iface[i];
            if(alias.family === 'IPv4' && alias.address !== '127.0.0.1' && !alias.internal){
                return alias.address;
            }
        }
    }
};
class IpUtil{
    // 获取内网ip
    static getLocalIp(){
        return localIp();
    }
    static getNetIp(){

    }

}
module.exports = IpUtil;