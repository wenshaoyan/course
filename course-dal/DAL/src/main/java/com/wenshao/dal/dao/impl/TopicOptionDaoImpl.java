package com.wenshao.dal.dao.impl;

import com.wenshao.dal.bean.TopicOptionBean;
import com.wenshao.dal.dao.BaseDao;
import com.wenshao.dal.thriftgen.TopicOption;
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
    public int insert(TopicOptionBean bean) throws Exception {
        return 0;
    }

    @Override
    public int update(TopicOptionBean bean) throws Exception {
        return 0;
    }

    @Override
    public int remove(TopicOptionBean bean) throws Exception {
        return 0;
    }

    @Override
    public TopicOptionBean findById(int id) throws Exception {
        return null;
    }

    @Override
    public List<TopicOptionBean> select(TopicOptionBean bean) throws Exception {
        return null;
    }

    @Override
    public List<TopicOptionBean> selectAll() throws Exception {
        return null;
    }
}
