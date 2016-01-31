package com.qianfeng.gameassistant.module4.ui;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.module4.bean.PrizeDetail;
import com.qianfeng.gameassistant.module4.util.ExchangeHttpUtil;
import com.qianfeng.gameassistant.other.ui.BaseActivity;
import com.qianfeng.gameassistant.other.utils.ImageLoader;
import com.qianfeng.gameassistant.other.utils.ZhuShouContants;
import com.qianfeng.gameassistant.other.utils.ZhuShouTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Li Yiwei
 *
 * @date :  2016/1/17.
 */
public class PrizeDetailActivity extends BaseActivity
{
    private ImageView ivIcon;
    private TextView tvName, tvRemain;
    private Button btnConsume;
    private ImageLoader imageLoader;

    @Override
    protected int getLayout()
    {
        return R.layout.prize_detail;
    }

    @Override
    protected void initView()
    {
        setTitleText(R.string.prize_detail);
        showLeftImage();

        ivIcon = (ImageView) findViewById(R.id.prize_detail_icon_iv);
        tvName = (TextView) findViewById(R.id.prize_detail_name_tv);
        tvRemain = (TextView) findViewById(R.id.prize_detail_remain_tv);
        btnConsume = (Button) findViewById(R.id.prize_detail_consume_btn);
    }

    @Override
    protected void initEvents()
    {

    }

    @Override
    protected void initData()
    {
        String id = getIntent().getStringExtra("id");
        ExchangeHttpUtil.requestPrizeDetail(id, new ZhuShouTask.RequestCallback()
        {
            @Override
            public void success(Object result)
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    String state = jsonObject.getString(ZhuShouContants.STATE);
                    if (ZhuShouContants.SUCCESS.equals(state))
                    {
                        JSONObject info = jsonObject.getJSONObject(ZhuShouContants.INFO);
                        PrizeDetail prizeDetail = PrizeDetail.objectFromData(info.toString());

                        imageLoader = new ImageLoader(PrizeDetailActivity.this);
                        imageLoader.DisplayImage(prizeDetail.getIcon(), ivIcon);

                        tvName.setText(prizeDetail.getName());

                        String remain = getResources().getString(R.string.remain_count, prizeDetail.getRemain());
                        tvRemain.setText(remain);

                        String consume = getResources().getString(R.string.exchange_at_now, prizeDetail.getConsume());
                        btnConsume.setText(consume);

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
