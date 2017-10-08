package com.wenshao.dal.server;


import com.wenshao.dal.bean.CacheXmlBean;
import com.wenshao.dal.handler.BannerHandler;
import com.wenshao.dal.handler.DynamicCacheProxyHandler;
import com.wenshao.dal.thriftgen.BannerService;
import com.wenshao.dal.util.JaxbUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

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
            // 创建代理对象
            DynamicCacheProxyHandler dynamicCacheProxyHandler = new DynamicCacheProxyHandler();
            BannerService.Iface handler = new BannerHandler(sessionFactory);
            BannerService.Iface handlerProxy = (BannerService.Iface)dynamicCacheProxyHandler.bind(handler);
            BannerService.Processor processor = new BannerService.Processor(handlerProxy);
            // 设置缓存对象
            Reader resourceAsReader = Resources.getResourceAsReader("cache/bannerCache.xml");
            
            Server server = new Server(processor, 9091,resourceAsReader);
            server.startService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
