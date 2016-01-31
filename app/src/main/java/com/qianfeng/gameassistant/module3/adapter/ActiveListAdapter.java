package com.qianfeng.gameassistant.module3.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.module3.bean.Active;
import com.qianfeng.gameassistant.other.utils.ImageLoader;

import java.util.List;

/**
 * Created by Li Yiwei
 *
 * @date :  2016/1/12.
 */
public class ActiveListAdapter extends BaseAdapter
{
    private List<Active> list;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;
    private Context context;

    public ActiveListAdapter(Context context, List<Active> list)
    {
        this.list = list;
        this.context = context;
        imageLoader = new ImageLoader(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        ActiveItem item = null;
        if (convertView == null)
        {
            item = new ActiveItem();

            convertView = inflater.inflate(R.layout.adapter_active_list_item, null);

            item.ivIcon = (ImageView) convertView.findViewById(R.id.adapter_active_icon_iv);

            item.tvTitle = (TextView) convertView.findViewById(R.id.adapter_active_title_tv);
            item.tvContent = (TextView) convertView.findViewById(R.id.adapter_active_content_tv);
            item.tvCount = (TextView) convertView.findViewById(R.id.adapter_active_count_tv);
            item.tvStatus = (TextView) convertView.findViewById(R.id.adapter_active_status_tv);

            convertView.setTag(item);

        } else
        {
            item = (ActiveItem) convertView.getTag();
        }
        Active active = list.get(position);
        imageLoader.DisplayImage(active.getHotpic(),item.ivIcon);
        item.tvTitle.setText(active.getAname());
        item.tvContent.setText(active.getShortname());
        item.tvCount.setText(active.getTotal_join_user() + "");
        item.tvStatus.setText(active.getStatus());



        return convertView;
    }

    class ActiveItem
    {
        ImageView ivIcon;
        TextView tvTitle, tvContent, tvCount, tvStatus;

    }
}
