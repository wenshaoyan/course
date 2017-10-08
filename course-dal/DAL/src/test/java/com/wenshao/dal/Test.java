package com.wenshao.dal;

import com.wenshao.dal.bean.CacheXmlBean;
import com.wenshao.dal.bean.UserBean;
import com.wenshao.dal.thriftgen.User;
import com.wenshao.dal.util.JaxbUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.beanutils.locale.converters.SqlTimeLocaleConverter;
import org.apache.commons.beanutils.locale.converters.SqlTimestampLocaleConverter;
import org.apache.commons.beanutils.locale.converters.StringLocaleConverter;
import org.apache.ibatis.annotations.Insert;

import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
        System.out.println(netip + localip);
        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }
    static class Person{
        public String name;

    }
    static class Student extends Person{
        public Student(){
            super();
        }

    }
    //定义一个打印信息方法
    public static void printPersonInfo(List<? extends Person> persons) {
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    public static void main2(String[] args) {

        List<Student> persons = new ArrayList<Student>();
        persons.add(new Student());
        persons.add(new Student());
        persons.add(new Student());
        //这里调用是没有问题的
        printPersonInfo(persons);


    }
    public static void main3(String[] args) {

        System.out.println("=============");
    }



}
