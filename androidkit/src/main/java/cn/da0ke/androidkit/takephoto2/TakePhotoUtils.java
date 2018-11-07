//package cn.da0ke.androidkit.takephoto2;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.net.Uri;
//
//
///**
// * Created by da0ke on 2018/11/7
// */
//public class TakePhotoUtils {
//
//    public static final int REQUEST_CODE_TAKEPHOTO = 2001;
//    private static int tp = 0;
//    private static int aspectX = 1;
//    private static int aspectY = 1;
//    private static int maxWidth = 600;
//    private static int maxHeight = 600;
//
//    public static void takePhoto(Activity activity) {
//        takePhoto(activity, tp, aspectX, aspectY);
//    }
//
//    public static void takePhoto(Activity activity, int tp, int aspectX, int aspectY) {
////        Intent intent = CropImage.activity(CropImage.getCaptureImageOutputUri(activity))
//        Intent intent = CropImage.activity()
//                .setAspectRatio(aspectX, aspectY)
//                .getIntent(activity);
//        intent.putExtra("tp", tp);
//        activity.startActivityForResult(intent, REQUEST_CODE_TAKEPHOTO);
//    }
//
//    public static TakePhotoResult getActivityResult(Activity activity, Intent data) {
//        CropImage.ActivityResult result = CropImage.getActivityResult(data);
//        Uri resultUri = result.getUri();
//
//
////        new Compressor(activity)
////                .setMaxWidth(maxWidth)
////                .setMaxHeight(maxHeight)
////                .compressToFile(FileProvider.);
//
//        TakePhotoResult takePhotoResult = new TakePhotoResult();
//        takePhotoResult.setResultUri(resultUri);
//
//        int tp = data.getIntExtra("tp",TakePhotoUtils.tp);
//        takePhotoResult.setTp(tp);
//
//        return takePhotoResult;
//    }
//
//
//}
