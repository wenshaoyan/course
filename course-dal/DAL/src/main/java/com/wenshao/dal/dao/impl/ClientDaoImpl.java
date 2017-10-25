package com.wenshao.dal.dao.impl;

import com.wenshao.dal.bean.ClientBean;
import com.wenshao.dal.dao.ClientDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

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
}
