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
    private String methodGet;  // 获取缓存方法名称
    @XmlElement(required = true)
    private String methodPut;  // 设置缓存方法名称


    public String getMethodGet() {
        return methodGet;
    }

    public void setMethodGet(String methodGet) {
        this.methodGet = methodGet;
    }

    public String getMethodPut() {
        return methodPut;
    }

    public void setMethodPut(String methodPut) {
        this.methodPut = methodPut;
    }


    @Override
    public String toString() {
        return "CacheBean{" +
                ", methodGet='" + methodGet + '\'' +
                ", methodPut='" + methodPut + '\'' +
                '}';
    }
}
