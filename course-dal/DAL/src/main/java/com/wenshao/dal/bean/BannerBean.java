package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.Banner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by wenshao on 2017/9/28.
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