package com.wenshao.cache.handler;

import com.wenshao.cache.thriftgen.Banner;
import com.wenshao.cache.thriftgen.BannerList;
import com.wenshao.cache.thriftgen.CacheService;
import org.apache.log4j.Logger;
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
    private static Logger logger = Logger.getLogger(String.valueOf(CacheHandler.class));
    private CacheManager mCacheManager;

    public CacheHandler(CacheManager cacheManager) {
        this.mCacheManager = cacheManager;
    }

    public void bannerPut(String key, BannerList banners) throws TException {
        Cache<String, BannerList> bannerCache = this.mCacheManager.getCache("banner", String.class, BannerList.class);
        logger.debug("设置缓存:"+key);
        bannerCache.put(key, banners);
    }

    public BannerList bannerGet(String key) throws TException {
        Cache<String, BannerList> bannerCache = this.mCacheManager.getCache("banner", String.class, BannerList.class);
        logger.debug("获取缓存:"+key);
        return bannerCache.get(key);
    }


}
