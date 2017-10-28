package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.User;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wenshao on 2017/8/28.
 * 用户类接口
 */
public class UserBean extends User {
    private Timestamp create_time_bean;
    private Timestamp update_time_bean;
    private Timestamp register_time_bean;
    private QueryBean queryBean;
    public static final String TABLE_PREFIX = "user_";
    private Set<String> tables = new HashSet<String>();


    public UserBean() {
        super();
    }

    public UserBean(User user) {
        super();
        this.id = user.id;
        this.setCreate_time(user.create_time);
        this.setUpdate_time(user.update_time);
        this.setRegister_time(user.register_time);
        this.name = user.name;
        this.head = user.head;
        this.tel = user.tel;
        this.device_uuid = user.device_uuid;
        this.password = user.password;
        this.role_id = user.role_id;
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

    public Timestamp getRegister_time_bean() {
        return register_time_bean;
    }

    public void setRegister_time_bean(Timestamp register_time_bean) {
        this.register_time = String.valueOf(register_time_bean.getTime());
        this.register_time_bean = register_time_bean;
    }

    public QueryBean getQueryBean() {
        return queryBean;
    }

    public void setQueryBean(QueryBean queryBean) {
        this.queryBean = queryBean;
    }

    @Override
    public User setCreate_time(String create_time) {
        if (create_time!=null)this.create_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setCreate_time(create_time);
    }

    @Override
    public User setUpdate_time(String update_time) {
        if (update_time!=null)this.update_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setUpdate_time(update_time);
    }

    @Override
    public User setRegister_time(String register_time) {
        if (register_time!=null)this.register_time_bean = new Timestamp(Long.parseLong(register_time));
        return super.setRegister_time(register_time);
    }

    public Set<String> getTables() {
        return tables;
    }

    public void setTables(Set<String> tables) {
        this.tables = tables;
    }
}
