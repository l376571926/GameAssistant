package com.qianfeng.gameassistant.other.utils;


import com.qianfeng.gameassistant.other.ui.HomeActivity;

import android.app.Activity;
import android.content.Intent;


/**
 * 跳转工具类
 * Created by Li Yiwei
 *
 * @date : 2016/1/11.
 */
public class JumpManager {
    /**
     * 跳转到主页面
     *
     * @param activity
     */
    public static void jumpToHome(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }
}
