package cn.da0ke.example;

import android.app.Application;

import cn.da0ke.androidkit.map.MapUtils;


/**
 * Created by da0ke on 2018/11/8
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MapUtils.initialize(this);
    }
}
