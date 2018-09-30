package cn.da0ke.androidkit.utils;

import okhttp3.OkHttpClient;

/**
 * Created by da0ke on 2018/9/30
 */
enum Singleton {

    INSTANCE;
    private OkHttpClient httpClient;

    private Singleton() {
        httpClient = new OkHttpClient();
//        httpClient = new OkHttpClient.Builder()
//                .connectTimeout(10, TimeUnit.SECONDS) //连接超时时间
//                .readTimeout(10, TimeUnit.SECONDS) //读取超时时间
//                .writeTimeout(10, TimeUnit.SECONDS) //写入超时时间
//                .build();
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

}
