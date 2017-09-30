package com.wenshao.dal.handler;

import com.wenshao.dal.bean.BannerBean;
import com.wenshao.dal.dao.impl.BannerDaoImpl;
import com.wenshao.dal.thriftgen.Banner;
import com.wenshao.dal.thriftgen.BannerService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by wenshao on 2017/9/28
 */
public class BannerHandler implements BannerService.Iface {
    private BannerDaoImpl bannerDao;
    private static Logger logger = Logger.getLogger("BannerHandler");

    public BannerHandler(SqlSessionFactory _sessionFactory) {
        bannerDao = new BannerDaoImpl(_sessionFactory);

    }

    public int insert(Banner banner) throws TException {
        BannerBean paramsBean = new BannerBean(banner);
        try {
            return  bannerDao.insert(paramsBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    public int update(Banner banner) throws TException {
        return 0;
    }

    public int remove(int id) throws TException {
        return 0;
    }

    public List<Banner> selectAll() throws TException {
        try {
            List<Banner> banners = new ArrayList<Banner>();
            List<BannerBean> bannerBeans = bannerDao.selectAll();
            for (BannerBean bean : bannerBeans) {
                banners.add((Banner) bean);

            }
            return banners;
        } catch (Exception e) {
            throw new TException(e);
        }

    }

    @Override
    public List<Banner> select(Banner banner) throws TException {
        List<Banner> banners = new ArrayList<Banner>();
        BannerBean paramsBean = new BannerBean(banner);
        try {
            List<BannerBean> bannerBeans = bannerDao.select(paramsBean);
            for (BannerBean bean : bannerBeans) {
                banners.add((Banner) bean);

            }
            return banners;
        } catch (Exception e) {
            throw new TException(e);
        }
    }

}
