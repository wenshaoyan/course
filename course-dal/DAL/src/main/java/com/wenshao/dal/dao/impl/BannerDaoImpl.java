package com.wenshao.dal.dao.impl;

import com.wenshao.dal.bean.BannerBean;
import com.wenshao.dal.dao.BannerDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created by wenshao on 2017/9/28.
 * 轮播图实现类
 */
public class BannerDaoImpl implements BannerDao{
    private SqlSessionFactory sqlSessionFactory;
    private static final String sqlTag = "mapper.bannerMapper";
    public BannerDaoImpl(SqlSessionFactory sqlSessionFactory){
        super();
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public int insert(BannerBean bannerBean) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert(sqlTag + ".insert", bannerBean);
        sqlSession.commit();
        sqlSession.close();
        return bannerBean.id;
    }

    public int update(BannerBean bannerBean) throws Exception {
        return 0;
    }

    public int remove(int id) throws Exception {
        return 0;
    }

    public int findById(int id) throws Exception {
        return 0;
    }

    public int select(int clientId) throws Exception {
        return 0;
    }

    public List<BannerBean> selectAll() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<BannerBean> objects = sqlSession.selectList(sqlTag + ".select");
        sqlSession.commit();
        sqlSession.close();
        return objects;
    }
}
