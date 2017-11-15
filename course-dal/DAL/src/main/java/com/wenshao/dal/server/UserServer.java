package com.wenshao.dal.server;

import com.mongodb.client.MongoCollection;
import com.wenshao.dal.handler.DynamicCacheProxyHandler;
import com.wenshao.dal.handler.UserHandler;
import com.wenshao.dal.thriftgen.UserService;
import com.wenshao.dal.util.MongoDBUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.bson.Document;


import java.io.IOException;
import java.io.Reader;

/**
 * Created by wenshao on 2017/9/4
 * 用户服务
 */
public class UserServer {
    public static void main(String[] args) {
        try {
            String resource = "mybatis-user-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            //构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

            // 创建代理对象
            DynamicCacheProxyHandler dynamicCacheProxyHandler = new DynamicCacheProxyHandler();
            UserService.Iface handler = new UserHandler(sessionFactory);
            UserService.Iface handlerProxy = (UserService.Iface)dynamicCacheProxyHandler.bind(handler);
            UserService.Processor processor = new UserService.Processor(handlerProxy);

            Server server = new Server(processor, 9090);
            server.startService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
