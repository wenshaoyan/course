package com.wenshao.dal.dao.impl;

import com.wenshao.dal.bean.ClientBean;
import com.wenshao.dal.dao.ClientDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by wenshao on 2017/10/8.
 * 客户端相关操作实现类
 */
public class ClientDaoImpl implements ClientDao {
    private SqlSessionFactory sqlSessionFactory;
    private static final String sqlTag = "mapper.clientMapper";
    public ClientDaoImpl(SqlSessionFactory sqlSessionFactory){
        super();
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<ClientBean> select(ClientBean clientBean) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<ClientBean> list = sqlSession.selectList(sqlTag + ".select",clientBean);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public List<ClientBean> selectAll() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<ClientBean> list = sqlSession.selectList(sqlTag + ".select");
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public int insert(ClientBean clientBean) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        long time = new Date().getTime();
        clientBean.setCreate_time(String.valueOf(time));
        clientBean.setUpdate_time(String.valueOf(time));
        sqlSession.insert(sqlTag + ".insert",clientBean);
        sqlSession.commit();
        sqlSession.close();
        return clientBean.getId();
    }

    @Override
    public int update(ClientBean clientBean) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        long time = new Date().getTime();
        clientBean.setUpdate_time(String.valueOf(time));
        int update = sqlSession.update(sqlTag + ".update", clientBean);
        sqlSession.commit();
        sqlSession.close();
        return update;
    }
}
