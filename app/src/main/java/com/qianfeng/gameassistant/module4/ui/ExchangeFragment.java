package com.qianfeng.gameassistant.module4.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.module4.adapter.ExchangePagerAdapter;
import com.qianfeng.gameassistant.other.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 兑换页面
 * Created by Li Yiwei
 *
 * @date :  2016/1/12.
 */
public class ExchangeFragment extends BaseFragment
{

    private ViewPager vpContent;
    private ExchangePagerAdapter adapter;

    private TextView tvNewest, tvHottest;

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int id = v.getId();
            int index = 0;
            if (id == R.id.exchange_fragment_newest_prize_tv)
            {
                index = 0;
            }
            if (id == R.id.exchange_fragment_hottest_prize_tv)
            {
                index = 1;
            }
            vpContent.setCurrentItem(index);
            boolean isChecked = index == 0 ? true : false;
            tvNewest.setSelected(isChecked);
            tvHottest.setSelected(!isChecked);
        }
    };
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener()
    {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
        {

        }

        @Override
        public void onPageSelected(int position)
        {
            vpContent.setCurrentItem(position);
            boolean isChecked = position == 0 ? true : false;
            tvNewest.setSelected(isChecked);
            tvHottest.setSelected(!isChecked);

        }

        @Override
        public void onPageScrollStateChanged(int state)
        {

        }
    };

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_exchange;
    }

    @Override
    protected void initView()
    {
        vpContent = (ViewPager) root.findViewById(R.id.exchange_type_vp);
        tvNewest = (TextView) root.findViewById(R.id.exchange_fragment_newest_prize_tv);
        tvHottest = (TextView) root.findViewById(R.id.exchange_fragment_hottest_prize_tv);
        tvNewest.setSelected(true);
    }

    @Override
    protected void initEvents()
    {
        tvNewest.setOnClickListener(onClickListener);
        tvHottest.setOnClickListener(onClickListener);
        vpContent.addOnPageChangeListener(onPageChangeListener);

    }

    @Override
    protected void initData()
    {
        ExchangeListFragment newestFragment = new ExchangeListFragment(1);
        ExchangeListFragment hottestFragment = new ExchangeListFragment(2);

        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(newestFragment);
        fragmentList.add(hottestFragment);

        adapter = new ExchangePagerAdapter(getFragmentManager(), fragmentList);
        vpContent.setAdapter(adapter);

    }
}
