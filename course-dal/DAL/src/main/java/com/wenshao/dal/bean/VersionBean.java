package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.Version;

import java.sql.Timestamp;

/**
 * Created by wenshao on 2017/10/8.
 * 版本管理bean对象
 */
public class VersionBean extends Version {
    private Timestamp create_time_bean;
    private Timestamp update_time_bean;
    private QueryBean queryBean;


    public VersionBean() {
        super();
    }

    public VersionBean(Version version) {
        super();
        this.id = version.id;
        this.setCreate_time(version.create_time);
        this.setUpdate_time(version.update_time);
        this.version_name = version.version_name;
        this.version_number = version.version_number;
        this.client = version.client;
        this.download_url = version.download_url;
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
    public Version setCreate_time(String create_time) {
        if (create_time != null) this.create_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setCreate_time(create_time);
    }

    @Override
    public Version setUpdate_time(String update_time) {
        if (update_time != null) this.update_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setUpdate_time(update_time);
    }

    public QueryBean getQueryBean() {
        return queryBean;
    }

    public void setQueryBean(QueryBean queryBean) {
        this.queryBean = queryBean;
    }
}
