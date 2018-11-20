package cn.da0ke.example;

import android.app.Application;

import cn.da0ke.androidkit.utils.AndroidUtils;

/**
 * Created by da0ke on 2018/11/20
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidUtils.init(this);
    }
}
