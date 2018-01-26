package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.TopicBank;
import com.wenshao.dal.thriftgen.TopicOption;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wenshao on 2018/1/9.
 * 题目对象
 */
public class TopicBankBean extends TopicBank{

    private final static String tableName = "t_topic_bank";
    public TopicBankBean(){
        super();
    }
    public TopicBankBean(TopicBank bean) {
        super();
    }

    public static String getTableName() {
        return tableName;
    }
}
