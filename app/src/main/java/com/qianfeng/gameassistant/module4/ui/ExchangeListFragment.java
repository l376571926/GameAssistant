package com.qianfeng.gameassistant.module4.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.module4.adapter.ExchangeListAdapter;
import com.qianfeng.gameassistant.module4.bean.Exchange;
import com.qianfeng.gameassistant.module4.util.ExchangeHttpUtil;
import com.qianfeng.gameassistant.other.ui.BaseFragment;
import com.qianfeng.gameassistant.other.utils.LogUtil;
import com.qianfeng.gameassistant.other.utils.ZhuShouContants;
import com.qianfeng.gameassistant.other.utils.ZhuShouTask;
import com.qianfeng.gameassistant.other.widget.LoadMoreListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 兑换页面的ListView显示
 * Created by Li Yiwei
 *
 * @date :  2016/1/12.
 */
public class ExchangeListFragment extends BaseFragment
{

    private LoadMoreListView loadMoreListView;
    private List<Exchange> exchangeList = new ArrayList<>();
    private ExchangeListAdapter adapter;
    /**
     * 最新奖品为1,最热奖品为2
     */
    private int type;

    public ExchangeListFragment(int type)
    {
        this.type = type;
    }

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_type_list;
    }

    @Override
    protected void initView()
    {
        loadMoreListView = (LoadMoreListView) root;

    }

    @Override
    protected void initEvents()
    {
        loadMoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Exchange exchange = exchangeList.get(position);
                String prizeId = exchange.getId();

                Intent intent = new Intent(getActivity(), PrizeDetailActivity.class);
                intent.putExtra("id", prizeId);
                getActivity().startActivity(intent);
            }
        });

    }

    @Override
    protected void initData()
    {
        adapter = new ExchangeListAdapter(getActivity(), exchangeList);
        loadMoreListView.setAdapter(adapter);

        ExchangeHttpUtil.requestExchangeList(type, new ZhuShouTask.RequestCallback()
        {
            @Override
            public void success(Object result)
            {
                LogUtil.e("tag", "兑换模块json数据下载成功 result = " + result.toString());
                try
                {
                    if (result == null)
                    {
                        //执行到此处,停止程序
                        return;
                    }
                    JSONObject json = new JSONObject(result.toString());
                    String state = json.getString(ZhuShouContants.STATE);
                    if (ZhuShouContants.SUCCESS.equals(state))
                    {
                        List<Exchange> exchanges = Exchange.arrayExchangeFromData(json.toString(), ZhuShouContants.INFO);
                        exchangeList.addAll(exchanges);
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg)
            {

            }
        });
    }
}
