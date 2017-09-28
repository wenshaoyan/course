package com.wenshao.dal.server;


import com.wenshao.dal.handler.BannerHandler;
import com.wenshao.dal.handler.UserHandler;
import com.wenshao.dal.thriftgen.BannerService;
import com.wenshao.dal.thriftgen.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by wenshao on 2017/9/28
 */
public class BannerServer{

    public static void main(String[] args) {
        try {
            String resource = "mybatis-banner-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            //构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

            BannerHandler handler = new BannerHandler(sessionFactory);
            BannerService.Processor processor = new BannerService.Processor(handler);
            Server server = new Server(processor, 9091);
            server.startService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
