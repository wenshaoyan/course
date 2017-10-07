package com.wenshao.dal.server;


import com.wenshao.dal.handler.ClientHandler;
import com.wenshao.dal.thriftgen.ClientService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by wenshao on 2017/9/28
 */
public class ClientServer {

    public static void main(String[] args) {
        try {
            String resource = "mybatis-client-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            //构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

            ClientHandler handler = new ClientHandler(sessionFactory);
            ClientService.Processor processor = new ClientService.Processor(handler);
            Server server = new Server(processor, 9092);
            server.startService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
