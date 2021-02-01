package cn.da0ke.example;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.da0ke.androidkit.dialog.ConfirmDialog;
import cn.da0ke.androidkit.dialog.ConfirmDialogStyle;
import cn.da0ke.androidkit.utils.AndroidUtils;

/**
 * Created by da0ke on 2018/12/22
 */
public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);


        findViewById(R.id.style1).setOnClickListener(v -> clickStyle1());
        findViewById(R.id.style2).setOnClickListener(v -> clickStyle2());
    }

    private void clickStyle1() {
        new ConfirmDialog.Builder(this)
                .message("确定要删除吗？")
                .positive("删除")
                .negative("取消")
                .onClickPositiveListener(new ConfirmDialog.OnClickPositiveListener() {
                    @Override
                    public void onClick() {
                        AndroidUtils.showMsg("删除");
                    }
                })
                .show();
    }

    private void clickStyle2() {
        new ConfirmDialog.Builder(this, ConfirmDialogStyle.Style2)
                .message("您好，邀请面试需要升级VIP会员")
                .positive("立即升级")
                .negative("取消")
                .onClickPositiveListener(new ConfirmDialog.OnClickPositiveListener() {
                    @Override
                    public void onClick() {
                        AndroidUtils.showMsg("立即升级");
                    }
                }).show();
    }



}
