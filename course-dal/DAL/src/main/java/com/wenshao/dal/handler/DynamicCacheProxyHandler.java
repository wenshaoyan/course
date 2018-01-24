package com.wenshao.dal.handler;

import com.wenshao.dal.Interceptor.CacheInterceptor;
import com.wenshao.dal.thriftgen.RequestException;
import com.wenshao.dal.util.ExceptionUtil;
import com.wenshao.dal.util.MD5Util;
import org.apache.thrift.TException;

import javax.print.attribute.standard.NumberUp;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by wenshao on 2017/10/8.
 * 反射实现缓存拦截器
 */
public class DynamicCacheProxyHandler implements InvocationHandler {
    private String serverName;
    public DynamicCacheProxyHandler(String _serverName){
        this.serverName = _serverName;
    }
    // 声明被代理对象
    private Object business;

    // 创建拦截器
    private CacheInterceptor interceptor = new CacheInterceptor();

    /**
     * 　　* 动态生成一个代理类对象，并绑定被代理类和代理处理器。
     * 　　*
     * 　　* @param business
     * 　　* @return 代理类对象
     */
    public Object bind(Object business) {
        this.business = business;

        /**
         * Proxy.newProxyInstance(参数1, 参数2, 参数3)
         *
         * 参数1, 表示被代理类的 ClassLoader
         * 参数2, 表示被代理的接口
         * 参数3, 表示代理处理器对象
         *
         * 该方法，返回代理实例
         */

        return Proxy.newProxyInstance(business.getClass().getClassLoader(),
                business.getClass().getInterfaces(),
                this);
    }

    /**
     * 　　* 代理需要调用的方法，并在该方法调用前后，先调用连接器的方法。
     * 　　*
     * 　　* @param proxy 代理类对象
     * 　　* @param method 被代理的接口方法
     * 　　* @param args 被代理接口方法的参数
     * 　　* @return 方法调用返回的结果
     * 　　* @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws RequestException {
        Object result = null;

        String md5Key = null;
        try {
            md5Key = getMd5Key(args);
            result = interceptor.before(method.getName(), md5Key);
            if (result == null) {
                result = method.invoke(business, args);
                interceptor.after(method.getName(), md5Key,result);
                return result;
            } else {
                return result;
            }
        } catch (Exception e) {     // 反射的结果
            e.printStackTrace();
            Throwable cause = e.getCause();
            RequestException qe;
            if (cause instanceof RequestException){
                qe = (RequestException)cause;
            } else {
                qe = ExceptionUtil.getUnknownE();
            }
            qe.serverName = this.serverName;
            qe.methodName = method.getName();
            throw qe;
        }
    }


    private String getMd5Key(Object[] args) throws Exception {
        if (args == null || args[0] == null) {
            return MD5Util.getMD5("all");
        }
        StringBuilder sb = new StringBuilder();
        try {
            for (Object o : args) {
                if (o.getClass().isPrimitive()) {    // 简单类型
                    sb.append(o);
                } else {
                    if (o instanceof Number) {
                        sb.append(o);
                    } else {
                        setObjectValues(o, sb);
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return MD5Util.getMD5(sb.toString());
    }

    private void setObjectValues(Object o, StringBuilder sb) throws InvocationTargetException, IllegalAccessException {
        Class userCla = (Class) o.getClass();
        Method[] methods = userCla.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getName().startsWith("get") && (!"getFieldValue".equals(method.getName()) && !"getClass".equals(method.getName()))) {
                Object invoke = method.invoke(o);
                sb.append(invoke);
            }
        }
    }
}
