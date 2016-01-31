package com.qianfeng.gameassistant.module1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.other.utils.ImageLoader;
import com.qianfeng.gameassistant.module1.bean.Profit;

import java.util.List;

/**
 * Created by Li Yiwei
 *
 * @date : 2016/1/12.
 */
public class ProfitListAdapter extends BaseAdapter
{
    private List<Profit> list;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;
    private Context context;

    public ProfitListAdapter(Context context, List<Profit> list)
    {
        this.list = list;
        this.context = context;
        imageLoader = new ImageLoader(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ProfitItem item = null;
        if (convertView == null)
        {
            item = new ProfitItem();

            convertView = inflater.inflate(R.layout.adapter_profit_list_item, null);

            item.ivIcon = (ImageView) convertView.findViewById(R.id.adapter_profit_list_item_icon_iv);

            item.tvName = (TextView) convertView.findViewById(R.id.adapter_profit_list_item_name_tv);
            item.tvScore = (TextView) convertView.findViewById(R.id.adapter_profit_list_item_score_tv);
            item.tvDown = (TextView) convertView.findViewById(R.id.adapter_profit_list_item_count_dl_tv);
            item.tvSize = (TextView) convertView.findViewById(R.id.adapter_profit_list_item_size_tv);
            item.tvPrize = (TextView) convertView.findViewById(R.id.adapter_profit_list_item_all_prize_tv);

            convertView.setTag(item);
        } else
        {
            item = (ProfitItem) convertView.getTag();
        }
        Profit profit = list.get(position);
        /**
         * 设置ListView各个元素的ivIcon,tvName, tvScore, tvDown, tvSize, tvPrize
         */
        imageLoader.DisplayImage(profit.getIcon(), item.ivIcon);

        item.tvName.setText(profit.getName());

        item.tvScore.setText(profit.getScore());

        item.tvDown.setText(profit.getCount_dl());

        item.tvSize.setText(profit.getSize());

        item.tvPrize.setText(profit.getAll_prize() + "");

        return convertView;
    }

    class ProfitItem
    {
        ImageView ivIcon;
        TextView tvName, tvScore, tvDown, tvSize, tvPrize;
    }
}
