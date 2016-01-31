package com.qianfeng.gameassistant.module4.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 兑换页面下面的ViewPager适配器
 * <p/>
 * Created by Li Yiwei
 *
 * @date :  2016/1/12.
 */
public class ExchangePagerAdapter extends FragmentPagerAdapter
{

    private List<Fragment> fragments;

    public ExchangePagerAdapter(FragmentManager fm, List<Fragment> fragments)
    {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }
}
