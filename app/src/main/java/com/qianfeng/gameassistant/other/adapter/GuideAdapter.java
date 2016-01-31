package com.qianfeng.gameassistant.other.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * 引导页的adapter
 * <p/>
 * Created by Li Yiwei
 *
 * @date : 2016/1/11.
 */
public class GuideAdapter extends PagerAdapter
{
    private List<ImageView> list;

    public GuideAdapter(List<ImageView> list)
    {
        this.list = list;
    }

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
    public Object instantiateItem(ViewGroup container, int position)
    {
        ImageView view = list.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView(list.get(position));
    }
}
