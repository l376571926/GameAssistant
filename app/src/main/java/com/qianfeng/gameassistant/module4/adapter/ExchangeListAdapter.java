package com.qianfeng.gameassistant.module4.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.module4.bean.Exchange;
import com.qianfeng.gameassistant.other.ui.LoginActivity;
import com.qianfeng.gameassistant.other.utils.ImageLoader;

import java.util.List;

/**
 * Created by Li Yiwei
 *
 * @date :  2016/1/12.
 */
public class ExchangeListAdapter extends BaseAdapter
{
    private Context context;
    private List<Exchange> exchangeList;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    public ExchangeListAdapter(Context context, List<Exchange> exchangeList)
    {
        this.exchangeList = exchangeList;
        this.context = context;
        imageLoader = new ImageLoader(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return exchangeList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return exchangeList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        ExchangeItem item = null;
        if (convertView == null)
        {
            item = new ExchangeItem();

            convertView = inflater.inflate(R.layout.adapter_exchange_list_item, null);

            item.ivIcon = (ImageView) convertView.findViewById(R.id.adapter_exchange_icon_iv);

            item.tvName = (TextView) convertView.findViewById(R.id.adapter_exchange_name_tv);
            item.tvConsume = (TextView) convertView.findViewById(R.id.adapter_exchange_consume_tv);
            item.tvRemain = (TextView) convertView.findViewById(R.id.adapter_exchange_remain_tv);

            item.btnExchange = (Button) convertView.findViewById(R.id.adapter_exchange_btn);
            item.btnExchange.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
            });

            convertView.setTag(item);

        } else
        {
            item = (ExchangeItem) convertView.getTag();
        }
        Exchange exchange = exchangeList.get(position);

        imageLoader.DisplayImage(exchange.getIcon(),item.ivIcon);

        item.tvName.setText(exchange.getName());
        item.tvConsume.setText(exchange.getConsume());
        item.tvRemain.setText(exchange.getRemain());


        return convertView;
    }

    class ExchangeItem
    {
        ImageView ivIcon;
        TextView tvName, tvConsume, tvRemain;
        Button btnExchange;

    }
}
