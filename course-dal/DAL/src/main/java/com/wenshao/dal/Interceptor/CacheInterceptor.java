package com.wenshao.dal.Interceptor;

import com.wenshao.dal.bean.CacheBean;
import com.wenshao.dal.bean.CacheXmlBean;
import com.wenshao.dal.server.Server;
import com.wenshao.dal.thriftgen.Banner;
import com.wenshao.dal.thriftgen.CacheService;
import com.wenshao.dal.util.CacheClientUtil;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wenshao on 2017/10/8.
 * 缓存拦截器
 */
public class CacheInterceptor {
    private static Logger logger = Logger.getLogger(String.valueOf(CacheInterceptor.class));

    // 在 action 之前调用
    public Object before(String actionName, String md5Key) {

        CacheService.Client cacheClient = CacheClientUtil.getCacheClient();
        CacheXmlBean cacheXmlBean = CacheClientUtil.getCacheXmlBean();
        // 1 检查是否配置了缓存
        if (cacheXmlBean == null) {
            return null;
        }
        Map<String, CacheBean> cacheBeans = cacheXmlBean.getCacheBeans();
        if (!cacheBeans.containsKey(actionName)){
            return null;
        }
        CacheBean cacheBean = cacheBeans.get(actionName);
        if (cacheBean == null || cacheBean.getMethodGet()==null || cacheBean.getMethodPut()==null){
            return null;
        }
        try {
            // 3 存在缓存则返回缓存中的数据 否则返回空
            Class userCla = (Class)cacheClient.getClass();
            Class[] paramsTypes = new Class[]{String.class};

            Method method = userCla.getMethod(cacheBean.getMethodGet(),paramsTypes);
            Object[] args = new Object[]{md5Key};
            Object invoke = method.invoke(cacheClient, args);
            logger.debug("获取缓存");
            return invoke;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return  null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return  null;
        } catch (InvocationTargetException e) { // 没有缓存
            e.printStackTrace();
            return  null;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }

    }

    // 在 action 之后调用
    public void after(String actionName, String md5Key,Object data) {
        CacheService.Client cacheClient = CacheClientUtil.getCacheClient();
        CacheXmlBean cacheXmlBean = CacheClientUtil.getCacheXmlBean();
        List<Banner> banners = new ArrayList<Banner>();
        try {
            logger.debug("设置缓存");
            cacheClient.bannerPut(md5Key,banners);
        } catch (TException e) {
            e.printStackTrace();
        }
    }


}
