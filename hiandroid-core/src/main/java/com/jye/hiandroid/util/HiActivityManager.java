package com.jye.hiandroid.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Activity管理
 *
 * @author jye
 * @since 1.0
 */
public class HiActivityManager {

    private static HiActivityManager sInstance = new HiActivityManager();

    //维护Activity 的list
    private List<Activity> mActivityList = new ArrayList<>();

    public static HiActivityManager getInstance() {
        return sInstance;
    }

    private HiActivityManager() {
    }

    /**
     * 添加一个activity到管理队列
     *
     * @param activity Activity
     */
    public void pushActivity(Activity activity) {
        mActivityList.add(activity);
    }

    /**
     * 删除一个activity在管理队列
     *
     * @param activity Activity
     */
    public void popActivity(Activity activity) {
        if (activity == null || !mActivityList.contains(activity)) {
            return;
        }
        mActivityList.remove(activity);
    }

    /**
     * 获取当前Activity（栈中最后一个压入的）
     */
    public Activity getCurrentActivity() {
        if (mActivityList.isEmpty()) {
            return null;
        }
        return mActivityList.get(mActivityList.size() - 1);
    }

    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public void finishCurrentActivity() {
        if (mActivityList.isEmpty()) {
            return;
        }
        Activity activity = mActivityList.get(mActivityList.size() - 1);
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity == null || !mActivityList.contains(activity)) {
            return;
        }

        mActivityList.remove(activity);
        activity.finish();
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (mActivityList.isEmpty()) {
            return;
        }

        for (Activity activity : mActivityList) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束（除了指定的Activity之外的）所有Activity
     *
     * @param classes 指定的Activity数组
     */
    public void finishAllActivityExcept(Class<?>... classes) {
        if (mActivityList.isEmpty()) {
            return;
        }

        Iterator<Activity> activityIterator = mActivityList.iterator();
        while (activityIterator.hasNext()) {
            Activity activity = activityIterator.next();
            boolean hasExcept = false;
            for (Class<?> exceptCls : classes) {
                if (activity.getClass() == exceptCls) {
                    hasExcept = true;
                    break;
                }
            }
            if (!hasExcept) {
                activity.finish();
                activityIterator.remove();
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (mActivityList.isEmpty()) {
            return;
        }

        for (Activity activity : mActivityList) {
            activity.finish();
        }
        mActivityList.clear();
    }

    /**
     * @return 获取Activity列表
     */
    public List<Activity> getActivityList() {
        return mActivityList;
    }
}
