package com.wenshao.dal.dao;

import com.wenshao.dal.bean.VersionBean;

import java.util.List;

/**
 * Created by wenshao on 2017/10/25
 * 版本管理表接口
 */
public interface VersionDao {
    public List<VersionBean> select(VersionBean clientVersionBean) throws Exception;
    public List<VersionBean> selectAll() throws Exception;
    public int count(VersionBean versionBean) throws Exception;

}
