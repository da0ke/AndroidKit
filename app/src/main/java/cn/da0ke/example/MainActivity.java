package cn.da0ke.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

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

        findViewById(R.id.takephoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTakephoto();
            }
        });
    }

    private void clickDialog() {
        Intent intent = new Intent(this, DialogActivity.class);
        startActivity(intent);
    }

    private void clickTakephoto() {
        AndroidUtils.showMsg("开发中");
    }


}
