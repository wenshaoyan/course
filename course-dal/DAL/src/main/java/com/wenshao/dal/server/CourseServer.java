package com.wenshao.dal.server;


import com.wenshao.dal.handler.CourseHandler;
import com.wenshao.dal.handler.DynamicCacheProxyHandler;
import com.wenshao.dal.thriftgen.CourseService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by wenshao on 2017/9/28
 * 课程服务
 */
public class CourseServer {

    public static void main(String[] args) {
        try {
            String resource = "mybatis-course-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            //构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            // 创建代理对象
            DynamicCacheProxyHandler dynamicCacheProxyHandler = new DynamicCacheProxyHandler();
            CourseService.Iface handler = new CourseHandler(sessionFactory);
            CourseService.Iface handlerProxy = (CourseService.Iface)dynamicCacheProxyHandler.bind(handler);
            CourseService.Processor processor = new CourseService.Processor(handlerProxy);

            
            Server server = new Server(processor, 9094);
            server.startService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
