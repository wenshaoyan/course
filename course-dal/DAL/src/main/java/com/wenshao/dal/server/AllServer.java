package com.wenshao.dal.server;

/**
 * Created by wenshao on 2017/9/4
 * 所有服务 解决内存不足
 */
public class AllServer {
    public static void main(final String[] args) {
        new Thread(){
            public void run() {
                UserServer.main(args);
            }
        }.start();
        new Thread(){
            public void run() {
                BannerServer.main(args);
            }
        }.start();
        new Thread(){
            public void run() {
                ClientServer.main(args);
            }
        }.start();
        new Thread(){
            public void run() {
                CourseServer.main(args);
            }
        }.start();
    }
}
