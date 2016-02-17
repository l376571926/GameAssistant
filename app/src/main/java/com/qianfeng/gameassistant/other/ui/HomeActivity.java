package com.qianfeng.gameassistant.other.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioGroup;

import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.module1.ui.ProfitFragment;
import com.qianfeng.gameassistant.module2.ui.GiftFragment;
import com.qianfeng.gameassistant.module3.ui.ActiveFragment;
import com.qianfeng.gameassistant.module4.ui.ExchangeFragment;
import com.qianfeng.gameassistant.module5.ui.MyFragment;

/**
 * 主页面
 * <p/>
 * Created by Li Yiwei
 *
 * @date : 2016/1/11.
 */
public class HomeActivity extends FragmentActivity
{

    private Fragment[] fragments;
    // 上一个被点中的
    private View checkedButton;
    private int checkedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.home_rg);
        radioGroup.setOnCheckedChangeListener(changeListener);

        // 从RadioGroup中获取第一个子控件RadioButton
        View firstChild = radioGroup.getChildAt(0);

        fragments = new Fragment[]
                {new ProfitFragment(), //
                        new GiftFragment(), //
                        new ActiveFragment(), //
                        new ExchangeFragment(), //
                        new MyFragment()};

        // 获取fragment管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // 将所有的fragment添加到fragment事务管理器中
        for (int i = 0; i < fragments.length; i++)
        {
            Fragment fragment = fragments[i];
            transaction.add(R.id.home_content_fl, fragment);
            transaction.hide(fragment);
        }
        // 指定默认显示的页面
        transaction.show(fragments[0]);
        transaction.commit();

        //默认选中第一个
        checkedButton = firstChild;
        // 让第一个RadioButton执行一下点击事件
        firstChild.performClick();

    }

    private RadioGroup.OnCheckedChangeListener changeListener = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId)
        {
            View radioButton = findViewById(checkedId);
            // 放大当前选中的RadioButton
            zoomBig(radioButton);

            if (radioButton == checkedButton)
            {
                return;
            }
            // 缩小上一次选中的RadioButton
            zoomSmalll(checkedButton);

            // 保存当前选中的RadioButton
            checkedButton = radioButton;

            int index = 0;
            switch (checkedId)
            {
                case R.id.home_profit_rb:
                    index = 0;
                    break;
                case R.id.home_gift_rb:
                    index = 1;
                    break;
                case R.id.home_avtive_rb:
                    index = 2;
                    break;
                case R.id.home_exchange_rb:
                    index = 3;
                    break;
                case R.id.home_my_rb:
                    index = 4;
                    break;
                default:
                    index = 0;
                    break;
            }
            // 获取fragment管理器
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.show(fragments[index]);
            transaction.hide(fragments[checkedIndex]);
            transaction.commit();
            checkedIndex = index;
        }
    };

    /**
     * 执行放大动画
     *
     * @param view 要执行动画的view
     */
    private void zoomBig(View view)
    {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_rb_zoom_big);
        view.startAnimation(animation);

    }

    /**
     * 执行缩小动画
     *
     * @param view 要执行动画的view
     */
    private void zoomSmalll(View view)
    {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_rb_zoom_small);
        view.startAnimation(animation);
    }
}