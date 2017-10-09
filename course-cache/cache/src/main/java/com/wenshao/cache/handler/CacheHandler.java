package com.wenshao.cache.handler;

import com.wenshao.cache.bean.ListBanner;
import com.wenshao.cache.thriftgen.Banner;
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

    public void bannerPut(String key, List<Banner> banners) throws TException {
        ListBanner listBanner = new ListBanner();
        listBanner.setBanners(banners);
        Cache<String, ListBanner> bannerCache = this.mCacheManager.getCache("banner", String.class, ListBanner.class);
        bannerCache.put(key, listBanner);

    }

    public List<Banner> bannerGet(String key) throws TException {
        Cache<String, ListBanner> bannerCache = this.mCacheManager.getCache("banner", String.class, ListBanner.class);
        ListBanner listBanner = bannerCache.get(key);
        if (listBanner != null) {
            return listBanner.getBanners();
        }
        return null;
    }
}
