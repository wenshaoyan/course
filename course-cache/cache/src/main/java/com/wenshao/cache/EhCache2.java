package com.wenshao.cache;

import com.wenshao.cache.bean.ListBanner;
import com.wenshao.cache.thriftgen.Banner;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenshao on 2017/9/27
 */
public class EhCache2 {
    public static void main(String[] args){

        URL myUrl = EhCache2.class.getResource("/ehcache.xml");
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        CacheManager myCacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        myCacheManager.init();
        Cache<String, ListBanner> foo = myCacheManager.getCache("foo", String.class, ListBanner.class);
        List<Banner> banners = new ArrayList<Banner>();
        Banner banner = new Banner();
        banner.setId(1);
        banners.add(banner);
        ListBanner listBanner = new ListBanner();
        listBanner.setBanners(banners);
        // 写
        for (int i=0;i<=20;i++){
            foo.put(String.valueOf(i), listBanner);
            System.out.println(String.valueOf(i));
        }
        // 读
        for (int i=0;i<=20;i++){
            ListBanner listBanner1 = foo.get(String.valueOf(i));
            System.out.println(listBanner1);
        }
        myCacheManager.close();


    }
}
