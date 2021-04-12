package com.jye.hiandroid.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

/**
 * Toast统一管理类
 *
 * @author jye
 * @since 1.0
 */
public final class HiToastUtils {

    //全局通用Toast
    private static Toast sToast = null;

    //默认显示位置
    private static int sDefaultGravity = 0;
    private static int sDefaultXOffset = 0;
    private static int sDefaultYOffset = 0;

    /**
     * 设置默认的显示位置
     *
     * @param gravity 默认显示位置
     * @param xOffset x轴偏移量
     * @param yOffset y轴偏移量
     */
    public static void setDefaultGravity(int gravity, int xOffset, int yOffset) {
        sDefaultGravity = gravity;
        sDefaultXOffset = xOffset;
        sDefaultYOffset = yOffset;
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        show(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        show(context, message, Toast.LENGTH_LONG);
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration 单位:毫秒
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (context == null || TextUtils.isEmpty(message))
            return;
        if (sToast == null) {
            sToast = Toast.makeText(context, message, duration);
        } else {
            sToast.setText(message);
        }
        if (sDefaultGravity != 0) {
            sToast.setGravity(sDefaultGravity, sDefaultXOffset, sDefaultYOffset);
        }
        sToast.show();
    }

    /**
     * 自定义Toast的View
     *
     * @param context
     * @param message
     * @param duration 单位:毫秒
     * @param view     显示自己的View
     */
    public static void customToastView(Context context, CharSequence message, int duration, View view) {
        if (context == null || TextUtils.isEmpty(message))
            return;

        if (sToast == null) {
            sToast = Toast.makeText(context, message, duration);
        } else {
            sToast.setText(message);
        }
        if (view != null) {
            sToast.setView(view);
        }
        if (sDefaultGravity != 0) {
            sToast.setGravity(sDefaultGravity, sDefaultXOffset, sDefaultYOffset);
        }
        sToast.show();
    }

    /**
     * 自定义Toast的位置
     *
     * @param context
     * @param message
     * @param duration 单位:毫秒
     * @param gravity
     * @param xOffset
     * @param yOffset
     */
    public static void customToastGravity(Context context, CharSequence message, int duration, int gravity, int xOffset, int yOffset) {
        if (context == null || TextUtils.isEmpty(message))
            return;

        if (sToast == null) {
            sToast = Toast.makeText(context, message, duration);
        } else {
            sToast.setText(message);
        }
        sToast.setGravity(gravity, xOffset, yOffset);
        sToast.show();
    }

    /**
     * 取消Toast显示
     */
    public static void cancelToast() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }
}  