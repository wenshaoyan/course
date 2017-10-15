package com.wenshao.coursate.bean;

/**
 * Created by wenshao on 2017/10/14.
 * 视频音频对象
 */

public class VideoBean {
    private int id;
    private String title;
    private String create_time;
    private float price;
    private String describe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "VideoBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", create_time='" + create_time + '\'' +
                ", price=" + price +
                ", describe='" + describe + '\'' +
                '}';
    }
}
