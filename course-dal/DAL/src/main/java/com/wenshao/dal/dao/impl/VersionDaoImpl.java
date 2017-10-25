package com.wenshao.dal.dao.impl;

import com.wenshao.dal.bean.VersionBean;
import com.wenshao.dal.dao.VersionDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

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
}
