package com.wenshao.dal.handler;

import com.wenshao.dal.bean.QueryBean;
import com.wenshao.dal.bean.TopicBankBean;
import com.wenshao.dal.bean.TopicBean;
import com.wenshao.dal.bean.TopicOptionBean;
import com.wenshao.dal.dao.impl.TopicBankDaoImpl;
import com.wenshao.dal.dao.impl.TopicDaoImpl;
import com.wenshao.dal.dao.impl.TopicOptionDaoImpl;
import com.wenshao.dal.thriftgen.*;
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

    public CommonHandler(SqlSessionFactory _sessionFactory) {
        topicDao = new TopicDaoImpl(_sessionFactory);
        topicOptionDao = new TopicOptionDaoImpl(_sessionFactory);
        topicBankDao = new TopicBankDaoImpl(_sessionFactory);
    }

    private List<TopicOption> topicOptionQuery(TopicOption params, Query query, Custom custom) throws Exception {
        List<TopicOption> results = new ArrayList<TopicOption>();
        TopicOptionBean paramsBean = new TopicOptionBean(params);
        if (query != null) paramsBean.setQueryBean(new QueryBean(query,TopicOptionBean.TABLE_PREFIX));
        if (custom != null) paramsBean.setTables(custom.getTables());
        List<TopicOptionBean> beans = topicOptionDao.select(paramsBean);
        for (TopicOptionBean bean : beans) {
            results.add((TopicOption) bean);
        }
        return results;
    }

    private List<Topic> topicQuery(Topic params, Query query, Custom custom) throws Exception {
        List<Topic> results = new ArrayList<Topic>();
        TopicBean paramsBean = new TopicBean(params);
        if (query != null) paramsBean.setQueryBean(new QueryBean(query,TopicBean.TABLE_PREFIX));
        if (custom != null) paramsBean.setTables(custom.getTables());
        List<TopicBean> beans = topicDao.select(paramsBean);
        for (TopicBean bean : beans) {
            results.add((Topic) bean);
        }
        return results;
    }

    private List<TopicBank> topicBankQuery(TopicBank params, Query query, Custom custom) throws Exception {
        List<TopicBank> results = new ArrayList<TopicBank>();
        TopicBankBean paramsBean = new TopicBankBean(params);
        if (query != null) paramsBean.setQueryBean(new QueryBean(query,TopicBankBean.TABLE_PREFIX));
        if (custom != null) paramsBean.setTables(custom.getTables());
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
            return topicOptionDao.insert(paramsBean);
        } catch (Exception e) {
            throw new TException(e);
        }
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
    public TopicOption topicOptionFindById(int id) throws TException {
        return null;
    }

    @Override
    public List<TopicOption> topicOptionSelect(TopicOption topicOption) throws TException {
        return null;
    }

    @Override
    public List<TopicOption> topicOptionSelectQuery(TopicOption topicOption, Query query) throws TException {
        return null;
    }

    @Override
    public List<TopicOption> topicOptionSelectCustom(TopicOption topicOption, Custom custom) throws TException {
        return null;
    }

    @Override
    public List<TopicOption> topicOptionSelectQueryCustom(TopicOption topicOption, Query query, Custom custom) throws TException {
        return null;
    }

    @Override
    public List<TopicOption> topicOptionSelectQueryCustomNotCache(TopicOption topicOption, Query query, Custom custom) throws TException {
        return null;
    }

    @Override
    public List<TopicOption> topicOptionSelectQueryNoCache(TopicOption topicOption, Query query) throws TException {
        return null;
    }

    @Override
    public int topicOptionCountSelectQuery(TopicOption topicOption, Query query) throws TException {
        return 0;
    }

    @Override
    public int topicInsert(Topic topic) throws TException {
        TopicBean paramsBean = new TopicBean(topic);
        try {
            return topicDao.insert(paramsBean);
        } catch (Exception e) {
            throw new TException(e);
        }
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
    public Topic courseFindById(int id) throws TException {
        return null;
    }

    @Override
    public List<Topic> topicSelect(Topic topic) throws TException {
        try {
            return topicQuery(topic,null,null);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<Topic> topicSelectQuery(Topic topic, Query query) throws TException {
        return null;
    }

    @Override
    public List<Topic> topicSelectCustom(Topic topic, Custom custom) throws TException {
        return null;
    }

    @Override
    public List<Topic> topicSelectQueryCustom(Topic topic, Query query, Custom custom) throws TException {
        return null;
    }

    @Override
    public List<Topic> topicSelectQueryCustomNotCache(Topic topic, Query query, Custom custom) throws TException {
        return null;
    }

    @Override
    public List<Topic> topicSelectQueryNoCache(Topic topic, Query query) throws TException {
        return null;
    }

    @Override
    public int topicCountSelectQuery(Topic topic, Query query) throws TException {
        return 0;
    }

    @Override
    public int topicBankInsert(TopicBank topicBank) throws TException {
        TopicBankBean paramsBean = new TopicBankBean(topicBank);
        try {
            return topicBankDao.insert(paramsBean);
        } catch (Exception e) {
            throw new TException(e);
        }
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
    public TopicBank topicBankFindById(int id) throws TException {
        return null;
    }

    @Override
    public List<TopicBank> topicBankSelect(TopicBank topicBank) throws TException {
        try {
            return topicBankQuery(topicBank,null,null);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<TopicBank> topicBankSelectQuery(TopicBank topicBank, Query query) throws TException {
        try {
            return topicBankQuery(topicBank,query,null);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<TopicBank> topicBankSelectCustom(TopicBank topicBank, Custom custom) throws TException {
        try {
            return topicBankQuery(topicBank,null,custom);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<TopicBank> topicBankSelectQueryCustom(TopicBank topicBank, Query query, Custom custom) throws TException {
        try {
            return topicBankQuery(topicBank,query,custom);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<TopicBank> topicBankSelectQueryCustomNotCache(TopicBank topicBank, Query query, Custom custom) throws TException {
        return topicBankSelectQueryCustom(topicBank,query,custom);
    }

    @Override
    public List<TopicBank> topicBankSelectQueryNoCache(TopicBank topicBank, Query query) throws TException {
        return topicBankSelectQuery(topicBank,query);
    }

    @Override
    public int topicBankCountSelectQuery(TopicBank topicBank, Query query) throws TException {
        return 0;
    }
}
