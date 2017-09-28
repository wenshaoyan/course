package com.wenshao.dal.server;

import com.wenshao.dal.handler.UserHandler;
import com.wenshao.dal.thriftgen.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.thrift.transport.TTransportException;


import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wenshao on 2017/9/4
 */
public class UserServer {
    public static void main(String[] args) {
        try {
            String resource = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            //构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            UserHandler handler = new UserHandler(sessionFactory);
            UserService.Processor processor = new UserService.Processor(handler);
            Server server = new Server(processor, 9090);
            server.startService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
