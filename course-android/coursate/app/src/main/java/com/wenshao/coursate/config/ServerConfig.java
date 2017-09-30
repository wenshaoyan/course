package com.wenshao.coursate.config;

/**
 * Created by wenshao on 2017/9/23.
 * 服务器请求地址
 */

public class ServerConfig {
    /**
     * http请求地址 端口
     */
    private final static String HTTP_ADDRESS = "http://123.207.55.204";
    private final static String HTTP_PORT = "8082";

    //private final static String HTTP_ADDRESS = "http://192.168.1.100";
    //private final static String HTTP_PORT = "10001";
    /**
     *
     * user 服务接口  restful api
     * 登录  get params={"name":"test",password:"aaa"}
     * 注册  post params={"name":"test",password:"aaa","email":"123@qq.com","tel":"1222333"}
     * 修改密码
     */
    public final static String HTTP_USER_V1 = HTTP_ADDRESS + ":" + HTTP_PORT + "/api/user/v1/user";
//    public final static String HTTP_USER_V1 = HTTP_ADDRESS + ":" + HTTP_PORT + "/user";
    /*// 登录接口
    public final static String HTTP_USER_LOGIN= HTTP_USER_V1+"login";
    // 注册接口
    public final static String HTTP_USER_REGISTER= HTTP_USER_V1+"register";*/


}
