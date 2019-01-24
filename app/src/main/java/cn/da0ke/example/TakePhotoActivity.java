package cn.da0ke.example;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TakePhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takephoto);

        findViewById(R.id.aspect_4_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aspectRatio(4, 3);
            }
        });

        findViewById(R.id.free).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aspectRatio(0, 0);
            }
        });
    }

    private void aspectRatio(int aspectX, int aspectY) {

    }

}
