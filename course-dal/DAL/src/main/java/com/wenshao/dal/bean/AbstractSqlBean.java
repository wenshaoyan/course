package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.AbstractSql;

import java.util.Map;

/**
 * Created by wenshao on 2018/1/24.
 * 查询抽象sql类\
 * where
 *   eq: '1'
 *
 */
public class AbstractSqlBean extends AbstractSql {
    private Map<String, Map<String, Object>> whereObject;

    public AbstractSqlBean(AbstractSql abstractSql, Class clazz) {
        super(abstractSql);
        Map<String,Map<String,String>> where = this.where;

        for (Map.Entry<String, Map<String,String>> entry : where.entrySet()) {
            Map<String,String> val = entry.getValue();
            String key = entry.getKey();

        }


    }
}
