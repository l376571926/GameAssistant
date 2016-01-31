package com.qianfeng.gameassistant.module3.ui;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.module3.adapter.ActiveListAdapter;
import com.qianfeng.gameassistant.module3.adapter.ActivePagerAdapter;
import com.qianfeng.gameassistant.module3.bean.Active;
import com.qianfeng.gameassistant.module3.bean.Banner;
import com.qianfeng.gameassistant.module3.util.ActiveHttpUtil;
import com.qianfeng.gameassistant.other.ui.BaseFragment;
import com.qianfeng.gameassistant.other.utils.ImageLoader;
import com.qianfeng.gameassistant.other.utils.LogUtil;
import com.qianfeng.gameassistant.other.utils.ZhuShouContants;
import com.qianfeng.gameassistant.other.utils.ZhuShouTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动页面
 * Created by Li Yiwei
 *
 * @date :  2016/1/12.
 */
public class ActiveFragment extends BaseFragment
{
    private ViewPager viewPager;
    private ActiveListAdapter activeListAdapter;
    private ListView listView;
    private List<Active> activeList = new ArrayList<>();
    private List<ImageView> imageViewList = new ArrayList<>();
    private TextView tvBname;

    private ActivePagerAdapter pagerAdapter;
    private ImageLoader imageLoader = new ImageLoader(getActivity());

    private List<Banner> bannerList;

    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener()
    {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
        {

        }

        @Override
        public void onPageSelected(int position)
        {
            LogUtil.e("tag","ViewPage选中的页面的索引是 = " + position);
            tvBname.setText(bannerList.get(position).getBname());
        }

        @Override
        public void onPageScrollStateChanged(int state)
        {

        }
    };

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_active;
    }

    @Override
    protected void initView()
    {
        tvBname = (TextView) root.findViewById(R.id.fragment_active_banner_name);
        viewPager = (ViewPager) root.findViewById(R.id.fragment_active_banner_info_vp);
        viewPager.addOnPageChangeListener(listener);
        listView = (ListView) root.findViewById(R.id.fragment_active_content_lv);

    }

    @Override
    protected void initEvents()
    {

    }

    @Override
    protected void initData()
    {

        activeListAdapter = new ActiveListAdapter(getActivity(), activeList);
        listView.setAdapter(activeListAdapter);
        loadList();

        pagerAdapter = new ActivePagerAdapter(getActivity(), imageViewList);
        viewPager.setAdapter(pagerAdapter);
        loadImageViewUrl();
        /**
         * 根据json数据中的广告url地址显示广告图片
         */
    }

    private void loadList()
    {
        ActiveHttpUtil.requestActiveList(new ZhuShouTask.RequestCallback()
        {
            @Override
            public void success(Object result)
            {
                LogUtil.e("tag", "请求活动模块活动列表成功 result = " + result.toString());
                if (result == null)
                {
                    return;
                }
                JSONObject json = null;

                try
                {
                    json = new JSONObject(result.toString());
                    String state = json.getString(ZhuShouContants.STATE);
                    if (ZhuShouContants.SUCCESS.equals(state))
                    {
                        List<Active> info = Active.arrayActiveFromData(json.toString(), "info");
                        activeList.addAll(info);
                        LogUtil.e("tag", "activeList.size = " + activeList.size());
                        activeListAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg)
            {
                LogUtil.e("tag", "请求活动模块活动列表失败!");
                throw new NullPointerException("请求活动模块活动列表失败!");
            }
        });
    }

    /**
     * 1,获取广告栏json数据
     * <p>
     * 2,从json数据中获取广告所有图片的url
     * <p>
     * 3,设置url到imageview中
     * <p>
     * 4,添加所有imageview到imageviewlist中
     */
    private void loadImageViewUrl()
    {
        ActiveHttpUtil.requestBannerList(new ZhuShouTask.RequestCallback()
        {
            @Override
            public void success(Object result)
            {
                LogUtil.e("tag", "请求广告列表成功 result = " + result.toString());
                if (result == null)
                {
                    return;
                }
                JSONObject json = null;

                try
                {
                    json = new JSONObject(result.toString());
                    String state = json.getString(ZhuShouContants.STATE);
                    bannerList = new ArrayList<>();
                    if (ZhuShouContants.SUCCESS.equals(state))
                    {
                        List<Banner> banners = Banner.arrayBannerFromData(json.toString(), ZhuShouContants.INFO);
                        bannerList.addAll(banners);
                        tvBname.setText(bannerList.get(0).getBname());
                    }

                    for (Banner banner : bannerList)
                    {
                        banner.getBimg();
                        ImageView iv = new ImageView(getActivity());
                        imageLoader.DisplayImage(banner.getBimg(), iv);
                        imageViewList.add(iv);
                    }
                    pagerAdapter.notifyDataSetChanged();

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg)
            {
                LogUtil.e("tag", "请求广告列表失败!!!" + msg);
            }
        });

    }
}
