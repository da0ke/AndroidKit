package cn.da0ke.example;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.devio.takephoto.model.TImage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.da0ke.androidkit.takephoto.TakePhotoUtils;

/**
 * Created by da0ke on 2018/11/7
 */
public class TakePhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takephoto);

        findViewById(R.id.takephoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTakePhoto();
            }
        });
    }

    private void clickTakePhoto() {
        TakePhotoUtils.takePhoto(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case TakePhotoUtils.REQUEST_CODE_TAKEPHOTO: {
                if(resultCode == RESULT_OK) {
                    TImage image = (TImage) data.getSerializableExtra("image");
                    Bitmap bitmap = BitmapFactory.decodeFile(image.getCompressPath());
                    ImageView _image = findViewById(R.id.image);
                    _image.setImageBitmap(bitmap);
                }
                break;
            }

        }
    }
}
