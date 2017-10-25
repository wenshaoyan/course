package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.ClientSide;
import com.wenshao.dal.thriftgen.Query;

import java.sql.Timestamp;

/**
 * Created by wenshao on 2017/10/8.
 * 客户端bean对象
 */
public class ClientBean extends ClientSide{
    private Timestamp create_time_bean;
    private Timestamp update_time_bean;
    private QueryBean queryBean;

    public ClientBean() {
        super();
    }

    public ClientBean(ClientSide client) {
        super();
        this.id = client.id;
        this.setCreate_time(client.create_time);
        this.setUpdate_time(client.update_time);
        this.name = client.name;
        this.package_name = client.package_name;

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
    public ClientSide setCreate_time(String create_time) {
        if (create_time!=null)this.create_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setCreate_time(create_time);
    }

    @Override
    public ClientSide setUpdate_time(String update_time) {
        if (update_time!=null)this.update_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setUpdate_time(update_time);
    }

    public QueryBean getQueryBean() {
        return queryBean;
    }

    public void setQueryBean(QueryBean queryBean) {
        this.queryBean = queryBean;
    }
}
