package com.wenshao.coursate.bean;

/**
 * Created by wenshao on 2017/9/23.
 * http 请求响应数据
 */

public class HttpApiResponse<T> {
    public int code;
    public String message;
    public T data;
    public boolean success;

}
