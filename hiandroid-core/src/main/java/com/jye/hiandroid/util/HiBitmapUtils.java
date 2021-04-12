package com.jye.hiandroid.util;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ScrollView;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Bitmap相关工具类
 *
 * @author jye
 * @since 1.0
 */
public class HiBitmapUtils {

    private HiBitmapUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 图片转换成base64字符串（改名为bitmapToBase64String）
     *
     * @param bitmap Bitmap
     * @return base64字符串
     */
    @Deprecated
    public static String bitmapToString(Bitmap bitmap) {
        return bitmapToBase64String(bitmap);
    }

    /**
     * Base64字符串转换成图片（改名为base64StringToBitmap）
     *
     * @param base64Str base64字符串
     */
    @Deprecated
    public static Bitmap stringToBitmap(String base64Str) {
        return base64StringToBitmap(base64Str);
    }

    /**
     * 图片转换成base64字符串
     *
     * @param bitmap Bitmap
     * @return base64字符串
     */
    public static String bitmapToBase64String(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, out);
        byte[] imgBytes = out.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }

    /**
     * Base64字符串转换成图片
     *
     * @param base64Str base64字符串
     * @return Bitmap
     */
    public static Bitmap base64StringToBitmap(String base64Str) {
        try {
            StringBuffer sb = new StringBuffer();
            Pattern pattern = Pattern.compile("data:image/.*;base64,");
            Matcher matcher = pattern.matcher(base64Str);
            while (matcher.find()) {
                String tmp = matcher.group();
                System.out.println(tmp);
                matcher.appendReplacement(sb, "");
            }
            matcher.appendTail(sb);

            byte[] bitmapArray = Base64.decode(sb.toString(), Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * View生成图片
     *
     * @param view View
     * @return 由传入的View生成的Bitmap
     */
    public static Bitmap getBitmapByView(View view) {
        if (view instanceof ScrollView) {
            return getBitmapByView((ScrollView) view);
        }
        Bitmap bitmap = null;
        try {
            int viewWidth = view.getWidth();
            int viewHeight = view.getHeight();
            if (viewWidth != 0 && viewHeight != 0) {
                bitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                view.layout(0, 0, viewWidth, viewHeight);
                view.draw(canvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
            bitmap = null;
        }
        return bitmap;
    }

    /**
     * ScrollView生成图片
     *
     * @param scrollView View
     * @return 由传入的View生成的Bitmap
     */
    public static Bitmap getBitmapByView(ScrollView scrollView) {
        int scrollViewHeight = 0;
        Bitmap bitmap = null;
        try {
            for (int i = 0; i < scrollView.getChildCount(); i++) {
                scrollViewHeight += scrollView.getChildAt(i).getHeight();
                scrollView.getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
            }
            if (scrollView.getWidth() != 0 && scrollViewHeight != 0) {
                bitmap = Bitmap.createBitmap(scrollView.getWidth(), scrollViewHeight, Bitmap.Config.RGB_565);
                final Canvas canvas = new Canvas(bitmap);
                scrollView.draw(canvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
            bitmap = null;
        }
        return bitmap;
    }

    /**
     * 保存图片到Pictures公共图片目录
     *
     * @param context  上下文对象
     * @param bitmap   需要保存的图片bitmap
     * @param fileName 保存图片文件名
     * @return 保存成功返回true，反之false
     */
    public static boolean saveImageToPictures(Context context, Bitmap bitmap, String fileName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, fileName);
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        OutputStream outputStream;
        try {
            outputStream = context.getContentResolver().openOutputStream(uri);
            bitmap.compress(CompressFormat.JPEG, 100, outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        //更新图库
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(HiUriUtils.getFileFromUri(context, uri)));
        context.sendBroadcast(intent);

        return true;
    }

    /**
     * 保存图片到Pictures公共图片目录(默认文件名为yyyyMMddHHmmssSSS.jpg)
     *
     * @param context 上下文对象
     * @param bitmap  需要保存的图片bitmap
     * @return 保存成功返回true，反之false
     */
    public static boolean saveImageToPictures(Context context, Bitmap bitmap) {
        return saveImageToPictures(context, bitmap, HiTimeUtils.timestampToString(System.currentTimeMillis(), "yyyyMMddHHmmssSSS") + ".jpg");
    }
}