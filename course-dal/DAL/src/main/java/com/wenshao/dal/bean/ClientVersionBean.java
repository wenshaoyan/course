package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.ClientVersion;

import java.sql.Timestamp;

/**
 * Created by wenshao on 2017/10/8.
 * 版本管理bean对象
 */
public class ClientVersionBean extends ClientVersion {
    private Timestamp create_time_bean;
    private Timestamp update_time_bean;

    public ClientVersionBean() {
        super();
    }

    public ClientVersionBean(ClientVersion clientVersion) {
        super();
        this.id = clientVersion.id;
        this.setCreate_time(clientVersion.create_time);
        this.setUpdate_time(clientVersion.update_time);
        this.version_name = clientVersion.version_name;
        this.version_number = clientVersion.version_number;
        this.client = clientVersion.client;
        this.download_url = clientVersion.download_url;
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
    public ClientVersion setCreate_time(String create_time) {
        if (create_time != null) this.create_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setCreate_time(create_time);
    }

    @Override
    public ClientVersion setUpdate_time(String update_time) {
        if (update_time != null) this.update_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setUpdate_time(update_time);
    }
}
