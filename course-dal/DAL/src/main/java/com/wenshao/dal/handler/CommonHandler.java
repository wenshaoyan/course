package com.wenshao.dal.handler;

import com.wenshao.dal.bean.TopicBankBean;
import com.wenshao.dal.bean.TopicBean;
import com.wenshao.dal.bean.TopicOptionBean;
import com.wenshao.dal.dao.impl.TopicBankDaoImpl;
import com.wenshao.dal.dao.impl.TopicDaoImpl;
import com.wenshao.dal.dao.impl.TopicOptionDaoImpl;
import com.wenshao.dal.thriftgen.CommonService;
import com.wenshao.dal.thriftgen.Topic;
import com.wenshao.dal.thriftgen.TopicBank;
import com.wenshao.dal.thriftgen.TopicOption;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by wenshao on 2018/1/9.
 * 公共服务接口实现类
 */
public class CommonHandler implements CommonService.Iface {

    private static Logger logger = Logger.getLogger("ClientHandler");
    private TopicDaoImpl topicDao;
    private TopicOptionDaoImpl topicOptionDao;
    private TopicBankDaoImpl topicBankDao;
    public CommonHandler(SqlSessionFactory _sessionFactory){
        topicDao = new TopicDaoImpl(_sessionFactory);
        topicOptionDao = new TopicOptionDaoImpl(_sessionFactory);
        topicBankDao = new TopicBankDaoImpl(_sessionFactory);
    }
    private List<TopicOption> topicOptionQuery(TopicOptionBean paramsBean) throws Exception {
        List<TopicOption> results = new ArrayList<TopicOption>();
        List<TopicOptionBean> beans = topicOptionDao.select(paramsBean);
        for (TopicOptionBean bean : beans) {
            results.add((TopicOption) bean);
        }
        return results;
    }
    private List<Topic> topicQuery(TopicBean paramsBean) throws Exception {
        List<Topic> results = new ArrayList<Topic>();
        List<TopicBean> beans = topicDao.select(paramsBean);
        for (TopicBean bean : beans) {
            results.add((Topic) bean);
        }
        return results;
    }
    private List<TopicBank> topicBankQuery(TopicBankBean paramsBean) throws Exception {
        List<TopicBank> results = new ArrayList<TopicBank>();
        List<TopicBankBean> beans = topicBankDao.select(paramsBean);
        for (TopicBankBean bean : beans) {
            results.add((TopicBank) bean);
        }
        return results;
    }
    @Override
    public int topicOptionInsert(TopicOption topicOption) throws TException {
        TopicOptionBean paramsBean = new TopicOptionBean(topicOption);
        try {
            return  topicOptionDao.insert(paramsBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int topicInsert(Topic topic) throws TException {
        TopicBean paramsBean = new TopicBean(topic);
        try {
            return  topicDao.insert(paramsBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<Topic> topicSelect(Topic topic) throws TException {
        TopicBean bean = new TopicBean(topic);
        try {
            return  topicQuery(bean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int topicBankInsert(TopicBank topicBank) throws TException {
        TopicBankBean paramsBean = new TopicBankBean(topicBank);
        try {
            return  topicBankDao.insert(paramsBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<TopicBank> topicBankSelect(TopicBank topicBank) throws TException {
        TopicBankBean topicBankBean = new TopicBankBean(topicBank);
        try {
            return  topicBankQuery(topicBankBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }
}
