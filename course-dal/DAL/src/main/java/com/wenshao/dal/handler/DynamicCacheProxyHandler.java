package com.wenshao.dal.handler;

import com.wenshao.dal.Interceptor.CacheInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wenshao on 2017/10/8.
 * 反射实现缓存拦截器
 */
public class DynamicCacheProxyHandler implements InvocationHandler {
    // 声明被代理对象
    private Object business;

    // 创建拦截器
    private CacheInterceptor interceptor = new CacheInterceptor();

    /**
     　　* 动态生成一个代理类对象，并绑定被代理类和代理处理器。
     　　*
     　　* @param business
     　　* @return 代理类对象
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
 　　* 代理需要调用的方法，并在该方法调用前后，先调用连接器的方法。
 　　*
 　　* @param proxy 代理类对象
 　　* @param method 被代理的接口方法
 　　* @param args 被代理接口方法的参数
 　　* @return 方法调用返回的结果
 　　* @throws Throwable
 　　*/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        String md5Key = getMd5Key(args);
        result = interceptor.before(method.getName(), md5Key);
        if (result==null){
            result = method.invoke(business, args);
            interceptor.after(method.getName(),md5Key,result);
            return result;
        }else{
            return result;
        }

    }


    private String getMd5Key(Object[] args){
        StringBuilder sb = new StringBuilder();

        return "abc";
    }
}
