package com.wenshao.dal.dao.impl;

import com.wenshao.dal.bean.TopicBankBean;
import com.wenshao.dal.dao.BaseDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public int insert(TopicBankBean bean) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert(sqlTag + ".insert", bean);
        sqlSession.commit();
        sqlSession.close();
        return bean.id;
    }

    @Override
    public int update(TopicBankBean bean) {
        return 0;
    }

    @Override
    public int remove(TopicBankBean bean) {
        return 0;
    }

    @Override
    public List<TopicBankBean> select(TopicBankBean bean) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<TopicBankBean> list = sqlSession.selectList(sqlTag + ".select",bean);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }
    public int addTopic(int tb_id,int topic_id){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("tb_id",tb_id);
        map.put("topic_id",topic_id);
        int i = sqlSession.insert(sqlTag + ".addTopic", map);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }
    public int removeTopic(int tb_id,int topic_id){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("tb_id",tb_id);
        map.put("topic_id",topic_id);
        int i = sqlSession.delete(sqlTag + ".removeTopic", map);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }
}
