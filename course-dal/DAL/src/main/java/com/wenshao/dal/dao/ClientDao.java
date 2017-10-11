package com.wenshao.dal.dao;

import com.wenshao.dal.bean.ClientBean;

import java.util.List;

/**
 * Created by wenshao on 2017/10/8.
 * 客户端相关操作
 */
public interface ClientDao {
    public List<ClientBean> clientSelect(ClientBean clientBean) throws Exception;
    public List<ClientBean> clientSelectAll() throws Exception;
}
