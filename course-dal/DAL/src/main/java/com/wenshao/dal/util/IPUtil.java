package com.wenshao.dal.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wenshao on 2017/9/7
 */
public class IPUtil {

    // 获取外网ip
    public static String getNetIp(){
        return getMyIp().get("netIp");
    }
    // 获取内网ip
    public static String getLocalIp(){
        return getMyIp().get("localIp");
    }
    @SuppressWarnings("rawtypes")
    private static Map<String, String> getMyIp() {
        String localIp = null;// 本地IP，如果没有配置外网IP则返回它
        String netIp = null;// 外网IP
        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            boolean finded = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !finded) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                Enumeration address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = (InetAddress) address.nextElement();
                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {// 外网IP
                        netIp = ip.getHostAddress();
                        finded = true;
                        break;
                    } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {// 内网IP
                        localIp = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        /*if (netIp != null && !"".equals(netIp)) {
            return netIp;
        } else {
            return localIp;
        }*/
        Map<String, String> map = new HashMap<String, String>();
        map.put("netIp",netIp);
        map.put("localIp",localIp);
        return  map;
    }
}
