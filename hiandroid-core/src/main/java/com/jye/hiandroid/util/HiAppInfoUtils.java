package com.jye.hiandroid.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.List;
import java.util.Objects;

/**
 * APP相关信息工具类
 *
 * @author jye
 * @since 1.0
 */
public final class HiAppInfoUtils {

    /**
     * 获取 PackageInfo
     *
     * @return PackageInfo
     */
    public static PackageInfo getPackageInfo(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getPackageInfo(getPackageName(context), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取 PackageInfo
     *
     * @param packageManager
     * @return PackageInfo
     */
    public static PackageInfo getPackageInfo(Context context, PackageManager packageManager) {
        try {
            return packageManager.getPackageInfo(getPackageName(context), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取 App 名称
     *
     * @return App 名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            return getPackageInfo(context, pm).applicationInfo.loadLabel(pm).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取 App 图标
     *
     * @return App 名称
     */
    public static Drawable getAppIcon(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            return getPackageInfo(context, pm).applicationInfo.loadIcon(pm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取 APP 版本号
     *
     * @return APP 版本号
     */
    public static int getVersionCode(Context context) {
        try {
            return getPackageInfo(context).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取 APP 版本名称
     *
     * @return APP 版本名称
     */
    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager()
                    .getPackageInfo(getPackageName(context), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取 APP 包名
     *
     * @return APP 包名
     */
    public static String getPackageName(Context context) {
        try {
            return context.getPackageName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 当前是否为Debug模式
     */
    public static boolean isDebug(Context context) {
        try {
            return context.getApplicationInfo() != null
                    && (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断 App 是否处于前台
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isAppForeground(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> info = manager.getRunningAppProcesses();
        if (info == null || info.size() == 0) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            if (aInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return aInfo.processName.equals(getPackageName(context));
            }
        }
        return false;
    }
}

