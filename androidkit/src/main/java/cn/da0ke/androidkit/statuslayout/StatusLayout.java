package cn.da0ke.androidkit.statuslayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.da0ke.androidkit.R;

/**
 * Created by da0ke on 2018/10/6
 */
public class StatusLayout extends LinearLayout {

    //空数据页面
    private ViewGroup mEmptyView;
    //加载中页面
    private ViewGroup mLoadingView;
    //加载失败页面
    private ViewGroup mErrorView;
    //加载中遮罩
    private ViewGroup mProgressView;

    public StatusLayout(Context context) {
        super(context);
        init();
    }

    public StatusLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StatusLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StatusLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(mInflater != null) {
            mEmptyView = (ViewGroup) mInflater.inflate(R.layout.androidkit_statuslayout_empty,this,false);
            mLoadingView = (ViewGroup) mInflater.inflate(R.layout.androidkit_statuslayout_loading,this,false);
            mErrorView = (ViewGroup) mInflater.inflate(R.layout.androidkit_statuslayout_error,this,false);
            mProgressView = (ViewGroup) mInflater.inflate(R.layout.androidkit_statuslayout_progress,this,false);

            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mEmptyView.setLayoutParams(layoutParams);
            mLoadingView.setLayoutParams(layoutParams);
            mErrorView.setLayoutParams(layoutParams);
            mProgressView.setLayoutParams(layoutParams);

            this.addView(mLoadingView);
            this.addView(mEmptyView);
            this.addView(mErrorView);
            this.addView(mProgressView);

            //阻止下层视图事件穿透
            this.setClickable(true);
            this.setFocusable(true);

            showLoading();
        }
    }

    public void showEmpty(String msg) {
        TextView _msg = mEmptyView.findViewById(R.id.statuslayout_empty_msg);

        if(!TextUtils.isEmpty(msg)) {
            _msg.setText(msg);
        } else {
            _msg.setText(getResources().getString(R.string.androidkit_statuslayout_empty_tip));
        }
        this.setVisibility(VISIBLE);
        mEmptyView.setVisibility(VISIBLE);
        mLoadingView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        mProgressView.setVisibility(GONE);
    }

    public void showEmpty() {
        showEmpty("");
    }

    public void showLoading() {
        this.setVisibility(VISIBLE);
        mEmptyView.setVisibility(GONE);
        mLoadingView.setVisibility(VISIBLE);
        mErrorView.setVisibility(GONE);
        mProgressView.setVisibility(GONE);
    }

    public void showError() {
        this.setVisibility(VISIBLE);
        mEmptyView.setVisibility(GONE);
        mLoadingView.setVisibility(GONE);
        mErrorView.setVisibility(VISIBLE);
        mProgressView.setVisibility(GONE);
    }

    public void showProgress() {
        this.setVisibility(VISIBLE);
        mEmptyView.setVisibility(GONE);
        mLoadingView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        mProgressView.setVisibility(VISIBLE);
    }

    public void hide() {
        this.setVisibility(GONE);
    }

    public void setOnErrorClickListener(final OnErrorClickListener callBack) {
        mErrorView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onErrorClick();
            }
        });
    }

    public interface OnErrorClickListener {
        void onErrorClick();
    }
}
