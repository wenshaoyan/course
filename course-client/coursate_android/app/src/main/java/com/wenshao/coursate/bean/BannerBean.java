package com.wenshao.coursate.bean;

/**
 * Created by wenshao on 2017/10/11.
 * 轮播图对象
 */

public class BannerBean {
    private int id;
    private String image_url;
    private String redirect_url;
    private int location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                ", image_url='" + image_url + '\'' +
                ", redirect_url='" + redirect_url + '\'' +
                ", location=" + location +
                '}';
    }
}
