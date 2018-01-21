package com.wenshao.dal.handler;

import com.wenshao.dal.bean.BannerBean;
import com.wenshao.dal.bean.QueryBean;
import com.wenshao.dal.dao.impl.BannerDaoImpl;
import com.wenshao.dal.thriftgen.Banner;
import com.wenshao.dal.thriftgen.BannerService;
import com.wenshao.dal.thriftgen.Custom;
import com.wenshao.dal.thriftgen.Query;
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

    private List<Banner> clientQuery(BannerBean paramsBean) throws Exception {
        List<Banner> results = new ArrayList<Banner>();
        List<BannerBean> beans = bannerDao.select(paramsBean);
        for (BannerBean bean : beans) {
            results.add((Banner) bean);
        }
        return results;
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
        BannerBean paramsBean = new BannerBean(banner);
        try {
            return  bannerDao.update(paramsBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }
    @Override
    public int remove(Banner banner) throws TException {
        BannerBean bannerBean = new BannerBean(banner);
        try {
            return  bannerDao.remove(bannerBean);

        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public Banner findById(int id) throws TException {
        try {
            return  (Banner)bannerDao.findById(id);
        } catch (Exception e) {
            throw new TException(e);
        }
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
        BannerBean bannerBean = new BannerBean(banner);
        try {
            return  clientQuery(bannerBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<Banner> selectQuery(Banner banner, Query query) throws TException {
        BannerBean bannerBean = new BannerBean(banner);
        QueryBean queryBean = new QueryBean(query,BannerBean.TABLE_PREFIX);
        bannerBean.setQueryBean(queryBean);
        try {
            return  clientQuery(bannerBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<Banner> selectCustom(Banner banner, Custom custom) throws TException {
        return this.select(banner);
    }

    @Override
    public List<Banner> selectQueryCustom(Banner banner, Query query, Custom custom) throws TException {
        return this.selectQuery(banner,query);
    }

    @Override
    public List<Banner> selectQueryCustomNotCache(Banner banner, Query query, Custom custom) throws TException {
        return this.selectQuery(banner,query);
    }

    @Override
    public List<Banner> selectQueryNoCache(Banner banner, Query query) throws TException {
        return this.selectQuery(banner,query);
    }

}
