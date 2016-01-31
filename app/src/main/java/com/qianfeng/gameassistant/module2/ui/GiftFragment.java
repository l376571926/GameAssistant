package com.qianfeng.gameassistant.module2.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.module2.adapter.GiftPagerAdapter;
import com.qianfeng.gameassistant.other.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 礼包页面 Created by Li Yiwei
 *
 * @date : 2016/1/12.
 */
public class GiftFragment extends BaseFragment
{

    private ViewPager vpContent;
    private GiftPagerAdapter pagerAdapter;

    private TextView tvMobile, tvWeb;

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int id = v.getId();
            int index = 0;
            if (id == R.id.gift_type_mobile_tv)
            {
                index = 0;
            } else if (id == R.id.gift_type_web_tv)
            {
                index = 1;
            }

            vpContent.setCurrentItem(index);
            boolean isCheckedMobile = index == 0 ? true : false;
            tvMobile.setSelected(isCheckedMobile);
            tvWeb.setSelected(!isCheckedMobile);
        }
    };

    private ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener()
    {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
        {

        }

        @Override
        public void onPageSelected(int position)
        {
            vpContent.setCurrentItem(position);
            boolean isCheckedMobile = position == 0 ? true : false;
            tvMobile.setSelected(isCheckedMobile);
            tvWeb.setSelected(!isCheckedMobile);

        }

        @Override
        public void onPageScrollStateChanged(int state)
        {

        }
    };

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_gift;
    }

    @Override
    protected void initView()
    {
        vpContent = (ViewPager) root.findViewById(R.id.gift_type_vp);
        tvMobile = (TextView) root.findViewById(R.id.gift_type_mobile_tv);
        tvWeb = (TextView) root.findViewById(R.id.gift_type_web_tv);
        tvMobile.setSelected(true);
    }

    @Override
    protected void initEvents()
    {
        tvMobile.setOnClickListener(onClickListener);
        tvWeb.setOnClickListener(onClickListener);
        vpContent.addOnPageChangeListener(changeListener);
    }

    @Override
    protected void initData()
    {

        GiftListFragment mobileListFragment = new GiftListFragment(1);
        GiftListFragment webListFragment = new GiftListFragment(2);

        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(mobileListFragment);
        fragmentList.add(webListFragment);

        pagerAdapter = new GiftPagerAdapter(getFragmentManager(), fragmentList);
        vpContent.setAdapter(pagerAdapter);
    }
}
