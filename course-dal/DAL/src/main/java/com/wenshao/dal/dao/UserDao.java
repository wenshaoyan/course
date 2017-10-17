package com.wenshao.dal.dao;

import com.wenshao.dal.bean.UserBean;

import java.util.List;

/**
 * Created by wenshao on 2017/8/28.
 * 用户表接口
 */
public interface UserDao {
    public int insert(UserBean userBean) throws Exception;
    public UserBean findById(int id) throws Exception;

    public List<UserBean> select(UserBean userBean) throws Exception;
    public List<UserBean> selectAll() throws Exception;

}
