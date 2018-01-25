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
        conMap.put("any", " IN ");
        conMap.put("notAny", " NOT IN ");
        conMap.put("like", " LIKE ");
        conMap.put("notLike", " NOT LIKE ");
    }

    public AbstractSqlBean(AbstractSql abstractSql, Class clazz) throws RequestException {
        super(abstractSql);
        Map<String, Where> where = this.where;
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

        for (Map.Entry<String, Where> entry : where.entrySet()) {
            Where queryValue = entry.getValue();
            String queryField = entry.getKey();
            String valueType = null;
            if (queryValue.getType() != null) {
                valueType = queryValue.getType();
            }
            Map<String, String> consObject = new HashMap<String, String>();

            if (queryValue.getEq() != null) {
                String conValue = getSqlValueByString("eq", queryValue.getEq(), valueType, queryField);
                if (conValue != null) consObject.put("eq", conValue);
            }
            if (queryValue.getNe() != null) {
                String conValue = getSqlValueByString("ne", queryValue.getNe(), valueType, queryField);
                if (conValue != null) consObject.put("ne", conValue);
            }

            if (queryValue.getGt() != null) {
                String conValue = getSqlValueByString("gt", queryValue.getGt(), valueType, queryField);
                if (conValue != null) consObject.put("gt", conValue);
            }
            if (queryValue.getGte() != null) {
                String conValue = getSqlValueByString("gte", queryValue.getGte(), valueType, queryField);
                if (conValue != null) consObject.put("gte", conValue);
            }
            if (queryValue.getLt() != null) {
                String conValue = getSqlValueByString("lt", queryValue.getLt(), valueType, queryField);
                if (conValue != null) consObject.put("lt", conValue);
            }
            if (queryValue.getLte() != null) {
                String conValue = getSqlValueByString("lte", queryValue.getLte(), valueType, queryField);
                if (conValue != null) consObject.put("lte", conValue);
            }
            if (queryValue.getLike() != null) {
                String conValue = getSqlValueByString("like", queryValue.getLike(), valueType, queryField);
                if (conValue != null) consObject.put("like", conValue);
            }
            if (queryValue.getNotLike() != null) {
                String conValue = getSqlValueByString("notLike", queryValue.getLike(), valueType, queryField);
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

    private String typeConvert(String valueType, String value, String key) {
        if ("number".equals(valueType) && StringUtils.isNumeric(value)) {
            if ("like".equals(key) || "notLike".equals(key)) {
                value = "'%" + value + "%'";
            }
        } else if ("string".equals(valueType)) {
            if ("like".equals(key) || "notLike".equals(key)) {
                value = "'%" + value + "%'";
            } else {
                value = "'" + value + "'";
            }
        } else {
            value = null;
        }
        return value;
    }

    private String getSqlValueByString(String conKey, String conValue, String valueType, String queryField) {
        if (valueType == null && fieldsMap.containsKey(queryField)) {
            valueType = fieldsMap.get(queryField);
        }
        conValue = typeConvert(valueType, conValue,conKey);
        if (conValue != null) {
            conValue = conMap.get(conKey) + conValue;
        }
        return conValue;
    }

    private String getSqlValueByList(String conKey, List<String> conValue, String valueType, String queryField) {
        if (valueType == null && fieldsMap.containsKey(queryField)) {
            valueType = fieldsMap.get(queryField);
        }
        if ("between".equals(conKey) || "notBetween".equals(conKey)) {
            if (conValue.size() != 2) {
                return null;
            } else {
                String conValueTool = null;
                String conValue1 = typeConvert(valueType, conValue.get(0), conKey);
                String conValue2 = typeConvert(valueType, conValue.get(1), conKey);
                if (conValue1 != null && conValue2 != null) {
                    conValueTool = conMap.get(conKey) + conValue1 + " AND " + conValue2;
                }
                return conValueTool;
            }
        }
        if ("any".equals(conKey) || "notAny".equals(conKey)) {
            if (conValue.size() == 0) {
                return null;
            } else {
                StringBuffer tool = new StringBuffer();
                tool.append("(");
                for (String v : conValue) {
                    v = typeConvert(valueType, v, conKey);
                    if (v == null) {
                        return null;
                    }
                    tool.append(v);
                    tool.append(",");
                }
                tool.deleteCharAt(tool.length() - 1);
                tool.append(')');
                return conMap.get(conKey) + tool;
            }
        }
        return null;
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
