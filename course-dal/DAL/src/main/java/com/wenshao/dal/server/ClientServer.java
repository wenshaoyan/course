package com.wenshao.dal.server;


import com.wenshao.dal.bean.CacheXmlBean;
import com.wenshao.dal.handler.ClientHandler;
import com.wenshao.dal.handler.DynamicCacheProxyHandler;
import com.wenshao.dal.thriftgen.ClientService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

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

            // 创建代理对象
            DynamicCacheProxyHandler dynamicCacheProxyHandler = new DynamicCacheProxyHandler();
            ClientService.Iface handler = new ClientHandler(sessionFactory);
            ClientService.Iface handlerProxy = (ClientService.Iface)dynamicCacheProxyHandler.bind(handler);
            ClientService.Processor processor = new ClientService.Processor(handlerProxy);


            Server server = new Server(processor, 9092);
            server.startService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
