package com.qianfeng.gameassistant.module3.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * 活动页面的ViewPager适配器
 * <p/>
 * Created by Li Yiwei
 *
 * @date :  2016/1/12.
 */
public class ActivePagerAdapter extends PagerAdapter
{
    private Context context;
    private List<ImageView> imageViewList;

    public ActivePagerAdapter(Context context, List<ImageView> imageViewList)
    {
        this.context = context;
        this.imageViewList = imageViewList;
    }

    /**
     * 该方法将返回所包含的 Item总个数。为了实现一种循环滚动的效果，返回了基本整型的最大值，这样就会创建很多的Item,
     * 其实这并非是真正的无限循环。
     */
    @Override
    public int getCount()
    {
        return imageViewList.size();
    }

    /**
     * 判断出去的view是否等于进来的view 如果为true直接复用
     */
    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    /**
     * 向ViewPager中添加一个View
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        ImageView imageView = imageViewList.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        super.destroyItem(container, position, object);
        container.removeView(imageViewList.get(position));
    }

}
