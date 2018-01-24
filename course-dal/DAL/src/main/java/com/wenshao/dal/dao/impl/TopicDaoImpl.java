package com.wenshao.dal.dao.impl;

import com.wenshao.dal.bean.BannerBean;
import com.wenshao.dal.bean.TopicBean;
import com.wenshao.dal.dao.BannerDao;
import com.wenshao.dal.dao.BaseDao;
import com.wenshao.dal.thriftgen.AbstractSql;
import com.wenshao.dal.thriftgen.RequestException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created by wenshao on 2017/9/28.
 * 题目的查询实现类
 */
public class TopicDaoImpl implements BaseDao<TopicBean> {
    private SqlSessionFactory sqlSessionFactory;
    private static final String sqlTag = "mapper.topicMapper";
    public TopicDaoImpl(SqlSessionFactory sqlSessionFactory){
        super();
        this.sqlSessionFactory = sqlSessionFactory;
    }
    @Override
    public int insert(TopicBean bean) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert(sqlTag + ".insert", bean);
        sqlSession.commit();
        sqlSession.close();
        return bean.id;
    }
    @Override
    public int update(TopicBean bean) {
        return 0;
    }

    @Override
    public int remove(TopicBean bean) {
        return 0;
    }

    @Override
    public List<TopicBean> select2(AbstractSql abstractSql) {
        return null;
    }

    @Override
    public List<TopicBean> select(TopicBean bean) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<TopicBean> list = sqlSession.selectList(sqlTag + ".select",bean);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

}
