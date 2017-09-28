package com.wenshao.dal.handler;

import com.wenshao.dal.bean.BannerBean;
import com.wenshao.dal.dao.impl.BannerDaoImpl;
import com.wenshao.dal.thriftgen.Banner;
import com.wenshao.dal.thriftgen.BannerService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.thrift.TException;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by wenshao on 2017/9/28
 */
public class BannerHandler implements BannerService.Iface {
    private BannerDaoImpl bannerDao;
    public BannerHandler(SqlSessionFactory _sessionFactory) {
        bannerDao = new BannerDaoImpl(_sessionFactory);

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

    private void toBean(Banner banner,BannerBean bannerBean){
        try {
            BeanUtils.copyProperties(bannerBean,banner);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
