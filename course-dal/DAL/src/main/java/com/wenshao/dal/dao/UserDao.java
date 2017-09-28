package com.wenshao.dal.dao;

import com.wenshao.dal.bean.UserBean;

import java.util.List;

/**
 * Created by wenshao on 2017/8/28.
 * 用户表接口
 */
public interface UserDao {
    public int insertUser(UserBean userBean) throws Exception;
    public List<UserBean> queryUserByTel(String tel) throws Exception;
    public UserBean queryUserById(short id) throws Exception;
    public List<UserBean> queryUserByInfo(UserBean userBean) throws Exception;

}
