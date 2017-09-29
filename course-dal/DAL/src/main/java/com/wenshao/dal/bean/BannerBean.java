package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.Banner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by UPC on 2017/9/28.
 */
/*
public class BannerBean {
    private int id;
    private Timestamp create_time;
    private Timestamp update_time;
    private int user_id;
    private String image_url;
    private String redirect_url;
    private int show_client_id;
    private int location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public int getShow_client_id() {
        return show_client_id;
    }

    public void setShow_client_id(int show_client_id) {
        this.show_client_id = show_client_id;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "BannerBean{" +
                "id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", user_id=" + user_id +
                ", image_url='" + image_url + '\'' +
                ", redirect_url='" + redirect_url + '\'' +
                ", show_client_id=" + show_client_id +
                ", location=" + location +
                '}';
    }
}
*/

public class BannerBean extends Banner {
    private Timestamp create_time_bean;
    private Timestamp update_time_bean;

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
        this.create_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setCreate_time(create_time);
    }

    @Override
    public Banner setUpdate_time(String update_time) {
        this.update_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setUpdate_time(update_time);
    }
}