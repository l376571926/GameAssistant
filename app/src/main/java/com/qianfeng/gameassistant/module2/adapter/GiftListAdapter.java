package com.qianfeng.gameassistant.module2.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.module2.bean.Gift;
import com.qianfeng.gameassistant.other.utils.ImageLoader;

import java.util.List;

/**
 * Created by Li Yiwei
 *
 * @date : 2016/1/12.
 */
public class GiftListAdapter extends BaseAdapter
{

    private List<Gift> list;
    private LayoutInflater inflater;
    private Context context;
    private ImageLoader imageLoader;

    public GiftListAdapter(Context context, List<Gift> list)
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

        GiftItem item = null;
        if (convertView == null)
        {
            item = new GiftItem();

            convertView = inflater.inflate(R.layout.adapter_gift_list_item, null);

            item.ivHeader = (ImageView) convertView.findViewById(R.id.adapter_gift_header_iv);

            item.tvName = (TextView) convertView.findViewById(R.id.adapter_gift_name_tv);
            item.tvRemain = (TextView) convertView.findViewById(R.id.adapter_gift_remain_tv);
            item.tvContent = (TextView) convertView.findViewById(R.id.adapter_gift_content_tv);

            item.btn1 = (Button) convertView.findViewById(R.id.adapter_gift_btn1);
            item.btn2 = (Button) convertView.findViewById(R.id.adapter_gift_btn2);

            convertView.setTag(item);

        } else
        {

            item = (GiftItem) convertView.getTag();

        }
        Gift gift = list.get(position);

        imageLoader.DisplayImage(gift.getIcon(), item.ivHeader);

        item.tvName.setText(gift.getName());

        // 用占位符和值连接成一个字符串
        String remain = context.getString(R.string.gift_list_remain, gift.getRemain());
        // 创建一个可加工的字符串
        SpannableString spannableString = new SpannableString(remain);
        int color = context.getResources().getColor(R.color.list_item_yellow_btn_color);
        // 定义一种颜色span
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        // 你要加工字符串中的哪一段
        spannableString.setSpan(span, 3, remain.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 把加工好的字符串设置进去
        item.tvRemain.setText(spannableString);

        item.tvContent.setText(gift.getContent());
        // 如果剩余是0,显示淘号,否则显示免费领取
        if (gift.getRemain() == 0)
        {
            item.btn1.setVisibility(View.VISIBLE);
            item.btn2.setVisibility(View.GONE);
        } else
        {
            item.btn1.setVisibility(View.GONE);
            item.btn2.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    class GiftItem
    {
        ImageView ivHeader;
        TextView tvName, tvRemain, tvContent;
        Button btn1, btn2;
    }
}
