package cn.da0ke.androidkit;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;

import java.lang.ref.WeakReference;

/**
 * 通过弱引用的方式，规避内存泄漏
 * @param <Params>
 * @param <Progress>
 * @param <Result>
 * @param <WeakTarget>
 */
public abstract class WeakAsyncTask<Params, Progress, Result, WeakTarget> extends AsyncTask<Params, Progress, Result> {

    private WeakReference<WeakTarget> mTarget;

    public WeakAsyncTask(WeakTarget target) {
        mTarget = new WeakReference<>(target);
    }

    @Override
    protected Result doInBackground(Params... params) {
        final WeakTarget target = mTarget.get();
        if(target != null) {
            return backgroundTask(target, params);
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Result result) {
        final WeakTarget target = mTarget.get();
        if(target != null) {

            if(target instanceof Activity) {
                Activity mActivity = (Activity) target;
                if(!mActivity.isFinishing()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        if(!mActivity.isDestroyed()) {
                            uiTask(target, result);
                        }
                    } else {
                        uiTask(target, result);
                    }
                }
            }
        }
    }

    protected Result backgroundTask(WeakTarget target, Params... params) {
        return null;
    }

    protected void uiTask(WeakTarget target, Result result) {

    }

}
