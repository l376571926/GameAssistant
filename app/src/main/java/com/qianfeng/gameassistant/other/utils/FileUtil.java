package com.qianfeng.gameassistant.other.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

/**
 * 文件式具类
 * <p/>
 * Created by Liu Jianping
 *
 * @date : 15/12/10.
 */
public class FileUtil {

    /**
     * 项目缓存目录
     */
    public static final File CACHE_DIR = getCache();

    /**
     * apk存储目录
     */
    public static final File APK_DIR = getApkDir();

    /**
     * 项目文件目录
     */
    public static final File PROJECT_DIR = getAppDir();


    /**
     * 获取SD卡文件目录
     *
     * @return
     */
    public static final File getSDcardDir() {
        //获取挂载状态
        String state = Environment.getExternalStorageState();

        //如果是已挂载,说明了有内存卡
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return Environment.getExternalStorageDirectory();
        }

        throw new NullPointerException("没有sd卡哦...");
    }

    /**
     * 获取本项目文件目录
     *
     * @return
     */
    private static final File getAppDir() {
        return new File(getSDcardDir(), "72Game");
    }

    /**
     * 获取项目缓存文件目录
     *
     * @return
     */
    private static final File getCache() {
        return new File(getAppDir(), "cache");
    }

    /**
     * 获取apk存储目录
     *
     * @return
     */
    private static final File getApkDir() {
        return new File(getAppDir(), "apk");
    }

    /**
     * 安装apk
     *
     * @param context
     * @param file
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        String type = "application/vnd.android.package-archive";
        intent.setDataAndType(Uri.fromFile(file), type);
        context.startActivity(intent);
    }

}
