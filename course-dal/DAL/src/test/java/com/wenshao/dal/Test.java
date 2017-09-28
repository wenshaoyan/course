package com.wenshao.dal;

import com.wenshao.dal.bean.UserBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.beanutils.locale.converters.SqlTimeLocaleConverter;
import org.apache.commons.beanutils.locale.converters.SqlTimestampLocaleConverter;
import org.apache.ibatis.annotations.Insert;

import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wenshao on 2017/9/7
 * 测试
 */
public class Test {
    /**
     * @param args
     */
    public static void main1(String[] args) {
        System.out.println(getMyIp());
    }

    @SuppressWarnings("rawtypes")
    public static String getMyIp() {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP
        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            boolean finded = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !finded) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                Enumeration address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = (InetAddress) address.nextElement();
                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                        netip = ip.getHostAddress();
                        finded = true;
                        break;
                    } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                        localip = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        System.out.println(netip+localip);
        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }
    public static void main(String[] args) {
        UserBean newUser=new UserBean();
        String s ="2017-09-27 10:01:01";
        ConvertUtils.register(new SqlTimestampLocaleConverter(Locale.CHINA,"yyyy-MM-dd HH:mm:ss.SSS"),Timestamp.class);
        /*ConvertUtils.register(new Converter(){
            @Override
            public Object convert(Class type, Object value) {
                if( type != Timestamp.class) return null;
                if(value == null || "".equals(value.toString().trim())) return null;
                return new Timestamp((Long) value);
            }
        },Timestamp.class);*/
        try {
            BeanUtils.copyProperty(newUser, "register_time", s);
            BeanUtils.copyProperty(newUser, "id", 2);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(newUser.toString());
    }
}
