package cn.da0ke.example;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;

import cn.da0ke.androidkit.utils.AndroidUtils;
import cn.da0ke.androidkit.utils.ToastUtils;

public class ToastActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        findViewById(R.id.normal).setOnClickListener(v -> {
            ToastUtils.msg("呵呵");
        });

        findViewById(R.id.blue).setOnClickListener(v -> {
            ToastUtils.msg("我是有背景色的哦", Color.parseColor("#668ABC"));
        });
    }
}
