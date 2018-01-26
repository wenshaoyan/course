package com.wenshao.dal.dao;

import com.wenshao.dal.bean.AbstractSqlBean;
import com.wenshao.dal.thriftgen.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wenshao on 2018/1/24.
 *
 */
public interface TopicDao {
    List<Topic> select(@Param("abstractSql") AbstractSqlBean abstractSqlBean);
}
