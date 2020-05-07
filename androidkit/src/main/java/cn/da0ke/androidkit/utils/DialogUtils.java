package cn.da0ke.androidkit.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dyhdyh.widget.loadingbar2.LoadingBar;

import cn.da0ke.androidkit.R;

public class DialogUtils {

    public static void showLoading(Context context) {
        //以Dialog的形式显示Loading
        //注意这里context不能用ApplicationContext
        LoadingBar.dialog(context)
                .setFactoryFromResource(R.layout.androidkit_dialog_loading)
                .show();
    }

    public static void hideLoading(Context context) {
        //取消Dialog
        //需要跟show方法传的是同一个context 才能正确取消
        LoadingBar.dialog(context).cancel();
    }



}
