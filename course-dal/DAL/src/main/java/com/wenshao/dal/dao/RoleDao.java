package com.wenshao.dal.dao;

import com.wenshao.dal.bean.RoleBean;
import com.wenshao.dal.thriftgen.Role;

import java.util.List;

/**
 * Created by wenshao on 2017/10/21.
 * 角色接口
 */
public interface RoleDao {
    public int insert(RoleBean roleBean) throws Exception;
    public RoleBean findById(int id) throws Exception;

    public List<RoleBean> select(RoleBean roleBean) throws Exception;
    public List<RoleBean> selectAll() throws Exception;
}
