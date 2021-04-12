package com.jye.hiandroid.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;

import androidx.annotation.StringDef;
import androidx.core.content.FileProvider;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Intent相关工具类
 *
 * @author jye
 * @since 1.0
 */
public final class HiIntentUtils {

    private HiIntentUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取安装 App（支持 8.0）的意图
     * <p>8.0 需添加权限
     * {@code <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />}</p>
     *
     * @param filePath  文件路径
     * @param authority 7.0 及以上安装需要传入清单文件中的{@code <provider>}的 authorities 属性
     *                  <br>参看 https://developer.android.com/reference/android/support/v4/content/FileProvider.html
     * @return 安装 App（支持 8.0）的意图
     */
    public static Intent getInstallAppIntent(Context context, final String filePath, final String authority) {
        return getInstallAppIntent(context, new File(filePath), authority);
    }

    /**
     * 获取安装 App(支持 8.0)的意图
     * <p>8.0 需添加权限
     * {@code <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />}</p>
     *
     * @param file      文件
     * @param authority 7.0 及以上安装需要传入清单文件中的{@code <provider>}的 authorities 属性
     *                  <br>参看 https://developer.android.com/reference/android/support/v4/content/FileProvider.html
     * @return 安装 App(支持 8.0)的意图
     */
    public static Intent getInstallAppIntent(Context context, final File file, final String authority) {
        if (file == null) {
            return null;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data;
        String type = "application/vnd.android.package-archive";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            data = Uri.fromFile(file);
        } else {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            data = FileProvider.getUriForFile(context, authority, file);
        }
        intent.setDataAndType(data, type);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * 获取卸载 App 的意图
     *
     * @param packageName 包名
     * @return 卸载 App 的意图
     */
    public static Intent getUninstallAppIntent(final String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * 获取打开 App 的意图
     *
     * @param packageName 包名
     * @return 打开 App 的意图
     */
    public static Intent getLaunchAppIntent(Context context,final String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        return intent;
    }

    /**
     * 调用浏览器打开网页的Intent
     * <p>
     * param url - 网址：例如，http://www.baidu.com
     * return
     */
    public static Intent getBrowserIntent(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * 获取 App 具体设置的意图
     *
     * @param packageName 包名
     * @return App 具体设置的意图
     */
    public static Intent getAppSettingsIntent(final String packageName) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + packageName));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * 跳转到拨号面板的Intent
     * <p>
     *
     * @param phoneNumber 电话号码
     * @return 对应的Intent对象
     */
    public static Intent getDialIntent(final String phoneNumber) {
        Uri uri = Uri.parse("tel:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * 直接拨打电话的Intent
     * <p>
     *
     * @param phoneNumber 电话号码
     * @return 对应的Intent对象
     */
    public static Intent getCallIntent(final String phoneNumber) {
        Uri uri = Uri.parse("tel:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * 打开短信程序，发送短信的Intent
     * <p>
     *
     * @param phoneNumber 电话号码
     * @param smsBody     短信内容文本
     * @return 对应的Intent对象
     */
    public static Intent getSMSIntent(final String phoneNumber, final String smsBody) {
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", smsBody);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * 分享-文本的Intent
     *
     * @param title   标题
     * @param content 内容
     * @return 对应的Intent对象
     */
    public static Intent getShareTextIntent(final String title, final String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent = Intent.createChooser(intent, title);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return Intent.createChooser(intent, "选择分享应用");
    }

    /**
     * 获取分享图片的意图
     *
     * @param content   文本
     * @param imagePath 图片文件路径
     * @return 分享图片的意图
     */
    public static Intent getShareImageIntent(final String content, final String imagePath) {
        return getShareImageIntent(content, new File(imagePath));
    }

    /**
     * 获取分享图片的意图
     *
     * @param content 文本
     * @param image   图片文件
     * @return 分享图片的意图
     */
    public static Intent getShareImageIntent(final String content, final File image) {
        return getShareImageIntent(content, Uri.fromFile(image));
    }

    /**
     * 获取分享图片的意图
     *
     * @param content 分享文本
     * @param uri     图片 uri
     * @return 分享图片的意图
     */
    public static Intent getShareImageIntent(final String content, final Uri uri) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * 获取拍照的意图
     *
     * @param outUri 输出的 uri
     * @return 拍照的意图
     */
    public static Intent getCaptureIntent(final Uri outUri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }


    /**
     * 获取选择照片的 Intent
     *
     * @return
     */
    public static Intent getPickIntentWithGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * 获取文件选择的 Intent
     *
     * @param fileType 文件类型
     * @return 文件选择的 Intent
     */
    public static Intent getFilePickerIntent(@FileType String fileType) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        if (!TextUtils.isEmpty(fileType)) {
            intent.setType(fileType);
        } else {
            intent.setType(FileType.ANY);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }


    /**
     * 选择文件的类型
     */
    @StringDef({FileType.IMAGE, FileType.AUDIO, FileType.VIDEO, FileType.ANY})
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.SOURCE)
    public @interface FileType {
        String IMAGE = "image/*";
        String AUDIO = "audio/*";
        String VIDEO = "video/*";
        String ANY = "*/*";
    }
}