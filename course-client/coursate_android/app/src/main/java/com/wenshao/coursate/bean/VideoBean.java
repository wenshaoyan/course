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
    private int play_number;
    private int buy_number;
    private int love_number;


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

    public int getPlay_number() {
        return play_number;
    }

    public void setPlay_number(int play_number) {
        this.play_number = play_number;
    }

    public int getBuy_number() {
        return buy_number;
    }

    public void setBuy_number(int buy_number) {
        this.buy_number = buy_number;
    }

    public int getLove_number() {
        return love_number;
    }

    public void setLove_number(int love_number) {
        this.love_number = love_number;
    }

    @Override
    public String toString() {
        return "VideoBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", create_time='" + create_time + '\'' +
                ", price=" + price +
                ", describe='" + describe + '\'' +
                ", play_number=" + play_number +
                ", buy_number=" + buy_number +
                ", love_number=" + love_number +
                '}';
    }
}
