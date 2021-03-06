package com.wenshao.dal.dao;

import com.wenshao.dal.bean.AbstractSqlBean;
import com.wenshao.dal.thriftgen.TopicBank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wenshao on 2018/1/24.
 */
public interface TopicBankDao {
    int insert(@Param("topicBank") TopicBank topicBank);

    int update(@Param("topicBank") TopicBank topicBan, @Param("abstractSql") AbstractSqlBean abstractSqlBean);

    int remove(@Param("topicBank") TopicBank topicBan, @Param("abstractSql") AbstractSqlBean abstractSqlBean);

    List<TopicBank> select(@Param("abstractSql") AbstractSqlBean abstractSqlBean);

    int count(@Param("abstractSql") AbstractSqlBean abstractSqlBean);
}
