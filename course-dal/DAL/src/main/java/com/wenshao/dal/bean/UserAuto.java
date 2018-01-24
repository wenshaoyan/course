package com.wenshao.dal.bean;

import java.io.Serializable;
import java.util.Date;

public class UserAuto implements Serializable {
    private Integer toId;

    private String toContext;

    private Integer toTopicId;

    private Date toCreateTime;

    private Date toUpdateTime;

    private static final long serialVersionUID = 1L;

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getToContext() {
        return toContext;
    }

    public void setToContext(String toContext) {
        this.toContext = toContext == null ? null : toContext.trim();
    }

    public Integer getToTopicId() {
        return toTopicId;
    }

    public void setToTopicId(Integer toTopicId) {
        this.toTopicId = toTopicId;
    }

    public Date getToCreateTime() {
        return toCreateTime;
    }

    public void setToCreateTime(Date toCreateTime) {
        this.toCreateTime = toCreateTime;
    }

    public Date getToUpdateTime() {
        return toUpdateTime;
    }

    public void setToUpdateTime(Date toUpdateTime) {
        this.toUpdateTime = toUpdateTime;
    }
}