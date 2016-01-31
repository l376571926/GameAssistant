package com.qianfeng.gameassistant.other.utils;


import android.util.Log;

/**
 * 日志管理类
 *
 * Created by Liu Jianping
 *
 * @date : 15/12/7.
 */
public class LogUtil {

    /**
     * 是否是调度状态
     */
    private static boolean isDebug = true;

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }
}
