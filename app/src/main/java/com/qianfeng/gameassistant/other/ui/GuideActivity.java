package com.qianfeng.gameassistant.other.ui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.other.adapter.GuideAdapter;
import com.qianfeng.gameassistant.other.utils.JumpManager;
import com.qianfeng.gameassistant.other.utils.ZhuShouContants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyiwei on 2016/1/11.
 */
public class GuideActivity extends Activity
{
    private ViewPager viewPager;
    private GuideAdapter adapter;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewPager = (ViewPager) findViewById(R.id.guide_content_vp);
        button = (Button) findViewById(R.id.guide_jump_btn);

        List<ImageView> list = new ArrayList<ImageView>();
        final int[] bitmaps = new int[]
                {R.drawable.bg_guide_01, //
                        R.drawable.bg_guide_02, //
                        R.drawable.bg_guide_03, //
                        R.drawable.bg_guide_04};

        for (int i = 0; i < bitmaps.length; i++)
        {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(bitmaps[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            list.add(imageView);
        }

        adapter = new GuideAdapter(list);
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                // 如果显示到最后一页,让Button显示出来
                if (position == (bitmaps.length - 1))
                {
                    button.setVisibility(View.VISIBLE);
                } else
                {
                    button.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });

        // 点击进入主页面,并设置进入的标识
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 获取指定的SharedPreferences
                SharedPreferences preferences = getSharedPreferences(ZhuShouContants.FIRST_USED,
                        Context.MODE_PRIVATE);

                // 获取编辑器
                SharedPreferences.Editor editor = preferences.edit();
                // 添加一个字段表示已经不是第一次使用了
                editor.putBoolean(ZhuShouContants.USED_FLAG, false);
                // 添加完字段后,要提交操作
                editor.commit();

                // 跳转到主页面
                JumpManager.jumpToHome(GuideActivity.this);
                // 当前页面已经不需要了
                finish();
            }
        });
    }
}
