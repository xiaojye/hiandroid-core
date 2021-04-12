package com.jye.hiandroid.util;

import android.content.Context;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import dalvik.system.DexFile;

/**
 * @author jye
 * @since 1.0
 */
public class HiContextUtils {

    /**
     * 根据包名获取Classes
     *
     * @param context      安卓上下文
     * @param packageNames 包名数组
     * @return
     * @throws IOException 输入输出异常
     */
    public static Set<String> getDexFileClasses(Context context, String[] packageNames) throws IOException {
        DexFile df = new DexFile(context.getPackageCodePath());//通过DexFile查找当前的APK中可执行文件
        Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
        Set<String> classes = new HashSet<>();
        while (enumeration.hasMoreElements()) {//遍历
            String className = (String) enumeration.nextElement();
            for (String packageName : packageNames) {
                if (className.startsWith(packageName)) {
                    classes.add(className);
                }
            }
        }
        return classes;
    }
}