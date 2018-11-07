package cn.da0ke.androidkit.takephoto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.TResult;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import cn.da0ke.androidkit.utils.AndroidUtils;

/**
 * Created by da0ke on 2018/10/6
 */
public class MyTakePhotoActivity extends org.devio.takephoto.app.TakePhotoActivity {


    Uri outPutUri;

    //同一个页面有多个对象时区分，如logo，营业执照
    private int tp;

    public static final File PHOTO_DIR = new File( //图片保存路径
            Environment.getExternalStorageDirectory()+File.separator
                    + Environment.DIRECTORY_DCIM + File.separator + "da0ke");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tp = getIntent().getIntExtra("tp",0);
        //裁剪图片比例
        int aspectX = getIntent().getIntExtra("aspectX", 1);
        int aspectY = getIntent().getIntExtra("aspectY", 1);

        if ((!PHOTO_DIR.exists() && PHOTO_DIR.mkdirs()) || PHOTO_DIR.exists()) {
            outPutUri = Uri.fromFile(new File(PHOTO_DIR, generateFileName()));

            TakePhoto takePhoto = getTakePhoto();

            CompressConfig compressConfig = new CompressConfig.Builder().setMaxSize(200*1024).setMaxPixel(600).create();
            takePhoto.onEnableCompress(compressConfig,true);

            CropOptions cropOptions = new CropOptions.Builder().setAspectX(aspectX).setAspectY(aspectY).setWithOwnCrop(true).create();

            int type = getIntent().getIntExtra("type", 0);
            if(type == 0) { //从相机获取图片并裁剪
                takePhoto.onPickFromCaptureWithCrop(outPutUri,cropOptions);
            } else { //从相册中获取图片并裁剪
                takePhoto.onPickFromGalleryWithCrop(outPutUri, cropOptions);
            }
        } else {
            AndroidUtils.showMsg("SD卡出错");
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);

        Intent data = new Intent();
        data.putExtra("image",result.getImage());
        data.putExtra("tp",tp);

        setResult(RESULT_OK,data);
        finish();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);

        Intent data = new Intent();
        data.putExtra("msg",msg);
        data.putExtra("tp",tp);

        setResult(500,data);
        finish();
    }

    @Override
    public void takeCancel() {
        super.takeCancel();

        setResult(RESULT_CANCELED);
        finish();
    }

    /**
     * 生成以".jpg"为后缀的文件名
     */
    private String generateFileName() {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
        String formatDate = format.format(new Date());
        int random = new Random().nextInt(10000);
        return formatDate + random + ".jpg";
    }
}
