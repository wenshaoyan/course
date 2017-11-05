package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.Course;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wenshao on 2017/10/18
 * 课程对象
 */
public class CourseBean extends Course{
    private Timestamp create_time_bean;
    private Timestamp update_time_bean;
    private QueryBean queryBean;
    private Set<String> tables = new HashSet<String>();
    public static final String TABLE_PREFIX = "course_";
    public CourseBean(){

    }
    public CourseBean(Course course){
        super();
        this.id = course.id;
        this.title = course.title;
        this.setCreate_time(course.create_time);
        this.setUpdate_time(course.update_time);
        this.price = course.price;
        this.describe = course.describe;
        this.status = course.status;
        this.author_id = course.author_id;
        this.courseTypeAll = course.courseTypeAll;
        this.image_url = course.image_url;

    }
    public Timestamp getCreate_time_bean() {
        return create_time_bean;
    }

    public void setCreate_time_bean(Timestamp create_time_bean) {
        this.create_time = String.valueOf(create_time_bean.getTime());
        this.create_time_bean = create_time_bean;
    }

    public Timestamp getUpdate_time_bean() {
        return update_time_bean;
    }
    public void setUpdate_time_bean(Timestamp update_time_bean) {
        this.update_time = String.valueOf(update_time_bean.getTime());
        this.update_time_bean = update_time_bean;
    }

    @Override
    public Course setCreate_time(String create_time) {
        if (create_time!=null)this.create_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setCreate_time(create_time);
    }

    @Override
    public Course setUpdate_time(String update_time) {
        if (update_time!=null)this.update_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setUpdate_time(update_time);
    }

    public QueryBean getQueryBean() {
        return queryBean;
    }

    public void setQueryBean(QueryBean queryBean) {
        this.queryBean = queryBean;
    }

    public Set<String> getTables() {
        return tables;
    }

    public void setTables(Set<String> tables) {
        this.tables = tables;
    }

}
