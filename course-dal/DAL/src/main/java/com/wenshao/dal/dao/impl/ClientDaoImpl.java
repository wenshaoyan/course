package com.wenshao.dal.dao.impl;

import com.wenshao.dal.dao.ClientDao;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by wenshao on 2017/10/8.
 * 客户端相关操作实现类
 */
public class ClientDaoImpl implements ClientDao {
    private SqlSessionFactory sqlSessionFactory;
    private static final String sqlTag = "mapper.bannerMapper";
    public ClientDaoImpl(SqlSessionFactory sqlSessionFactory){
        super();
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
