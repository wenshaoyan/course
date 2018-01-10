package com.wenshao.dal.dao.impl;

import com.wenshao.dal.bean.TopicBankBean;
import com.wenshao.dal.bean.TopicOptionBean;
import com.wenshao.dal.dao.BaseDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created by wenshao on 2017/9/28.
 * 题目对应的选项查询实现类
 */
public class TopicBankDaoImpl implements BaseDao<TopicBankBean> {
    private SqlSessionFactory sqlSessionFactory;
    private static final String sqlTag = "mapper.topicBankMapper";
    public TopicBankDaoImpl(SqlSessionFactory sqlSessionFactory){
        super();
        this.sqlSessionFactory = sqlSessionFactory;

    }

    @Override
    public int insert(TopicBankBean bean) throws Exception {
        return 0;
    }

    @Override
    public int update(TopicBankBean bean) throws Exception {
        return 0;
    }

    @Override
    public int remove(TopicBankBean bean) throws Exception {
        return 0;
    }

    @Override
    public List<TopicBankBean> select(TopicBankBean bean) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<TopicBankBean> list = sqlSession.selectList(sqlTag + ".select",bean);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }
}
