package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.Topic;

import java.sql.Timestamp;

/**
 * Created by wenshao on 2018/1/9.
 * 题目对象
 */
public class TopicBean extends Topic{
    private Timestamp create_time_bean;
    private Timestamp update_time_bean;
    private QueryBean queryBean;
    public static final String TABLE_PREFIX = "topic_";

    public TopicBean(){
        super();
    }
    public TopicBean(Topic bean) {
        super();
        this.id = bean.id;
        this.setCreate_time(bean.create_time);
        this.setUpdate_time(bean.update_time);
        this.title = bean.title;
        this.type = bean.type;
        this.analysis = bean.analysis;
        this.correct_answer = bean.correct_answer;
        this.score = bean.score;
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
    public Topic setCreate_time(String create_time) {
        if (create_time!=null)this.create_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setCreate_time(create_time);
    }

    @Override
    public Topic setUpdate_time(String update_time) {
        if (update_time!=null)this.update_time_bean = new Timestamp(Long.parseLong(create_time));
        return super.setUpdate_time(update_time);
    }

    public QueryBean getQueryBean() {
        return queryBean;
    }

    public void setQueryBean(QueryBean queryBean) {
        this.queryBean = queryBean;
    }
}
