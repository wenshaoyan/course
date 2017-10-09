package com.wenshao.dal.bean;



import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wenshao on 2017/10/8.
 * cache xml 配置文件读取
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class CacheXmlBean {
    @XmlElementWrapper(name = "cacheBeans")
    @XmlElement(name = "cacheBean")
    private Map<String,CacheBean> cacheBeans = new HashMap<String, CacheBean>();

    public Map<String, CacheBean> getCacheBeans() {
        return cacheBeans;
    }

    public void setCacheBeans(Map<String, CacheBean> cacheBeans) {
        this.cacheBeans = cacheBeans;
    }

    @Override
    public String toString() {
        return "CacheXmlBean{" +
                "cacheBeans=" + cacheBeans +
                '}';
    }
}
