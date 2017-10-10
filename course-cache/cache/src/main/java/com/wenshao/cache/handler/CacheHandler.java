package com.wenshao.cache.handler;

import com.wenshao.cache.thriftgen.Banner;
import com.wenshao.cache.thriftgen.BannerList;
import com.wenshao.cache.thriftgen.CacheService;
import org.apache.thrift.TException;
import org.ehcache.Cache;
import org.ehcache.CacheManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenshao on 2017/10/8.
 * 缓存
 */
public class CacheHandler implements CacheService.Iface {
    private CacheManager mCacheManager;

    public CacheHandler(CacheManager cacheManager) {
        this.mCacheManager = cacheManager;
    }

    public void bannerPut(String key, BannerList banners) throws TException {

        Cache<String, BannerList> bannerCache = this.mCacheManager.getCache("banner", String.class, BannerList.class);
        bannerCache.put(key, banners);
        System.out.println("设置缓存成功");
    }

    public BannerList bannerGet(String key) throws TException {
        Cache<String, BannerList> bannerCache = this.mCacheManager.getCache("banner", String.class, BannerList.class);
        BannerList bannerList = new BannerList();
        List<Banner> banners = new ArrayList<Banner>();
        Banner banner = new Banner();
        banner.setId(1);
        banners.add(banner);
        bannerList.setData(banners);
        System.out.println("获取缓存成功");

        return bannerCache.get(key);
//        return bannerList;
    }


}
