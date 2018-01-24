package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.AbstractSql;
import com.wenshao.dal.util.ExceptionUtil;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by wenshao on 2018/1/24.
 * 查询抽象sql类\
 * where
 * eq: '1'
 */
public class AbstractSqlBean extends AbstractSql {
    private Map<String, Map<String, Object>> whereObject = new HashMap<String, Map<String, Object>>();
    private final static Set<String> conSet = new HashSet<String>();
    private final static Set<String> valueTypeSet = new HashSet<String>();
    static {
        conSet.add("eq");
        conSet.add("ne");
        conSet.add("gt");
        conSet.add("gte");
        conSet.add("lt");
        conSet.add("lte");
        conSet.add("between");
        conSet.add("notBetween");
        conSet.add("in");
        conSet.add("notIn");
        conSet.add("like");
        conSet.add("notLike");
    }
    public AbstractSqlBean(AbstractSql abstractSql,  Class clazz) {
        super(abstractSql);
        Map<String, Map<String, String>> where = this.where;
        Class<?> aClass  = null;
        try {

            aClass = Class.forName(clazz.getName());
            Object o = aClass.newInstance();
            List<Map<String,Object>> list = getFieldsInfo(o);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, Map<String, String>> entry : where.entrySet()) {
            Map<String, String> queryValue = entry.getValue();
            String queryField = entry.getKey();
            String valueType = null;
            if (queryValue.containsKey("type")) {
                valueType = queryValue.get("type");
            }
            Map<String, Object> consObject = new HashMap<String, Object>();
            for (Map.Entry<String, String> map : queryValue.entrySet()) {
                String conValue = map.getValue();
                String conKey = map.getKey();
                if (conSet.contains(conKey)) {
                    Object v = conValue;
                    if (valueType != null) {
                        try {
                            if ("int".equals(valueType)) {
                                v = Integer.parseInt(conValue);
                            }
                            consObject.put(conKey, v);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                }

            }

        }
    }

    private void typeConvert () {

    }
    private List<Map<String,Object>> getFieldsInfo(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> infoMap = null;
        for (Field field : fields) {
            infoMap = new HashMap<String, Object>();
            infoMap.put("type", field.getType().toString());
            System.out.println(field.getType().toString());
            infoMap.put("name", field.getName());
            System.out.println(field.getName());
            list.add(infoMap);
        }
        return list;
    }
    public Map<String, Map<String, Object>> getWhereObject() {
        return whereObject;
    }

    public void setWhereObject(Map<String, Map<String, Object>> whereObject) {
        this.whereObject = whereObject;
    }


}
