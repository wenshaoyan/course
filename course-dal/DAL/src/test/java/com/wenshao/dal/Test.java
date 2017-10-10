package com.wenshao.dal;

import com.wenshao.dal.bean.CacheXmlBean;
import com.wenshao.dal.bean.UserBean;
import com.wenshao.dal.thriftgen.Banner;
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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
     * @b args
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

    static class Person {
        public String name;

    }

    static class Student extends Person {
        public Student() {
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

    public static void main3(String[] args) throws IllegalAccessException, InvocationTargetException {
        Banner banner = new Banner();
        banner.setId(1);
        banner.setUser_id(1);

        Object b = banner;
        Class userCla = (Class) b.getClass();
        //得到类中的所有属性集合
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); //设置些属性是可以访问的
            Object val = f.get(b);//得到此属性的值
            String type = f.getType().toString();//得到此属性的类型

            System.out.println("name:" + f.getName() + "\t value = " + val + "\t type=" + type);

            /*String type = f.getType().toString();//得到此属性的类型
            if (type.endsWith("String")) {
                System.out.println(f.getType()+"\t是String");
                f.set(b,"12") ;        //给属性设值
            }else if(type.endsWith("int") || type.endsWith("Integer")){
                System.out.println(f.getType()+"\t是int");
                f.set(b,12) ;       //给属性设值
            }else{
                System.out.println(f.getType()+"\t");
            }*/

        }
        /*
        * 得到类中的方法
        */
        Method[] methods = userCla.getMethods();
        ClassLoader classLoader = userCla.getClassLoader();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];

            if (method.getName().startsWith("get") && !"getFieldValue".equals(method.getName())) {
                System.out.print("methodName:" + method.getName() + "\t");
                System.out.println("value:" + method.invoke(b));//得到get 方法的值
            }
            /*if ("getFieldValue".equals(method.getName())){
                Class<?>[] parameterTypes = method.getParameterTypes();
                Class<?> parameterType = parameterTypes[0];
                if ("org.apache.thrift.TFieldIdEnum".equals(parameterType.getName())){
                    //System.out.print("methodName:"+method.getName()+"\t"+" i="+parameterType.getName());
                    System.out.println("value:"+method.invoke(b,Banner._Fields.CREATE_TIME));
                }

            }*/
        }

        //System.out.println(b);
    }
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        /*try {
            Object banners = new ArrayList<Banner>();
            Class<?> aClass = Class.forName("java.util.ArrayList");
            Object o = aClass.newInstance();
            System.out.println(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }*/
        List<String> strings = new ArrayList<String>();
        strings.add("111");

        Object o = strings;

        /*ArrayList<Banner> banners = new ArrayList<Banner>();
        Banner banner = new Banner();
        banner.setUser_id(11);
        banners.add(banner);
        Object obj = banners;
        if (obj instanceof ArrayList<?>){
            List<?> obj1 = (ArrayList<?>) obj;
            if (obj1.size()>0){
                Object o = obj1.get(0);
                Class<?> aClass = Class.forName(o.getClass().getName());
                //List<aClass> aClasses = new ArrayList<aClass>();
            }

            //test(obj1);
            for (Object o : obj1){
                System.out.println("===========");

                Class<?> aClass = Class.forName(o.getClass().getName());
                Object o1 = aClass.newInstance();
                System.out.println();
                System.out.println(o1);

            }

            System.out.println("===========");

        }
*/
    }
    public static void test(List<String> s){

    }

}
