package com.wenshao.dal.dao.impl;

import com.wenshao.dal.bean.QueryBean;
import com.wenshao.dal.bean.UserBean;
import com.wenshao.dal.dao.UserDao;
import org.apache.ibatis.cursor.Cursor;
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
    @Override
    public int insert(UserBean userBean) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert(sqlTag + ".insertUser", userBean);
        sqlSession.commit();
        sqlSession.close();
        return userBean.getId();
    }

    @Override
    public UserBean findById(int id) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserBean userBean1 = new UserBean();
        userBean1.setId(id);
        List<UserBean> userBeans  = sqlSession.selectList(sqlTag + ".select", userBean1);
        UserBean userBean;
        if (userBeans.size()==0){
            userBean = new UserBean();
        }else{
            userBean = userBeans.get(0);
        }
        sqlSession.commit();
        sqlSession.close();
        return userBean;
    }
    @Override
    public List<UserBean> select(UserBean userBean) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<UserBean> userBeans = sqlSession.selectList(sqlTag + ".select", userBean);
        sqlSession.commit();
        sqlSession.close();
        return userBeans;
    }

    @Override
    public int count(UserBean userBean) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int i = sqlSession.selectOne(sqlTag + ".count", userBean);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    @Override
    public List<UserBean> selectAll() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<UserBean> UserBeans = sqlSession.selectList(sqlTag + ".select");
        sqlSession.commit();
        sqlSession.close();
        return UserBeans;
    }

}
