package com.wenshao.dal.dao;

import com.wenshao.dal.bean.CourseBean;

import java.util.List;

/**
 * Created by wenshao on 2017/8/28.
 * 课程表接口
 */
public interface CourseDao {
    public int insert(CourseBean courseBean) throws Exception;
    public int update(CourseBean courseBean) throws Exception;
    public int remove(CourseBean courseBean) throws Exception;
    public CourseBean findById(int id) throws Exception;
    public List<CourseBean> select(CourseBean courseBean) throws Exception;
    public int count(CourseBean courseBean) throws Exception;



}
