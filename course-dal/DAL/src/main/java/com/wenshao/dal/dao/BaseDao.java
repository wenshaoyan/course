package com.wenshao.dal.dao;

import java.util.List;

/**
 * Created by wenshao on 2018/1/9.
 * 基础的接口
 */
public interface BaseDao<T> {
    public int insert(T bean) throws Exception;
    public int update(T bean) throws Exception;
    public int remove(T bean) throws Exception;
    public T findById(int id) throws Exception;

    public List<T> select(T bean) throws Exception;
    public List<T> selectAll() throws Exception;
}
