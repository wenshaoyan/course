package com.wenshao.cache.bean;

import com.wenshao.cache.thriftgen.Banner;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wenshao on 2017/10/9.
 * 轮播图集合对象
 */
public class ListBanner implements Serializable{
    private List<Banner> banners;

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    @Override
    public String toString() {
        return "ListBanner{" +
                "banners=" + banners +
                '}';
    }
}
