package com.wenshao.coursate.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wenshao on 2017/3/13.
 * sp操作
 */

public class SpUtil {
    private static SharedPreferences config;
    private static SharedPreferences token;


    /**
     * 写入
     *
     * @param context 上下文
     * @param key     写入名称
     * @param value   写入值
     */
    public static void putBoolean(Context context, String key, boolean value) {
        if (config == null) {
            config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        config.edit().putBoolean(key, value).apply();
    }

    /**
     * 读取
     *
     * @param context  上下文
     * @param key      读取名称
     * @param defValue 默认值
     * @return 对应的值
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (config == null) {
            config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return config.getBoolean(key, defValue);
    }

    /**
     * 写入
     *
     * @param context 上下文
     * @param key     写入名称
     * @param value   写入值
     */
    public static void putString(Context context, String key, String value) {
        if (config == null) {
            config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        config.edit().putString(key, value).apply();
    }

    /**
     * 读取
     *
     * @param context  上下文
     * @param key      读取名称
     * @param defValue 默认值
     * @return 对应的值
     */
    public static String getString(Context context, String key, String defValue) {

        if (config == null) {
            config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return config.getString(key, defValue);
    }

    public static void remove(Context context, String key) {
        if (config == null) {
            config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        config.edit().remove(key).apply();
    }

    public static int getInt(Context context, String key, int defValue) {
        if (config == null) {
            config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return config.getInt(key, defValue);
    }

    public static void putInt(Context context, String key, int value) {
        if (config == null) {
            config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        config.edit().putInt(key, value).apply();
    }

    /**
     * 获取tokenID  判断当前token是否过期
     *
     * @param context 上下文
     * @return 获取到则返回保存的tokenID 没有则返回空
     */
    public static String getToken(Context context,String domain) {
        if (token == null) {
            token = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        }
        String tokenID;
        try {
            tokenID = token.getString(domain+":tokenId", null);
        }catch (Exception e){
            return null;
        }
        if (tokenID != null ) {
            Log.i("getToken", "getToken: "+tokenID);
            return tokenID;
        } else {
            return null;
        }

    }
    /**
     * 保存服务器返回的tokenID
     *
     * @param context  上下文
     * @param tokenStr tokenID=dwef7f773f3f3f3fsdq&time=112344455
     */
    public static void setToken(Context context, String tokenStr,String domain) {
        if (token == null) {
            token = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        }
        if (tokenStr==null || domain ==null){
            return;
        }
        Map<String, String> map = new HashMap<>();
        StringBuilder key = new StringBuilder();
        StringBuilder val = new StringBuilder();
        boolean tag = true;
        for (int index = 0; index < tokenStr.length(); index++) {
            char c = tokenStr.charAt(index);
            if (c != '=' && c != '&' && tag) {
                key.append(c);
            } else if (c != '=' && c != '&' && !tag) {
                val.append(c);
            } else if (c == '=') {
                tag = false;
            } else {
                map.put(key.toString(), val.toString());
                key.setLength(0);
                val.setLength(0);
                tag = true;
            }
        }
        map.put(key.toString(), val.toString());

        try{
            String tokenId = map.get("tokenId");
            if (tokenId!=null){
                SharedPreferences.Editor edit = token.edit();
                edit.putString(domain+":tokenId",tokenId);
                edit.apply();
            }

            //token.edit().putString(domain+":tokenID",tokenID).putLong(domain+":time",time).apply();
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
