package com.wenshao.dal.dao;

import com.wenshao.dal.bean.BannerBean;

import java.util.List;

/**
 * Created by wenshao on 2017/9/28.
 * 轮播图接口
 */
public interface BannerDao {
    public int insert(BannerBean bannerBean) throws Exception;
    public int update(BannerBean bannerBean) throws Exception;
    public int remove(int id) throws Exception;
    public int findById(int id) throws Exception;


    public List<BannerBean> select(BannerBean bannerBean) throws Exception;
    public List<BannerBean> selectAll() throws Exception;
}
