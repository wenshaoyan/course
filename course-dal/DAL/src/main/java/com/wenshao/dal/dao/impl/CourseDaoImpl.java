package com.wenshao.dal.dao.impl;

import com.wenshao.dal.bean.CourseBean;
import com.wenshao.dal.dao.CourseDao;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created by wenshao on 2017/10/18
 * 课程表接口实现类
 */
public class CourseDaoImpl implements CourseDao{
    private SqlSessionFactory sqlSessionFactory;
    private static final String sqlTag = "mapper.courseMapper";
    public CourseDaoImpl(SqlSessionFactory sqlSessionFactory){
        super();
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public int insert(CourseBean courseBean) throws Exception {
        return 0;
    }

    @Override
    public CourseBean findById(int id) throws Exception {
        return null;
    }

    @Override
    public List<CourseBean> select(CourseBean courseBean) throws Exception {
        return null;
    }

    @Override
    public List<CourseBean> selectAll() throws Exception {
        return null;
    }
}
