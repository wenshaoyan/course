package com.wenshao.dal.handler;

import com.wenshao.dal.bean.CourseBean;
import com.wenshao.dal.bean.QueryBean;
import com.wenshao.dal.dao.CourseDao;
import com.wenshao.dal.dao.impl.CourseDaoImpl;
import com.wenshao.dal.thriftgen.*;
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

    private List<Course> courseQuery(Course course, Query query, Custom custom) throws Exception {
        CourseBean courseBean = new CourseBean(course);
        if (query != null) courseBean.setQueryBean(new QueryBean(query,CourseBean.TABLE_PREFIX));
        if (custom != null) courseBean.setTables(custom.getTables());
        List<Course> results = new ArrayList<Course>();
        List<CourseBean> beans = courseDao.select(courseBean);
        for (CourseBean bean : beans) {
            results.add(bean);
        }
        return results;
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
    public int courseRemove(Course course) throws TException {
        return 0;
    }

    @Override
    public Course courseFindById(int id) throws TException {
        return null;
    }

    @Override
    public List<Course> courseSelect(Course course) throws TException {
        try {
            return this.courseQuery(course, null, null);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<Course> courseSelectQuery(Course course, Query query) throws TException {
        try {
            return this.courseQuery(course, query, null);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<Course> courseSelectCustom(Course course, Custom custom) throws TException {
        try {
            return this.courseQuery(course, null, custom);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<Course> courseSelectQueryCustom(Course course, Query query, Custom custom) throws TException {
        try {
            return this.courseQuery(course, query, custom);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<Course> courseSelectQueryCustomNotCache(Course course, Query query, Custom custom) throws TException {
        return this.courseSelectQueryCustom(course, query, custom);
    }

    @Override
    public List<Course> courseSelectQueryNoCache(Course course, Query query) throws TException {
        return this.courseSelectQuery(course, query);
    }

    @Override
    public int courseCountSelectQuery(Course course, Query query) throws TException {
        try {
            return courseDao.count(new CourseBean(course));
        } catch (Exception e) {
            throw new TException(e);
        }
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
    public int videoRemove(Video video) throws TException {
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
