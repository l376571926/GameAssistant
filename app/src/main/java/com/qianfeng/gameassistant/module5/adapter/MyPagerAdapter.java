package com.qianfeng.gameassistant.module5.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Li Yiwei
 *
 * @date :  2016/1/16.
 */
public class MyPagerAdapter extends PagerAdapter
{
    private List list;

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        return super.instantiateItem(container, position);
    }
}
