package com.wenshao.dal.dao;

import com.wenshao.dal.thriftgen.AbstractSql;
import com.wenshao.dal.thriftgen.RequestException;

import java.util.List;

/**
 * Created by wenshao on 2018/1/9.
 * 基础的接口
 */
public interface BaseDao<T> {
    public int insert(T bean);
    public int update(T bean);
    public int remove(T bean);
    public List<T> select2(AbstractSql abstractSql);
    public List<T> select(T bean);
}
