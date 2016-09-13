package com.example.tangyi.travels.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by aCandy on 2016/9/10.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
