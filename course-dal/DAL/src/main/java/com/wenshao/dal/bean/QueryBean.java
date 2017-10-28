package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.Query;

/**
 * Created by wenshao on 2017/10/22.
 * 查询对象
 */
public class QueryBean extends Query {
    private String orderByClause;
    private int create_time_start_bean;
    private int create_time_end_bean;
    private int update_time_start_bean;
    private int update_time_end_bean;
    private int offset;
    private String tablePrefix;

    public QueryBean() {

    }
    public QueryBean(Query query) {
        super();
        this.tablePrefix = "";
        init(query);
    }

    public QueryBean(Query query,String _tablePrefix) {
        super();
        this.tablePrefix = _tablePrefix;
        init(query);
    }
    private void init(Query query) {
        this.setCreate_time_start_bean(stringToInt(query.create_time_start));
        this.setCreate_time_end_bean(stringToInt(query.create_time_end));
        this.setUpdate_time_start_bean(stringToInt(query.update_time_start));
        this.setUpdate_time_end_bean(stringToInt(query.update_time_end));
        this.page = query.page;
        this.limit = query.limit;
        this.sort_by = query.sort_by;
        this.order = query.order;
        String temp = null;
        if (this.sort_by != null) {
            temp = "" + this.sort_by;
        }
        if (temp != null) {
            if (this.order == null || "asc".equals(this.order)) {
                this.setOrderByClause(this.tablePrefix+temp);
            }else{
                this.setOrderByClause(this.tablePrefix+temp+" DESC");
            }
        }
        if (query.page > 0) this.offset = (query.page - 1) * query.limit;
    }
    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public int getCreate_time_start_bean() {
        return create_time_start_bean;
    }

    public void setCreate_time_start_bean(int create_time_start_bean) {
        this.create_time_start_bean = create_time_start_bean;
    }

    public int getCreate_time_end_bean() {
        return create_time_end_bean;
    }

    public void setCreate_time_end_bean(int create_time_end_bean) {
        this.create_time_end_bean = create_time_end_bean;
    }

    public int getUpdate_time_start_bean() {
        return update_time_start_bean;
    }

    public void setUpdate_time_start_bean(int update_time_start_bean) {
        this.update_time_start_bean = update_time_start_bean;
    }

    public int getUpdate_time_end_bean() {
        return update_time_end_bean;
    }

    public void setUpdate_time_end_bean(int update_time_end_bean) {
        this.update_time_end_bean = update_time_end_bean;
    }
    private int stringToInt(String string){
        if (string !=null) return Integer.parseInt(string);
        return 0;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

}
