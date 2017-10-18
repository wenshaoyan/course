package com.wenshao.dal.handler;

import com.wenshao.dal.dao.CourseDao;
import com.wenshao.dal.dao.impl.CourseDaoImpl;
import com.wenshao.dal.thriftgen.Course;
import com.wenshao.dal.thriftgen.CourseService;
import com.wenshao.dal.thriftgen.Video;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by wenshao on 2017/9/28
 * 课程方法实现
 */
public class CourseHandler implements CourseService.Iface {
    private CourseDao courseDao;
    private static Logger logger = Logger.getLogger("CourseHandler");

    public CourseHandler(SqlSessionFactory _sessionFactory) {
        courseDao = new CourseDaoImpl(_sessionFactory);

    }


    @Override
    public int courseInsert(Course course) throws TException {
        return 0;
    }

    @Override
    public int courseUpdate(Course course) throws TException {
        return 0;
    }

    @Override
    public int courseRemove(int id) throws TException {
        return 0;
    }

    @Override
    public Course courseFindById(int id) throws TException {
        return null;
    }

    @Override
    public List<Course> courseSelectAll() throws TException {
        return null;
    }

    @Override
    public List<Course> courseSelect(Course course) throws TException {
        return null;
    }

    @Override
    public String videoInsert(Video video) throws TException {
        return null;
    }

    @Override
    public int videoUpdate(Video video) throws TException {
        return 0;
    }

    @Override
    public int videoRemove(String id) throws TException {
        return 0;
    }

    @Override
    public Video versionFindById(String id) throws TException {
        return null;
    }

    @Override
    public List<Video> videoSelectAll() throws TException {
        return null;
    }

    @Override
    public List<Video> videoSelect(Video video) throws TException {
        return null;
    }
}
