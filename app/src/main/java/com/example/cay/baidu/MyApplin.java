package com.example.cay.baidu;

import android.app.Application;

import com.example.cay.baidu.http.HttpUtils;

/**
 * Created by Cay on 2017/3/9.
 */

public class MyApplin extends Application {
    public static String UP_MOVIE_IP =null;
    @Override
    public void onCreate() {
        super.onCreate();
        HttpUtils.getInstance().setContext(getApplicationContext());
    }
}
