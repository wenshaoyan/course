package com.wenshao.dal.handler;

import com.wenshao.dal.bean.*;
import com.wenshao.dal.dao.TopicBankDao;
import com.wenshao.dal.dao.TopicDao;
import com.wenshao.dal.dao.TopicOptionDao;
import com.wenshao.dal.thriftgen.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenshao on 2018/1/9.
 * 公共服务接口实现类
 */
public class CommonHandler implements CommonService.Iface {
    private static Logger logger = Logger.getLogger(CommonHandler.class);
    private SqlSessionFactory sessionFactory;

    public CommonHandler(SqlSessionFactory _sessionFactory) {
        sessionFactory = _sessionFactory;
    }

    @Override
    public int topicOptionInsert(TopicOption topicOption) throws RequestException,TException {
        return 0;
    }

    @Override
    public int topicOptionUpdate(TopicOption topicOption) throws TException {
        return 0;
    }

    @Override
    public int topicOptionRemove(TopicOption topicOption) throws TException {
        return 0;
    }


    @Override
    public List<TopicOption> topicOptionSelect(AbstractSql abstractSql) throws TException {
        SqlSession sqlSession = sessionFactory.openSession();
        TopicOptionDao dao = sqlSession.getMapper(TopicOptionDao.class);
        return dao.select(new AbstractSqlBean(abstractSql,TopicOption.class));
    }

    @Override
    public List<TopicOption> topicOptionSelectNoCache(AbstractSql abstractSql) throws TException {
        return null;
    }

    @Override
    public int topicOptionCount(AbstractSql abstractSql) throws RequestException, TException {
        return 0;
    }

    @Override
    public int topicInsert(Topic topic) throws TException {
        return  0;
    }

    @Override
    public int topicUpdate(Topic topic) throws TException {
        return 0;
    }

    @Override
    public int topicRemove(Topic topic) throws TException {
        return 0;
    }


    @Override
    public List<Topic> topicSelect(AbstractSql abstractSql) throws RequestException {
        SqlSession sqlSession = sessionFactory.openSession();
        TopicDao dao = sqlSession.getMapper(TopicDao.class);
        return dao.select(new AbstractSqlBean(abstractSql,TopicOption.class));
    }

    @Override
    public List<Topic> topicSelectNoCache(AbstractSql abstractSql) throws RequestException {
        return null;
    }

    @Override
    public int topicCount(AbstractSql abstractSql) throws RequestException, TException {
        return 0;
    }

    @Override
    public int topicBankInsert(TopicBank topicBank) throws TException {
        return 0;
    }

    @Override
    public int topicBankUpdate(TopicBank topicBank) throws TException {
        return 0;
    }

    @Override
    public int topicBankRemove(TopicBank topicBank) throws TException {
        return 0;
    }

    @Override
    public List<TopicBank> topicBankSelect(AbstractSql abstractSql) throws RequestException {
        SqlSession sqlSession = sessionFactory.openSession();
        TopicBankDao dao = sqlSession.getMapper(TopicBankDao.class);
        return dao.select(new AbstractSqlBean(abstractSql,TopicOption.class));
    }

    @Override
    public List<TopicBank> topicBankSelectNoCache(AbstractSql abstractSql) throws RequestException {
        return topicBankSelect(abstractSql);
    }

    @Override
    public int topicBankCount(AbstractSql abstractSql) throws RequestException {
        SqlSession sqlSession = sessionFactory.openSession();
        TopicBankDao dao = sqlSession.getMapper(TopicBankDao.class);
        return dao.count(new AbstractSqlBean(abstractSql,TopicOption.class));
    }


}
