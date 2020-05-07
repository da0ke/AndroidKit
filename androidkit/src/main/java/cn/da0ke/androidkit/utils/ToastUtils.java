package cn.da0ke.androidkit.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.da0ke.androidkit.R;

public class ToastUtils {

    /**
     * 显示吐司
     */
    public static void msg(String msg, int bgColor) {
        LayoutInflater inflate = (LayoutInflater) AndroidUtils.getApp().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View toastView = inflate.inflate(R.layout.androidkit_toast,null);
        TextView tv = toastView.findViewById(R.id.TextViewInfo);
        tv.setBackgroundColor(bgColor);
        tv.setText(msg);
        Toast toast = new Toast(AndroidUtils.getApp());
        toast.setView(toastView);
        toast.show();
    }

    /**
     * 显示吐司
     */
    public static void msg(String msg) {
        msg(msg, Color.parseColor("#000000"));
    }

}
