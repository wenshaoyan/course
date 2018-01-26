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
    private final static String tableName = "t_topic_option";
    public TopicOptionBean(){
        super();
    }
    public TopicOptionBean(TopicOption bean) {
        super();
    }
    public static String getTableName() {
        return tableName;
    }
}
