package com.wenshao.coursate.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.wenshao.coursate.R;
import com.wenshao.coursate.bean.HttpApiResponse;
import com.wenshao.coursate.bean.UserBean;
import com.wenshao.coursate.config.ServerConfig;
import com.wenshao.coursate.constant.SpConstant;
import com.wenshao.coursate.util.JsonCallback;
import com.wenshao.coursate.util.SpUtil;

/**
 * Created by Administrator on 2017/9/20.
 * 欢迎页面
 * app打开的第一个页面
 */

public class SplashActivity extends Activity {
    private final static String TAG = "SplashActivity";
    private Context mContext;
    private Intent nextIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_splash);
        autoLogin();
        initLocalData();
        initTimer();
    }



    /**
     * 如果用户进行了登录 则自动登录
     */
    private void autoLogin() {
        String userName = SpUtil.getString(mContext, SpConstant.USER_NAME, null);
        String userPassword = SpUtil.getString(mContext, SpConstant.USER_PASSWORD, null);
        boolean userAuth = SpUtil.getBoolean(mContext, SpConstant.USER_AUTH, false);

        OkGo.<HttpApiResponse<UserBean>>get(ServerConfig.HTTP_USER_V1)                            // 请求方式和请求url
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("login")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)  // 缓存模式，详细请看缓存介绍
                .params("tel","15712821772")
                .params("password","e10adc3949ba59abbe56e057f20f883e")
                //.upJson()
                .execute(new JsonCallback<HttpApiResponse<UserBean>>() {
                    @Override
                    public void onSuccess(Response<HttpApiResponse<UserBean>> httpApiResponseResponse) {
                        writeUserInfo(httpApiResponseResponse.body().data);
                    }
                });
        if (userAuth && userName != null && userPassword != null) {


        }else{
            if (userAuth){
                SpUtil.putBoolean(mContext,SpConstant.USER_AUTH, false);
            }
        }


    }
    private void writeUserInfo(UserBean userBean){

    }

    /**
     * 获取本地存储的数据
     */
    private void initLocalData() {
        int guideVersion = SpUtil.getInt(mContext, SpConstant.GUIDE_VERSION, 0);
        if (guideVersion == 0) { // 跳往引导页面
            nextIntent = new Intent(mContext, GuideActivity.class);

        } else {  // 跳往首页
            nextIntent = new Intent(mContext,IndexActivity.class);
        }

    }
    private void initTimer() {
        final SplashActivity splashActivity = this;
        TimerTask task = new TimerTask(){
            public void run(){
                startActivity(nextIntent);
                splashActivity.finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 2000);
    }

    /**
     * 获取网络的数据
     */
    private void initNetData() {
        this.finish();
    }

    private void initUi() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        //stringStringHashMap

        /*Button btn_b = (Button) findViewById(R.id.button);
        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkGo.<String>post("http://192.168.1.100:10001/api/user/v1/test")                            // 请求方式和请求url
                        .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                        .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                        .cacheMode(CacheMode.DEFAULT)  // 缓存模式，详细请看缓存介绍
                        //.upJson()
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                System.out.println(response);
                            }
                        });
            }
        });*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
