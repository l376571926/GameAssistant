package com.qianfeng.gameassistant.other.widget;


import com.qianfeng.gameassistant.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

/**
 * 分享菜单
 * Created by Li Yiwei
 *
 * @date :  2016/1/14.
 */
public class SharePopupWindow extends PopupWindow
{

    private Button btn;
    public SharePopupWindow(Context context)
    {
        super(context);

        //设置Pop布局
        View content = LayoutInflater.from(context).inflate(R.layout.share_to_layout, null);

        setContentView(content);

        //设置Pop的宽高,如里不设置则不显示
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        //设置进出动画
        setAnimationStyle(R.style.share_pop_anim);

        //点击在pop以外的区域会消失
        setFocusable(true);

        btn = (Button) content.findViewById(R.id.pop_widow_cancel_btn);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });



    }
}
