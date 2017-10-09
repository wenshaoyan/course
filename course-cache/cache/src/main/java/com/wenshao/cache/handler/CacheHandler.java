package com.wenshao.cache.handler;

import com.wenshao.cache.thriftgen.Banner;
import com.wenshao.cache.thriftgen.CacheService;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenshao on 2017/10/8.
 */
public class CacheHandler implements CacheService.Iface{
    public void bannerPut(String key, List<Banner> banners) throws TException {

    }

    public List<Banner> bannerGet(String key) throws TException {

        ArrayList<Banner> banners = new ArrayList<Banner>();
        Banner banner = new Banner();
        banner.id=1;
        banners.add(banner);
        return banners;
    }
}
