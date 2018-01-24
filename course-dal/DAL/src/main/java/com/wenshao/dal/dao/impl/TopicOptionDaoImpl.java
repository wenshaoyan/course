package com.wenshao.dal.dao.impl;

import com.wenshao.dal.bean.TopicOptionBean;
import com.wenshao.dal.dao.BaseDao;
import com.wenshao.dal.thriftgen.AbstractSql;
import com.wenshao.dal.thriftgen.TopicOption;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created by wenshao on 2017/9/28.
 * 题目对应的选项查询实现类
 */
public class TopicOptionDaoImpl implements BaseDao<TopicOptionBean> {
    private SqlSessionFactory sqlSessionFactory;
    private static final String sqlTag = "mapper.topicOptionMapper";
    public TopicOptionDaoImpl(SqlSessionFactory sqlSessionFactory){
        super();
        this.sqlSessionFactory = sqlSessionFactory;

    }
    @Override
    public int insert(TopicOptionBean bean) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert(sqlTag + ".insert", bean);
        sqlSession.commit();
        sqlSession.close();
        return bean.to_id;
    }

    @Override
    public int update(TopicOptionBean bean) {
        return 0;
    }

    @Override
    public int remove(TopicOptionBean bean) {
        return 0;
    }

    @Override
    public List<TopicOptionBean> select2(AbstractSql abstractSql) {
        System.out.println(abstractSql);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<TopicOptionBean> list = sqlSession.selectList(sqlTag + ".select",abstractSql);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public List<TopicOptionBean> select(TopicOptionBean bean) {
        return null;
    }


}
