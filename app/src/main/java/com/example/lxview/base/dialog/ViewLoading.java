package com.example.lxview.base.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.lxview.R;

import java.lang.ref.WeakReference;

/**
 * <pre>
 *     @author yangchong
 *     blog  : https://github.com/yangchong211
 *     time  : 2016/06/4
 *     desc  : 全局加载弹窗
 *     revise:
 * </pre>
 */
public class ViewLoading extends Dialog {
    private static WeakReference<ViewLoading> loadingDialog;
    private final boolean cancelAble;
    private TextView message;

    private ViewLoading(Context context, String content, boolean cancelAble) {
        super(context, R.style.Loading);
        this.cancelAble = cancelAble;
        if (content != null && content.length() > 0) {
            setContentView(R.layout.common_loading_text_dialog_layout);
            setCancelable(cancelAble);
            setCanceledOnTouchOutside(false);
            message = findViewById(R.id.message);
            message.setText(content);
        } else {
            setContentView(R.layout.common_loading_dialog_layout);
        }
        ImageView progressImageView = findViewById(R.id.iv_image);
        // 创建旋转动画
        Animation animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(600);
        // 动画的重复次数
        animation.setRepeatCount(Animation.INFINITE);
        // 设置为true，动画转化结束后被应用
        animation.setFillAfter(true);
        // 开始动画
        progressImageView.startAnimation(animation);
        // 设置Dialog参数
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.CENTER;
            window.setAttributes(params);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (!cancelAble && keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static boolean isDialogShowing() {
        ViewLoading ld = getVl();
        if (ld == null) return false;
        return ld.isShowing();
    }

    public static void showMessage(String message) {
        ViewLoading ld = getVl();
        if (ld == null) return;
        if (ld.isShowing()) {
            ld.message.setText(message);
        }
    }

    public static void show(Context context) {
        show(context, "");
    }

    public static void show(Context context, String message) {
        show(context, message, false);
    }

    /**
     * 展示加载窗
     *
     * @param context  上下文
     * @param message  内容
     * @param isCancel 是否可以取消
     */
    public static void show(Context context, String message, boolean isCancel) {
        if (context == null) return;
        if (context instanceof Activity && ((Activity) context).isFinishing()) return;
        ViewLoading ld = getVl();
        if (ld == null) {
            loadingDialog = new WeakReference<>(new ViewLoading(context, message, isCancel));
            show(context, message, isCancel);
            return;
        }
        if (isDialogShowing()) return;
        ld.show();
    }

    /**
     * 销毁加载窗
     *
     * @param context 上下文
     */
    public static void dismiss(Context context) {
        ViewLoading ld = getVl();
        if (ld == null) return;
        try {
            if (context == null) {
                ld.dismiss();
            }
            if (context instanceof Activity) {
                if (((Activity) context).isFinishing()) {
                    ld.dismiss();
                    return;
                }
            }
            if (isDialogShowing()) {
                Context loadContext = ld.getContext();
                if (loadContext instanceof Activity) {
                    if (((Activity) loadContext).isFinishing()) {
                        return;
                    }
                }
                ld.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (loadingDialog != null) loadingDialog.clear();
        }
    }

    private static ViewLoading getVl() {
        return loadingDialog == null ? null : loadingDialog.get();
    }
}
