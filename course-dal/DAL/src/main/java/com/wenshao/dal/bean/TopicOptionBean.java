package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.TopicOption;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wenshao on 2018/1/9.
 * 题目对象
 */
public class TopicOptionBean extends TopicOption{

    public TopicOptionBean(){
        super();
    }
    public TopicOptionBean(TopicOption bean) {
        super();
        this.to_id = bean.to_id;
        this.to_create_time = bean.to_create_time;
        this.to_context = bean.to_context;
        this.to_topic_id = bean.to_topic_id;
    }

}
