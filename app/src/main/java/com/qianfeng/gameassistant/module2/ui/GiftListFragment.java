package com.qianfeng.gameassistant.module2.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.module2.adapter.GiftListAdapter;
import com.qianfeng.gameassistant.module2.bean.Gift;
import com.qianfeng.gameassistant.module2.util.GiftHttpUtil;
import com.qianfeng.gameassistant.other.ui.BaseFragment;
import com.qianfeng.gameassistant.other.utils.ZhuShouContants;
import com.qianfeng.gameassistant.other.utils.ZhuShouTask;
import com.qianfeng.gameassistant.other.widget.LoadMoreListView;
import com.qianfeng.gameassistant.other.widget.LoadMoreListView.LoadMoreListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 礼包页面的ListView显示
 * <p>
 * Created by Li Yiwei
 *
 * @date : 2016/1/12.
 */
public class GiftListFragment extends BaseFragment
{

    private LoadMoreListView loadMoreListView;
    private int type;

    private List<Gift> giftList = new ArrayList<Gift>();
    private float lastY;
    private int page = 1;

    private GiftListAdapter adapter;

    public GiftListFragment(int type)
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
        loadMoreListView.setLoadMoreListener(new LoadMoreListener()
        {
            @Override
            public void onLoadMore()
            {
                // TODO Auto-generated method stub
                page++;
                loadList();

            }
        });

        loadMoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Gift gift = giftList.get(position);
                String gameId = gift.getId();

                Intent intent = new Intent(getActivity(), GiftInfoActivity.class);
                intent.putExtra("id", gameId);
                getActivity().startActivity(intent);

            }
        });

    }

    @Override
    protected void initData()
    {

        adapter = new GiftListAdapter(getActivity(), giftList);
        loadMoreListView.setAdapter(adapter);
        loadList();

    }

    private void loadList()
    {
        GiftHttpUtil.requestGiftList(type, page, new ZhuShouTask.RequestCallback()
        {
            @Override
            public void success(Object result)
            {
                try
                {
                    if (result == null)
                    {
                        return;
                    }
                    JSONObject json = new JSONObject(result.toString());
                    String state = json.getString(ZhuShouContants.STATE);
                    if (ZhuShouContants.SUCCESS.equals(state))
                    {
                        List<Gift> gifts = Gift.arrayGiftFromData(json.toString(), ZhuShouContants.INFO);
                        giftList.addAll(gifts);
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
