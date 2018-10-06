package cn.da0ke.androidkit.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.view.ContextThemeWrapper;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import cn.da0ke.androidkit.activity.TakePhotoActivity;

/**
 * Created by da0ke on 2018/10/6
 */
public class TakePhotoUtils {

    public static final int REQUEST_CODE_TAKEPHOTO = 2001;

    public static void takePhoto(final Activity activity) {
        takePhoto(activity, 0, 0 ,0);
    }

    public static void takePhoto(final Activity activity, final int tp) {
        takePhoto(activity, tp, 0 ,0);
    }

    public static void takePhoto(final Activity activity, final int aspectX, final int aspectY) {
        takePhoto(activity, 0, aspectX, aspectY);
    }

    public static void takePhoto(final Activity activity, final int tp, final int aspectX, final int aspectY) {
        final Context dialogContext = new ContextThemeWrapper(activity,
                android.R.style.Theme_Light);
        String[] choices = new String[2];
        choices[0] = "拍照";
        choices[1] = "从相册中选择";

        final ListAdapter adapter = new ArrayAdapter<>(dialogContext,
                android.R.layout.simple_list_item_1,choices);
        final AlertDialog.Builder builder = new AlertDialog.Builder(
                dialogContext);
        builder.setSingleChoiceItems(adapter, -1,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        switch (which) {
                            case 0: {
                                String status = Environment
                                        .getExternalStorageState();
                                if (status.equals(Environment.MEDIA_MOUNTED)) {
                                    //拍照
                                    Intent intent = new Intent(activity, TakePhotoActivity.class);
                                    intent.putExtra("type",0);
                                    intent.putExtra("tp",tp);
                                    intent.putExtra("aspectX", aspectX);
                                    intent.putExtra("aspectY", aspectY);
                                    activity.startActivityForResult(intent,REQUEST_CODE_TAKEPHOTO);
                                } else {
                                    AndroidUtils.showMsg("没有SD卡");
                                }
                                break;
                            }
                            case 1:
                                //从相册中选择
                                Intent intent = new Intent(activity, TakePhotoActivity.class);
                                intent.putExtra("type",1);
                                intent.putExtra("tp",tp);
                                intent.putExtra("aspectX", aspectX);
                                intent.putExtra("aspectY", aspectY);
                                activity.startActivityForResult(intent,REQUEST_CODE_TAKEPHOTO);
                                break;
                        }
                    }
                });
        builder.create().show();
    }

}
