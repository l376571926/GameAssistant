package com.qianfeng.gameassistant.module1.ui;

import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.other.ui.BaseActivity;

/**
 * Created by Li Yiwei
 *
 * @date :  2016/1/17.
 */
public class GameDetailActivity extends BaseActivity
{

    @Override
    protected int getLayout()
    {
        return R.layout.activity_game_detail;
    }

    @Override
    protected void initView()
    {
        setTitleText(R.string.game_detail);
        showLeftImage();
    }

    @Override
    protected void initEvents()
    {

    }

    @Override
    protected void initData()
    {

    }
}
