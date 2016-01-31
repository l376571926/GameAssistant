package com.qianfeng.gameassistant.other.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.other.utils.FileUtil;
import com.qianfeng.gameassistant.other.utils.JumpManager;
import com.qianfeng.gameassistant.other.utils.LogUtil;
import com.qianfeng.gameassistant.other.utils.OtherHttpUtil;
import com.qianfeng.gameassistant.other.utils.ZhuShouContants;
import com.qianfeng.gameassistant.other.utils.ZhuShouTask;
import com.qianfeng.gameassistant.other.utils.ZhuShouTask.RequestCallback;
import com.qianfeng.gameassistant.other.utils.ZhuShouTask.UpgradeListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * 主页面
 */
public class MainActivity extends Activity
{
    private ImageView ivIcon, ivLable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        try
        {
            // 获取包信息
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

            if (packageInfo != null)
            {
                final String versionName = packageInfo.versionName;

                OtherHttpUtil.requestVersion(versionName, new ZhuShouTask.RequestCallback()
                {
                    @Override
                    public void success(Object result)
                    {
                        try
                        {
                            JSONObject jsonObject = new JSONObject(result.toString());
                            LogUtil.e("tag", "版本请求结果 result =" + result.toString());

                            String state = jsonObject.getString(ZhuShouContants.STATE);

                            if (ZhuShouContants.SUCCESS.equals(state))
                            {
                                /**
                                 * 向服务器请求客户端版本号,当版本号不相同时,服务器返回包含"info"字符串的json数据
                                 * ,内容为新版本客户端的更新详情
                                 */
                                JSONObject info = jsonObject.getJSONObject("info");

                                String msg = info.getString("msg");

                                msg = Html.fromHtml(msg).toString();

                                String apkUrl = info.getString("src");

                                Dialog dialog = getDialog(msg, apkUrl);

                                dialog.show();
                            }
                        } catch (JSONException e)
                        {
                            /**
                             * 向服务器请求客户端版本号,当版本号相同时,服务器返回json数据中不包含"info"字符串,
                             * 所以解析异常,判断是否是第一次使用应用,,那么跳转到引导页,
                             * 如果不是直接跳转到HomeActivity
                             */
                            if (isFirstUsed())
                            {
                                Intent intent = new Intent(MainActivity.this, GuideActivity.class);
                                startActivity(intent);
                                finish();
                            } else
                            {
                                showAnim();
                            }
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void error(String msg)
                    {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    @NonNull
    private Dialog getDialog(String msg, final String apkUrl)
    {
        // 如果请求成功了,那么用Dialog显示新版本信息
        final Dialog dialog = new Dialog(MainActivity.this, R.style.upgrade_dialog_style);

        dialog.setContentView(R.layout.dialog_upgrade_layout);

        TextView tvMsp = (TextView) dialog.findViewById(R.id.upgrade_msg_tv);
        tvMsp.setText(msg);

        Button btnCancel = (Button) dialog.findViewById(R.id.upgrade_cancel_btn);
        final Button btnUpgrade = (Button) dialog.findViewById(R.id.upgrade_upgrade_btn);
        final ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.upgrade_pb);

        progressBar.setVisibility(View.VISIBLE);

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LogUtil.e("tag", "" + v.toString());

                /**
                 * 点击版本更新提醒对话框中的"取消"按钮后,直接退出应用
                 */
                finish();
            }
        });
        btnUpgrade.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 如果开始下载,那么先显示进度条
                progressBar.setVisibility(View.VISIBLE);

                // 点击更新按钮后,使其不可再次点击
                btnUpgrade.setEnabled(false);

                // 执行下载apk操作
                OtherHttpUtil.downLoadApk(apkUrl, new RequestCallback()
                {

                    @Override
                    public void success(Object result)
                    {

                        /**
                         * 点击版本更新提醒对话框中的"更新"按钮, 下载完成后让用户选择是否安装新版本客户端
                         */
                        finish();

                        // 下载更新版本的Apk
                        File apk = (File) result;

                        FileUtil.installApk(MainActivity.this, apk);
                    }

                    @Override
                    public void error(String msg)
                    {
                        Toast.makeText(MainActivity.this, "无法下载apk,请检查下载地址是否可用!!!", Toast.LENGTH_SHORT).show();
                    }
                }, new UpgradeListener()
                {
                    @Override
                    public void showProgress(int progress)
                    {
                        progressBar.setProgress(progress);
                    }
                });

            }
        });
        return dialog;
    }

    private void showAnim()
    {
        setContentView(R.layout.activity_main);

        ivIcon = (ImageView) findViewById(R.id.main_icon_iv);
        ivLable = (ImageView) findViewById(R.id.main_lable_iv);

        // 初始化label的icon的动画
        Animation laberAnim = AnimationUtils.loadAnimation(this, R.anim.anim_main_lable_in);
        final Animation iconAnim = AnimationUtils.loadAnimation(this, R.anim.anim_main_icon_in);

        // 分别设置动画的监听
        laberAnim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                // 在label动画结束后再执行icon的动画
                ivIcon.startAnimation(iconAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
        });

        iconAnim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                // 在icon动画结束后显示icon
                ivIcon.setVisibility(View.VISIBLE);

                // 跳转到主页面
                JumpManager.jumpToHome(MainActivity.this);
                // 当前页面已经不需要了
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
        ivLable.startAnimation(laberAnim);
    }

    private boolean isFirstUsed()
    {
        SharedPreferences preferences = getSharedPreferences(ZhuShouContants.FIRST_USED,
                Context.MODE_PRIVATE);
        return preferences.getBoolean(ZhuShouContants.USED_FLAG, true);
    }
}
