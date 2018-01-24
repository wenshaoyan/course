package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.AbstractSql;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by wenshao on 2018/1/24.
 * 查询抽象sql类\
 * where
 * eq: '1'
 */
public class AbstractSqlBean extends AbstractSql {
    private Map<String, Map<String, String>> whereObject = new HashMap<String, Map<String, String>>();
    private final static Map<String, String> conMap = new HashMap<String, String>();
    private final static Set<String> valueTypeSet = new HashSet<String>();
    private Map<String, String> fieldsMap;

    static {
        conMap.put("eq", " = ");
        conMap.put("ne", " != ");
        conMap.put("gt", " > ");
        conMap.put("gte", " >= ");
        conMap.put("lt", " < ");
        conMap.put("lte", " <= ");
        conMap.put("between", " BETWEEN ");
        conMap.put("notBetween", " NOT BETWEEN ");
        conMap.put("in", " IN ");
        conMap.put("notIn", " NOT IN ");
        conMap.put("like", " LIKE ");
        conMap.put("notLike", " NOT LIKE ");
    }

    public AbstractSqlBean(AbstractSql abstractSql, Class clazz) {
        super(abstractSql);
        Map<String, Map<String, String>> where = this.where;
        Object classObject = null;
        try {
            Class<?> aClass = null;
            aClass = Class.forName(clazz.getName());
            classObject = aClass.newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        getFieldsInfo(classObject);

        for (Map.Entry<String, Map<String, String>> entry : where.entrySet()) {
            Map<String, String> queryValue = entry.getValue();
            String queryField = entry.getKey();
            String valueType = null;
            if (queryValue.containsKey("type")) {
                valueType = queryValue.get("type");
            }
            Map<String, String> consObject = new HashMap<String, String>();
            for (Map.Entry<String, String> map : queryValue.entrySet()) {
                String conValue = map.getValue();
                String conKey = map.getKey();
                if (conMap.containsKey(conKey)) {
                    if (valueType == null && fieldsMap.containsKey(queryField)) {
                        valueType = fieldsMap.get(queryField);
                    }
                    if ("number".equals(valueType) && StringUtils.isNumeric(conValue)) {
                        conValue = conMap.get(conKey) + conValue;
                    } else if ("string".equals(valueType)) {
                        conValue = conMap.get(conKey) + "'" + conValue + "'";
                    } else {
                        conValue = null;
                    }
                    if (conValue != null) consObject.put(conKey, conValue);
                }
            }
            if (!consObject.isEmpty()) {
                whereObject.put(queryField, consObject);
            }
        }
    }

    private void typeConvert() {

    }

    private void getFieldsInfo(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        Map<String, String> infoMap = new HashMap<String, String>();
        for (Field field : fields) {
            String s = field.getType().getName();
            if ("java.lang.String".equals(s)) {
                s = "string";
            } else if ("int".equals(s)) {
                s = "number";
            }
            infoMap.put(field.getName(), s);
        }
        this.fieldsMap = infoMap;
    }

    public Map<String, Map<String, String>> getWhereObject() {
        return whereObject;
    }

    public void setWhereObject(Map<String, Map<String, String>> whereObject) {
        this.whereObject = whereObject;
    }


}
