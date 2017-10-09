package com.wenshao.cache.server;

import com.wenshao.cache.EhCache2;
import com.wenshao.cache.bean.ListBanner;
import com.wenshao.cache.handler.CacheHandler;
import com.wenshao.cache.thriftgen.CacheService;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;

/**
 * Created by wenshao on 2017/10/8.
 */

public class CacheServer{

    public static void main(String[] args) {
        CacheManager cacheManager = initCacheConfig();
        CacheHandler handler = new CacheHandler(cacheManager);
        CacheService.Processor processor = new CacheService.Processor(handler);
        Server server = new Server(processor, 20001);
        server.startService();
    }

    private static CacheManager initCacheConfig() {
        URL myUrl = EhCache2.class.getResource("/ehcache.xml");
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        CacheManager myCacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        myCacheManager.init();
        return myCacheManager;
    }
}