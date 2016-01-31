package com.qianfeng.gameassistant.module2.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 礼包页面下面的ViewPager适配器
 *
 * Created by Li Yiwei
 *
 * @date :  2016/1/12.
 */
public class GiftPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public GiftPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        fragments = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
