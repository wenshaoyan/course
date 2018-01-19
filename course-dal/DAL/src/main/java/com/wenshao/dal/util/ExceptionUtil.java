package com.wenshao.dal.util;

import com.wenshao.dal.thriftgen.RequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wenshao on 2018/1/19.
 * 标准化异常
 */
public class ExceptionUtil {
    private static Logger logger = LogManager.getLogger(ExceptionUtil.class);

    public static RequestException getUnknownE(){
        RequestException qe = new RequestException();
        qe.code = 1;
        qe.message = "未知异常";
        return qe;
    }
    public static RequestException getParameterE(){
        RequestException qe = new RequestException();
        qe.code = 10;
        qe.message = "参数错误";
        return qe;
    }
    public static RequestException getSqlE(Exception e){
        return getSqlE();
    }
    public static RequestException getSqlE(){
        RequestException qe = new RequestException();
        qe.code = 10;
        qe.message = "sql执行错误";
        return qe;
    }
}
