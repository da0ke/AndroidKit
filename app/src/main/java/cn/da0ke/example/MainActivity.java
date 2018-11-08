package cn.da0ke.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import cn.da0ke.androidkit.map.MapActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMap();
            }
        });
    }

    private void clickMap() {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("com","苏州某某网络信息技术有限公司");
        intent.putExtra("address","盛泽镇舜湖西路");
        intent.putExtra("lat",30.910196);
        intent.putExtra("lng",120.632369);
        startActivity(intent);
    }


}
