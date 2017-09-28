package com.wenshao.dal.dao.impl;

import com.wenshao.dal.bean.UserBean;
import com.wenshao.dal.dao.UserDao;
import com.wenshao.dal.thriftgen.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created by wenshao on 2017/8/28.
 * 用户表实现类
 */
public class UserDaoImpl implements UserDao {
    // 需要向dao实现类中注入SqlSessionFactory
    // 这里通过构造方法注入
    private SqlSessionFactory sqlSessionFactory;
    private static final String sqlTag = "mapper.userMapper";
    public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
        super();
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public int insertUser(UserBean userBean) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert(sqlTag + ".insertUser", userBean);
        sqlSession.commit();
        sqlSession.close();
        return userBean.getId();
    }

    public List<UserBean> queryUserByTel(String tel) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<UserBean> UserBeans = sqlSession.selectList(sqlTag + ".queryUserByTel", tel);
        sqlSession.commit();
        sqlSession.close();
        return UserBeans;
    }

    public UserBean queryUserById(int id) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserBean userBean = sqlSession.selectOne(sqlTag + ".queryUserByTel", id);
        sqlSession.commit();
        sqlSession.close();
        return userBean;
    }

    public List<UserBean> queryUserByInfo(UserBean userBean) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<UserBean> userBeans = sqlSession.selectList(sqlTag + ".queryUserByInfo", userBean);
        sqlSession.commit();
        sqlSession.close();
        return userBeans;
    }
}
