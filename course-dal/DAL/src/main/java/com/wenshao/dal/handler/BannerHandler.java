package com.wenshao.dal.handler;

import com.wenshao.dal.dao.impl.UserDaoImpl;
import com.wenshao.dal.thriftgen.Banner;
import com.wenshao.dal.thriftgen.BannerService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.thrift.TException;

/**
 * Created by wenshao on 2017/9/28
 */
public class BannerHandler implements BannerService.Iface {

    public BannerHandler(SqlSessionFactory _sessionFactory) {
         new UserDaoImpl(_sessionFactory);

    }
    public int insert(Banner banner) throws TException {
        return 0;
    }

    public int update(Banner banner) throws TException {
        return 0;
    }

    public int remove(int id) throws TException {
        return 0;
    }
}
