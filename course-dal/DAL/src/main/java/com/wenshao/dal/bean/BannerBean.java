package com.wenshao.dal.bean;

import java.sql.Timestamp;

/**
 * Created by UPC on 2017/9/28.
 *
 */
public class BannerBean {
    public int id;
    public Timestamp create_time;
    public Timestamp update_time;
    public int user_id;
    public String image_url;
    public String redirect_url;
    public int show_client_id;
    public int location;

}
