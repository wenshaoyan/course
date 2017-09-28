package com.wenshao.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import java.net.URL;

/**
 * Created by wenshao on 2017/9/27
 */
public class EhCache2 {
    public static void main(String[] args){

        URL myUrl = EhCache2.class.getResource("/ehcache.xml");
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        CacheManager myCacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        myCacheManager.init();
        Cache<String, String> foo = myCacheManager.getCache("foo", String.class, String.class);

        // 写
        for (int i=0;i<=200000000;i++){
            foo.put(String.valueOf(i), "$"+i);
            System.out.println(String.valueOf(i));
        }
        // 读
        for (int i=0;i<=200000;i++){
            String value = foo.get(String.valueOf(i));
            System.out.println("get at "+i+":"+value);
        }
        myCacheManager.close();


    }
}
