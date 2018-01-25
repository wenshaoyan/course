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
        qe.code = 800;
        qe.message = "未知异常";
        return qe;
    }
    public static RequestException getParameterE(){
        RequestException qe = new RequestException();
        qe.code = 801;
        qe.message = "参数错误";
        return qe;
    }
    public static RequestException getSqlE(){
        RequestException qe = new RequestException();
        qe.code = 802;
        qe.message = "sql执行错误";
        return qe;
    }
    public static RequestException getClassE(){
        RequestException qe = new RequestException();
        qe.code = 803;
        qe.message = "反射class错误";
        return qe;
    }
    public static RequestException getClassE(Exception ex){
        RequestException re = ExceptionUtil.getClassE();
        re.fullMessage = ExceptionUtil.getExceptionAllInformation(ex);
        return re;
    }
    private static String getExceptionAllInformation(Exception ex){
        StringBuilder sOut = new StringBuilder();
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
            sOut.append("\tat ");
            sOut.append(s);
            sOut.append("\r\n" );
        }
        return sOut.toString();
    }
}
