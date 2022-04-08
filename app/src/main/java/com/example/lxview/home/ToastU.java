package com.example.lxview.home;

import android.content.Context;
import android.widget.Toast;

/**
 * author: 李 祥
 * date:   2022/3/30 6:31 下午
 * description:
 */
public class ToastU {

    public static Toast toast;

    public static void showToast(Context context, String msg) {
        //在外部定义一个Toast 类型的变量
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
