package com.wenshao.dal.util;
import org.apache.commons.beanutils.Converter;

import java.sql.Timestamp;

/**
 * Created by wenshao on 2017/9/28.
 * 将字符串的时间转sql的Timestamp
 */
public class DateStringToTimeLocaleConverter implements Converter {
    @Override
    public <T> T convert(Class<T> aClass, Object value) {
        if( aClass != Timestamp.class) return null;
        if(value == null || "".equals(value.toString().trim())) return null;
        //return new Timestamp((Long) value);
        return null;
    }
}
