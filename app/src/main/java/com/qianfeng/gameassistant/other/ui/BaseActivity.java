package com.qianfeng.gameassistant.other.ui;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qianfeng.gameassistant.R;


/**
 * Created by Li Yiwei
 *
 * @date :  2016/1/13.
 */
public abstract class BaseActivity extends Activity
{

    private ImageView ivBack;
    private ImageView ivShare;
    private TextView tvTitle;
    private TextView tvShare;
    protected LinearLayout llContent;
    private RelativeLayout rlShare;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);

        ivBack = (ImageView) findViewById(R.id.title_bar_left_iv);
        ivShare = (ImageView) findViewById(R.id.title_bar_right_iv);
        tvTitle = (TextView) findViewById(R.id.title_bar_title_tv);
        tvShare = (TextView) findViewById(R.id.title_bar_share_tv);
        llContent = (LinearLayout) findViewById(R.id.base_content_ll);
        rlShare = (RelativeLayout) findViewById(R.id.gift_info_title_bar_rl);

        setDefaultEvent();

        getLayoutInflater().inflate(getLayout(), llContent);

        initView();

        initEvents();

        initData();
    }

    /**
     * 获取布局
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化事件
     */
    protected abstract void initEvents();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    protected void setTitleText(String title)
    {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
    }

    protected void setTitleText(int StringId)
    {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(StringId);
    }

    protected void setRightText(String text)
    {
        tvShare.setVisibility(View.VISIBLE);
        tvShare.setText(text);
    }

    protected void setRightText(int StringId)
    {
        tvShare.setVisibility(View.VISIBLE);
        tvShare.setText(StringId);
    }

    protected void setRightImage(int selectorId)
    {
        ivShare.setVisibility(View.VISIBLE);
        ivShare.setImageResource(selectorId);
    }

    /**
     * 监听
     */
    protected void setDefaultEvent()
    {
        ivBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

    }

    public void setShareOnClickListener(View.OnClickListener onClickListener)
    {
        rlShare.setOnClickListener(onClickListener);
    }
    protected void showLeftImage()
    {
        ivBack.setVisibility(View.VISIBLE);
    }
}
