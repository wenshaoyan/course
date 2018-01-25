package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.AbstractSql;
import com.wenshao.dal.thriftgen.RequestException;
import com.wenshao.dal.thriftgen.Where;
import com.wenshao.dal.util.ExceptionUtil;
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
    private final static Map<String, String> conditionMap = new HashMap<String, String>();
    private final static Set<String> valueTypeSet = new HashSet<String>();
    private Map<String, String> fieldsMap;
    private String limitObject;

    static {
        conditionMap.put("eq", " = ");
        conditionMap.put("ne", " != ");
        conditionMap.put("gt", " > ");
        conditionMap.put("gte", " >= ");
        conditionMap.put("lt", " < ");
        conditionMap.put("lte", " <= ");
        conditionMap.put("between", " BETWEEN ");
        conditionMap.put("notBetween", " NOT BETWEEN ");
        conditionMap.put("any", " IN ");
        conditionMap.put("notAny", " NOT IN ");
        conditionMap.put("like", " LIKE ");
        conditionMap.put("notLike", " NOT LIKE ");
    }

    public AbstractSqlBean(AbstractSql abstractSql, Class clazz) throws RequestException {
        super(abstractSql);
        initClazz(clazz);
        initWhere();
        initOrder();
        initLimit();
    }

    private void initClazz(Class clazz) throws RequestException {
        try {
            Class<?> aClass = Class.forName(clazz.getName());
            Object classObject = aClass.newInstance();
            getFieldsInfo(classObject);
        } catch (ClassNotFoundException e) {
            throw ExceptionUtil.getClassE(e);
        } catch (IllegalAccessException e) {
            throw ExceptionUtil.getClassE(e);
        } catch (InstantiationException e) {
            throw ExceptionUtil.getClassE(e);
        }
    }

    /**
     * 初始化whereObject
     */
    private void initWhere() {
        Map<String, Where> where = this.where;
        for (Map.Entry<String, Where> entry : where.entrySet()) {
            Where queryValue = entry.getValue();
            String queryField = entry.getKey();
            String valueType = null;
            if (queryValue.getType() != null) {
                valueType = queryValue.getType();
            }
            Map<String, String> consObject = new HashMap<String, String>();

            if (queryValue.getEq() != null) {
                String conValue = combineSqlConditionByString("eq", queryValue.getEq(), valueType, queryField);
                if (conValue != null) consObject.put("eq", conValue);
            }
            if (queryValue.getNe() != null) {
                String conValue = combineSqlConditionByString("ne", queryValue.getNe(), valueType, queryField);
                if (conValue != null) consObject.put("ne", conValue);
            }

            if (queryValue.getGt() != null) {
                String conValue = combineSqlConditionByString("gt", queryValue.getGt(), valueType, queryField);
                if (conValue != null) consObject.put("gt", conValue);
            }
            if (queryValue.getGte() != null) {
                String conValue = combineSqlConditionByString("gte", queryValue.getGte(), valueType, queryField);
                if (conValue != null) consObject.put("gte", conValue);
            }
            if (queryValue.getLt() != null) {
                String conValue = combineSqlConditionByString("lt", queryValue.getLt(), valueType, queryField);
                if (conValue != null) consObject.put("lt", conValue);
            }
            if (queryValue.getLte() != null) {
                String conValue = combineSqlConditionByString("lte", queryValue.getLte(), valueType, queryField);
                if (conValue != null) consObject.put("lte", conValue);
            }
            if (queryValue.getLike() != null) {
                String conValue = combineSqlConditionByString("like", queryValue.getLike(), valueType, queryField);
                if (conValue != null) consObject.put("like", conValue);
            }
            if (queryValue.getNotLike() != null) {
                String conValue = combineSqlConditionByString("notLike", queryValue.getNotLike(), valueType, queryField);
                if (conValue != null) consObject.put("notLike", conValue);
            }
            if (queryValue.getBetween() != null) {
                String conValue = getSqlValueByList("between", queryValue.getBetween(), valueType, queryField);
                if (conValue != null) consObject.put("between", conValue);
            }
            if (queryValue.getNotBetween() != null) {
                String conValue = getSqlValueByList("notBetween", queryValue.getNotBetween(), valueType, queryField);
                if (conValue != null) consObject.put("notBetween", conValue);
            }
            if (queryValue.getAny() != null) {
                String conValue = getSqlValueByList("any", queryValue.getAny(), valueType, queryField);
                if (conValue != null) consObject.put("any", conValue);
            }
            if (queryValue.getNotAny() != null) {
                String conValue = getSqlValueByList("notAny", queryValue.getNotAny(), valueType, queryField);
                if (conValue != null) consObject.put("notAny", conValue);
            }
            if (!consObject.isEmpty()) {
                whereObject.put(queryField, consObject);
            }
        }
    }

    private void initOrder() {

    }

    private void initLimit() {
        if (this.limit !=null && this.limit.size() != 0) {
            if (this.limit.size() == 1) {
                this.limitObject = String.valueOf(this.limit.get(0));
            }
            if (this.limit.size() == 2) {
                this.limitObject = String.valueOf(this.limit.get(0)) +
                        " OFFSET "+ String.valueOf(this.limit.get(1));
            }
        }
    }

    /**
     * 类型转换  主要区分是否加单引号 防止sql注入 number 类型不行加单引号 否则需要加
     *
     * @param valueType 转化后的值类型
     * @param value     转换前的值
     * @param key       conditionMap中的key  用户判断like,notLike
     * @return 转换后
     */
    private String typeConvert(String valueType, String value, String key) {
        if ("number".equals(valueType) && StringUtils.isNumeric(value)) {
            if ("like".equals(key) || "notLike".equals(key)) {
                value = "'%" + value + "%'";
            }
        } else {
            if ("like".equals(key) || "notLike".equals(key)) {
                value = "'%" + value + "%'";
            } else {
                value = "'" + value + "'";
            }
        }
        return value;
    }

    /**
     * 组合出sql的过滤添加  传递的值为字符串
     *
     * @param conditionKey   conditionMap中的key
     * @param conditionValue 组合前的value
     * @param valueType      在where中value类型
     * @param queryField     当前where的的查询字段 用于拼接sql
     * @return 组合后的sql语句
     */
    private String combineSqlConditionByString(String conditionKey, String conditionValue, String valueType, String queryField) {
        // 判断是否被指定了value的类型 如果没指定 则通过反射获取当前对象中属性的类型
        if (valueType == null && fieldsMap.containsKey(queryField)) {
            valueType = fieldsMap.get(queryField);
        }
        conditionValue = typeConvert(valueType, conditionValue, conditionKey);
        if (conditionValue != null) {
            conditionValue = conditionMap.get(conditionKey) + conditionValue;
        }
        return conditionValue;
    }

    /**
     * 组合出sql的过滤添加  传递的值为字符串
     *
     * @param conditionKey   conditionMap中的key
     * @param conditionValue 组合前的value
     * @param valueType      在where中value类型
     * @param queryField     当前where的的查询字段 用于拼接sql
     * @return 组合后的sql语句
     */
    private String getSqlValueByList(String conditionKey, List<String> conditionValue, String valueType, String queryField) {
        if (valueType == null && fieldsMap.containsKey(queryField)) {
            valueType = fieldsMap.get(queryField);
        }
        if ("between".equals(conditionKey) || "notBetween".equals(conditionKey)) {
            if (conditionValue.size() != 2) {
                return null;
            } else {
                String conValueTool = null;
                String conValue1 = typeConvert(valueType, conditionValue.get(0), conditionKey);
                String conValue2 = typeConvert(valueType, conditionValue.get(1), conditionKey);
                if (conValue1 != null && conValue2 != null) {
                    conValueTool = conditionMap.get(conditionKey) + conValue1 + " AND " + conValue2;
                }
                return conValueTool;
            }
        }
        if ("any".equals(conditionKey) || "notAny".equals(conditionKey)) {
            if (conditionValue.size() == 0) {
                return null;
            } else {
                StringBuilder tool = new StringBuilder();
                tool.append("(");
                for (String v : conditionValue) {
                    v = typeConvert(valueType, v, conditionKey);
                    if (v == null) {
                        return null;
                    }
                    tool.append(v);
                    tool.append(",");
                }
                tool.deleteCharAt(tool.length() - 1);
                tool.append(')');
                return conditionMap.get(conditionKey) + tool;
            }
        }
        return null;
    }

    /**
     * 通过反射获取字段对应类型简称( number  string )
     *
     * @param o 对应对象的类型
     */
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

    public String getLimitObject() {
        return limitObject;
    }

    public void setLimitObject(String limitObject) {
        this.limitObject = limitObject;
    }
}
