package com.wenshao.dal.server;

import com.wenshao.dal.handler.DynamicCacheProxyHandler;
import com.wenshao.dal.handler.UserHandler;
import com.wenshao.dal.thriftgen.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by wenshao on 2017/9/4
 * 所有服务 解决内存不足
 */
public class AllServer {
    public static void main(final String[] args) {
        new Thread(){
            public void run() {
                UserServer.main(args);
            }
        }.start();
        new Thread(){
            public void run() {
                BannerServer.main(args);
            }
        }.start();
        new Thread(){
            public void run() {
                ClientServer.main(args);
            }
        }.start();
        new Thread(){
            public void run() {
                CourseServer.main(args);
            }
        }.start();
    }
}
