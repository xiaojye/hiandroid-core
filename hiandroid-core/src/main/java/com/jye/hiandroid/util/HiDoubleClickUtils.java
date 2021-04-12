package com.jye.hiandroid.util;

import android.os.SystemClock;

/**
 * 双击判断工具类
 *
 * @author jye
 * @since 1.0
 */
public final class HiDoubleClickUtils {

    private static final long[] ONCLICK_TIME = new long[2]; // 数组的长度为2代表只记录双击操作
    private static final int INTERVAL_TIME_DEFAULT = 1000; // 默认限定间隔时长

    private HiDoubleClickUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 是否在短时间内进行了双击操作
     */
    public static boolean isDoubleClick() {
        return isDoubleClick(INTERVAL_TIME_DEFAULT);
    }

    /**
     * 是否在短时间内进行了双击操作
     *
     * @param intervalTime 双击间隔时间
     */
    public static boolean isDoubleClick(int intervalTime) {
        System.arraycopy(ONCLICK_TIME, 1, ONCLICK_TIME, 0, ONCLICK_TIME.length - 1);
        ONCLICK_TIME[ONCLICK_TIME.length - 1] = SystemClock.uptimeMillis();
        if (ONCLICK_TIME[0] >= (SystemClock.uptimeMillis() - intervalTime)) {
            return true;
        } else {
            return false;
        }
    }

}