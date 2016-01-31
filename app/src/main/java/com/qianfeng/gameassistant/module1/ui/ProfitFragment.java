package com.qianfeng.gameassistant.module1.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.other.ui.BaseFragment;
import com.qianfeng.gameassistant.other.ui.LoginActivity;
import com.qianfeng.gameassistant.other.utils.LogUtil;
import com.qianfeng.gameassistant.other.utils.ZhuShouContants;
import com.qianfeng.gameassistant.other.utils.ZhuShouTask;
import com.qianfeng.gameassistant.module1.adapter.ProfitListAdapter;
import com.qianfeng.gameassistant.module1.bean.Profit;
import com.qianfeng.gameassistant.module1.util.ProfitHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 赚钱页面
 * <p/>
 * Created by Li Yiwei
 *
 * @date : 2016/1/12.
 */
public class ProfitFragment extends BaseFragment
{
    private TextView tvSign, tvTask, tvMarket;
    private ProfitListAdapter adapter;
    private ListView listview;
    private List<Profit> profitList = new ArrayList<>();
    private View.OnClickListener listener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.profit_sign_get_score_tv:
                    Toast.makeText(getActivity(), "请先登录!", Toast.LENGTH_SHORT).show();
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                    break;

                case R.id.profit_hodiernal_task_tv:
                    /**
                     * 在Fragment中实例化意图对象的第一个参数应为getActivity()
                     *
                     * 在Fragment中启动Acitivity要通过getActivity().startActivity()这种方式来启动
                     */
                    Toast.makeText(getActivity(), "请先登录再查看今日任务", Toast.LENGTH_SHORT).show();
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                    break;

                case R.id.profit_score_market_tv:

                    RadioButton radioButton = (RadioButton) getActivity().findViewById(R.id.home_exchange_rb);
                    radioButton.performClick();

                    break;
            }
        }
    };

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            getActivity().startActivity(new Intent(getActivity(), GameDetailActivity.class));
        }
    };

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_profit;
    }

    @Override
    protected void initView()
    {
        tvSign = (TextView) root.findViewById(R.id.profit_sign_get_score_tv);
        tvTask = (TextView) root.findViewById(R.id.profit_hodiernal_task_tv);
        tvMarket = (TextView) root.findViewById(R.id.profit_score_market_tv);
        listview = (ListView) root.findViewById(R.id.profit_content_lv);

    }

    @Override
    protected void initEvents()
    {
        tvSign.setOnClickListener(listener);
        tvTask.setOnClickListener(listener);
        tvMarket.setOnClickListener(listener);
        listview.setOnItemClickListener(onItemClickListener);
    }

    @Override
    protected void initData()
    {
        adapter = new ProfitListAdapter(getActivity(), profitList);
        listview.setAdapter(adapter);

        loadGameList();

    }

    private void loadGameList()
    {
        ProfitHttpUtil.requestProfitList(new ZhuShouTask.RequestCallback()
        {
            @Override
            public void success(Object result)
            {
                LogUtil.e("tag", "请求赚钱页面游戏列表 result = " + result.toString());

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
                        List<Profit> info = Profit.arrayProfitFromData(json.toString(), "info");
                        profitList.addAll(info);
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
                LogUtil.e("tag", "请求赚钱游戏列表失败!");
                throw new NullPointerException("请求<赚钱>游戏列表失败!");
            }
        });
    }
}
