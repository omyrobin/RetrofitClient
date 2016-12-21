package com.http.retrofitclient;

import android.app.Application;

/**
 * Created by omyrobin on 2016/11/17.
 */
public class MyApplication extends Application {

    public static MyApplication instance;

    //用来判断是否被强杀的状态标识
    public static int mAppStatus = -1;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance(){
        return instance;
    }

}
