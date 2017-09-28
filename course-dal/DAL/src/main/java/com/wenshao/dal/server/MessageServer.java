package com.wenshao.dal.server;

import com.wenshao.dal.handler.MessageHandler;
import com.wenshao.dal.thriftgen.MessageService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by wenshao on 2017/9/6
 */
public class MessageServer {
    public static void main(String[] args) {
        try {
            String resource = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            //构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            MessageHandler handler = new MessageHandler();
            MessageService.Processor processor = new MessageService.Processor(handler);
            Server server = new Server(processor, 9091);
            server.startService();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
