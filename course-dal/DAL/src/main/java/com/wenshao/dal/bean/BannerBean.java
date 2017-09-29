package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.Banner;

import java.sql.Timestamp;

/**
 * Created by wenshao on 2017/9/28.
 */
public class BannerBean extends Banner {
    private Timestamp create_time_bean;
    private Timestamp update_time_bean;

    public BannerBean() {
        super();
    }

    public BannerBean(Banner banner) {
        super();
        this.id = banner.id;
        this.setCreate_time(banner.create_time);
        this.setUpdate_time(banner.update_time);
        this.user_id = banner.user_id;
        this.image_url = banner.image_url;
        this.redirect_url = banner.redirect_url;
        this.show_client_id = banner.show_client_id;
        this.location = banner.location;
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
    public Banner setCreate_time(String create_time) {
        if (create_time!=null)this.create_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setCreate_time(create_time);
    }

    @Override
    public Banner setUpdate_time(String update_time) {
        if (update_time!=null)this.update_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setUpdate_time(update_time);
    }

    @Override
    public String toString() {
        return "BannerBean{" +
                "create_time_bean=" + create_time_bean +
                ", update_time_bean=" + update_time_bean +
                ", id=" + id +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", user_id=" + user_id +
                ", image_url='" + image_url + '\'' +
                ", redirect_url='" + redirect_url + '\'' +
                ", show_client_id=" + show_client_id +
                ", location=" + location +
                '}';
    }
}