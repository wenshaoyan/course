package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.Topic;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wenshao on 2018/1/9.
 * 题目对象
 */
public class TopicBean extends Topic{
    private final static String tableName = "t_topic";
    public TopicBean(){
        super();
    }
    public TopicBean(Topic bean) {
        super();
    }
    public static String getTableName() {
        return tableName;
    }
}
