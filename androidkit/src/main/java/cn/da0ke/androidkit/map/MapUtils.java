package cn.da0ke.androidkit.map;

import android.content.Context;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by da0ke on 2018/11/8
 */
public class MapUtils {

    public static void initialize(Context applicationContext) {
        SDKInitializer.initialize(applicationContext);
    }

}
