package com.wenshao.coursate.bean;

import static android.R.attr.name;

/**
 * Created by wenshao on 2018/1/7.
 * 试卷对象
 */

public class PaperBean {
    private int id;
    private String title;

    public PaperBean(int id, String title) {
        this.id = id;
        this.title = title;
    }

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

    @Override
    public String toString() {
        return "PaperBean{" +
                "id=" + id +
                ", title=" + title +
                '}';
    }
}
