package cn.da0ke.example;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import cn.da0ke.androidkit.dialog.ConfirmDialog;
import cn.da0ke.androidkit.utils.AndroidUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDialog();
            }
        });
    }

    private void clickDialog() {
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


}
