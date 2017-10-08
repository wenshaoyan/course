package com.wenshao.dal.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by wenshao on 2017/10/8.
 * 每个action对应的对象
 */
@XmlAccessorType(XmlAccessType.FIELD)
public  class CacheBean {
    @XmlElement(required = true)
    private int expireTime;     // 过期时间 单位:分钟
    @XmlElement(required = true)
    private String actionName;  // 方法名称

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "CacheBean{" +
                "expireTime=" + expireTime +
                ", actionName='" + actionName + '\'' +
                '}';
    }
}
