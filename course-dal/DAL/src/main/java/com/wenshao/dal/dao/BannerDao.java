package com.wenshao.dal.dao;

import com.wenshao.dal.bean.BannerBean;

/**
 * Created by UPC on 2017/9/28.
 * 轮播图接口
 */
public interface BannerDao {
    public int insert(BannerBean bannerBean) throws Exception;
    public int update(BannerBean bannerBean) throws Exception;
    public int remove(int id) throws Exception;
    public int findById(int id) throws Exception;


    public int select(int clientId) throws Exception;
    public int selectAll(int id) throws Exception;
}
