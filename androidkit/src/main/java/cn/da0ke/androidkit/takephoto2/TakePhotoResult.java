package cn.da0ke.androidkit.takephoto2;

import android.net.Uri;


/**
 * Created by da0ke on 2018/11/7
 */
public class TakePhotoResult {

    private int tp;
    private Uri resultUri;

    public int getTp() {
        return tp;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public Uri getResultUri() {
        return resultUri;
    }

    public void setResultUri(Uri resultUri) {
        this.resultUri = resultUri;
    }
}
