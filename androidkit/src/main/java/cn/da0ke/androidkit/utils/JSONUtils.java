package cn.da0ke.androidkit.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by da0ke on 2018/9/30
 */
public class JSONUtils {

    public static String getStringValue(JSONObject json, String key) {
        String result;
        if(json.containsKey(key)) {
            result =  json.getString(key);
            if(result == null) {
                result = "";
            }
        } else {
            result =  "";
        }
        return result;
    }

    public static int getIntValue(JSONObject json,String key) {
        return json.getIntValue(key);
    }

    public static long getLongValue(JSONObject json,String key) {
        return json.getLongValue(key);
    }

    public static double getDoubleValue(JSONObject json,String key) {
        return json.getDoubleValue(key);
    }

    public static boolean getBooleanValue(JSONObject json,String key) {
        return json.getBooleanValue(key);
    }

}
