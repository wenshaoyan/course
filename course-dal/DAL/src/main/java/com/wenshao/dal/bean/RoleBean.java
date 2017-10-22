package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.Role;

import java.sql.Timestamp;

/**
 * Created by wenshao on 2017/10/21.
 * 用户角色对象
 */
public class RoleBean extends Role{
    private Timestamp create_time_bean;
    private Timestamp update_time_bean;

    public RoleBean() {
        super();
    }

    public RoleBean(Role role) {
        super();
        this.id = role.id;
        this.setCreate_time(role.create_time);
        this.setUpdate_time(role.update_time);
        this.name = role.name;
        this.permission = role.permission;
    }

    public Timestamp getCreate_time_bean() {
        return create_time_bean;
    }

    public void setCreate_time_bean(Timestamp create_time_bean) {
        this.create_time = String.valueOf(create_time_bean.getTime());
        this.create_time_bean = create_time_bean;
    }

    public Timestamp getUpdate_time_bean() {
        return update_time_bean;
    }
    public void setUpdate_time_bean(Timestamp update_time_bean) {
        this.update_time = String.valueOf(update_time_bean.getTime());
        this.update_time_bean = update_time_bean;
    }

    @Override
    public Role setCreate_time(String create_time) {
        if (create_time!=null)this.create_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setCreate_time(create_time);
    }

    @Override
    public Role setUpdate_time(String update_time) {
        if (update_time!=null)this.update_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setUpdate_time(update_time);
    }

}
