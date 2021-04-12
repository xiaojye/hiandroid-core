package com.jye.hiandroid.util;

import android.content.Context;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jye
 * @since 1.0
 */
public class HiReflectUtils {


    public static Set<Field> getClassSet(Class<?> tClass) {
        return HiReflectUtils.getClassSet(tClass, false, null);
    }

    public static Set<Field> getAllClassSet(Class<?> tClass) {
        return HiReflectUtils.getClassSet(tClass, true, null);
    }

    public static Set<Field> getClassSet(Class<?> tClass, boolean superClass, Set<Field> fieldSet) {
        if (fieldSet == null) {
            fieldSet = new HashSet<>();
        }
        fieldSet.addAll(Arrays.asList(tClass.getFields()));
        fieldSet.addAll(Arrays.asList(tClass.getDeclaredFields()));

        if (superClass) {
            Class supperClass = tClass.getSuperclass();
            if (!Object.class.equals(supperClass)) {
                return getClassSet(supperClass, superClass, fieldSet);
            }
        }
        return fieldSet;
    }

    public static String getType(String name) {
        return name.substring(0, name.indexOf("."));
    }

    public static int getResourceId(Context paramContext, String paramString, String type) {
        return paramContext.getResources().getIdentifier(paramString, type, paramContext.getPackageName());
    }

    public static void setData(Field field, Object src, Object o) {
        boolean temp = field.isAccessible();
        field.setAccessible(true);
        try {
            field.set(src, o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        field.setAccessible(temp);
    }

    public static String getName(String name) {
        return name.substring(name.indexOf(".") + 1);
    }
}
