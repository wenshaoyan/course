package com.wenshao.dal.dao;

import com.wenshao.dal.bean.AbstractSqlBean;
import com.wenshao.dal.thriftgen.AbstractSql;
import com.wenshao.dal.thriftgen.TopicOption;

import java.util.List;

/**
 * Created by wenshao on 2018/1/24.
 *
 */
public interface TopicOptionDao {
    List<TopicOption> select(AbstractSqlBean abstractSqlBean);
}
