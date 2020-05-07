package cn.da0ke.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


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

        findViewById(R.id.toast).setOnClickListener(v -> clickToast());
    }

    private void clickDialog() {
        Intent intent = new Intent(this, DialogActivity.class);
        startActivity(intent);
    }

    private void clickToast() {
        startActivity(new Intent(this, ToastActivity.class));
    }

}
