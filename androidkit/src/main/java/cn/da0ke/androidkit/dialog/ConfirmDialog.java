package cn.da0ke.androidkit.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.da0ke.androidkit.R;

/**
 * Created by da0ke on 2018/11/20
 */
public class ConfirmDialog extends Dialog {

    private Button _positive;
    private Button _negative;
    private TextView _title;
    private TextView _message;

    private String title;
    private String message;
    private String positive;
    private String negative;

    private OnClickPositiveListener onClickPositiveListener;
    private OnClickNegativeListener onClickNegativeListener;

    public ConfirmDialog(@NonNull Context context) {
        super(context);
    }

    public ConfirmDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ConfirmDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.androidkit_dialog_confirm);

        // 点空白处消失
        setCanceledOnTouchOutside(true);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        _positive = findViewById(R.id.positive);
        _negative = findViewById(R.id.negative);
        _title = findViewById(R.id.title);
        _message = findViewById(R.id.message);
    }

    private void initData() {
        if(title != null && !title.isEmpty()) {
            _title.setText(title);
        } else {
            _title.setVisibility(View.GONE);
        }
        if(message != null && !message.isEmpty()) {
            _message.setText(message);
        }
        if(positive != null && !positive.isEmpty()) {
            _positive.setText(positive);
        }
        if(negative != null && !negative.isEmpty()) {
            _negative.setText(negative);
        }
    }

    private void initEvent() {
        _positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if(onClickPositiveListener != null) {
                    onClickPositiveListener.onClick();
                }
            }
        });

        _negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if(onClickNegativeListener != null) {
                    onClickNegativeListener.onClick();
                }
            }
        });

    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public void setNegative(String negative) {
        this.negative = negative;
    }

    public void setOnClickPositiveListener(OnClickPositiveListener onClickPositiveListener) {
        this.onClickPositiveListener = onClickPositiveListener;
    }

    public void setOnClickNegativeListener(OnClickNegativeListener onClickNegativeListener) {
        this.onClickNegativeListener = onClickNegativeListener;
    }

    public static class Builder {

        private Context context;
        private String title;
        private String message;
        private String positive;
        private String negative;
        private OnClickPositiveListener onClickPositiveListener;
        private OnClickNegativeListener onClickNegativeListener;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder positive(String positive) {
            this.positive = positive;
            return this;
        }

        public Builder negative(String negative) {
            this.negative = negative;
            return this;
        }

        public Builder onClickPositiveListener(OnClickPositiveListener onClickPositiveListener) {
            this.onClickPositiveListener = onClickPositiveListener;
            return this;
        }

        public Builder onClickNegativeListener(OnClickNegativeListener onClickNegativeListener) {
            this.onClickNegativeListener = onClickNegativeListener;
            return this;
        }

        private ConfirmDialog create() {
            final ConfirmDialog dialog = new ConfirmDialog(context);

            dialog.setTitle(title);
            dialog.setMessage(message);
            dialog.setPositive(positive);
            dialog.setNegative(negative);

            if(onClickPositiveListener != null) {
                dialog.setOnClickPositiveListener(onClickPositiveListener);
            }

            if(onClickNegativeListener != null) {
                dialog.setOnClickNegativeListener(onClickNegativeListener);
            }

            return dialog;
        }

        public ConfirmDialog show() {
            final ConfirmDialog dialog = create();
            dialog.show();
            return dialog;
        }

    }

    public interface OnClickPositiveListener {
        void onClick();
    }

    public interface OnClickNegativeListener {
        void onClick();
    }

}
