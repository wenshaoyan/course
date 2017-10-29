package com.wenshao.dal.dao.impl;

import com.wenshao.dal.bean.VersionBean;
import com.wenshao.dal.dao.VersionDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by wenshao on 2017/10/25
 * 版本管理实现类
 */
public class VersionDaoImpl implements VersionDao {
    private SqlSessionFactory sqlSessionFactory;
    private static final String sqlTag = "mapper.versionMapper";
    public VersionDaoImpl(SqlSessionFactory sqlSessionFactory){
        super();
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public int insert(VersionBean versionBean) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        long time = new Date().getTime();
        versionBean.setCreate_time(String.valueOf(time));
        versionBean.setUpdate_time(String.valueOf(time));
        int insert = sqlSession.insert(sqlTag + ".insert", versionBean);
        sqlSession.commit();
        sqlSession.close();
        return insert;
    }

    @Override
    public int update(VersionBean versionBean) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        long time = new Date().getTime();
        versionBean.setUpdate_time(String.valueOf(time));
        System.out.println(versionBean);
        int update = sqlSession.update(sqlTag + ".update", versionBean);
        sqlSession.commit();
        sqlSession.close();
        return update;
    }

    @Override
    public List<VersionBean> select(VersionBean clientVersionBean) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<VersionBean> list = sqlSession.selectList(sqlTag + ".select",clientVersionBean);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public List<VersionBean> selectAll() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<VersionBean> list = sqlSession.selectList(sqlTag + ".select");
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public int count(VersionBean versionBean) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int i = sqlSession.selectOne(sqlTag + ".count", versionBean);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }
}
