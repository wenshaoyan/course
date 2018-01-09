package com.wenshao.dal.server;

import com.wenshao.dal.handler.CommonHandler;
import com.wenshao.dal.handler.DynamicCacheProxyHandler;
import com.wenshao.dal.thriftgen.CommonService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by wenshao on 2018/1/9.
 * 公共服务
 */
public class CommonServer {
    public static void main(String[] args) {
        try {
            String resource = "mybatis-common-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            //构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

            // 创建代理对象
            DynamicCacheProxyHandler dynamicCacheProxyHandler = new DynamicCacheProxyHandler();
            CommonService.Iface handler = new CommonHandler(sessionFactory);
            CommonService.Iface handlerProxy = (CommonService.Iface)dynamicCacheProxyHandler.bind(handler);
            CommonService.Processor processor = new CommonService.Processor(handlerProxy);

            Server server = new Server(processor, 9095);
            server.startService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

