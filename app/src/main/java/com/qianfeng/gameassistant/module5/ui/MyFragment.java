package com.qianfeng.gameassistant.module5.ui;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.other.ui.BaseFragment;
import com.qianfeng.gameassistant.other.ui.LoginActivity;

/**
 * 我的页面
 * Created by Li Yiwei
 *
 * @date :  2016/1/12.
 */
public class MyFragment extends BaseFragment
{
    private RelativeLayout rlGift;

    private RelativeLayout rlActivity, rlExchange, rlPlay, rlTask, rlLetter, rlInvite, rlGame, rlSetup;

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.fragment_my_gift_rl:
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                    break;
                case R.id.fragment_my_activity_rl:
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                    break;
                case R.id.fragment_my_exchange_rl:
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                    break;
                case R.id.fragment_my_play_rl:
                    break;
                case R.id.fragment_my_task_rl:
                    break;
                case R.id.fragment_my_letter_rl:
                    break;
                case R.id.fragment_my_invite_rl:
                    break;
                case R.id.fragment_my_game_rl:
                    break;
                case R.id.fragment_my_setup_rl:

                    break;
                default:

                    break;
            }
        }
    };

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView()
    {

        //横向4个个子功能模块
        rlGift = (RelativeLayout) root.findViewById(R.id.fragment_my_gift_rl);
        rlActivity = (RelativeLayout) root.findViewById(R.id.fragment_my_activity_rl);
        rlExchange = (RelativeLayout) root.findViewById(R.id.fragment_my_exchange_rl);
        rlPlay = (RelativeLayout) root.findViewById(R.id.fragment_my_play_rl);

        //列表纵向5个子功能模块
        rlTask = (RelativeLayout) root.findViewById(R.id.fragment_my_task_rl);
        rlLetter = (RelativeLayout) root.findViewById(R.id.fragment_my_letter_rl);
        rlInvite = (RelativeLayout) root.findViewById(R.id.fragment_my_invite_rl);
        rlGame = (RelativeLayout) root.findViewById(R.id.fragment_my_game_rl);
        rlSetup = (RelativeLayout) root.findViewById(R.id.fragment_my_setup_rl);

    }

    @Override
    protected void initEvents()
    {   //横向4个个子功能模块,设置监听器
        rlGift.setOnClickListener(onClickListener);
        rlActivity.setOnClickListener(onClickListener);
        rlExchange.setOnClickListener(onClickListener);
        rlPlay.setOnClickListener(onClickListener);

        //列表纵向5个子功能模块,设置监听器
        rlTask.setOnClickListener(onClickListener);
        rlLetter.setOnClickListener(onClickListener);
        rlInvite.setOnClickListener(onClickListener);
        rlGame.setOnClickListener(onClickListener);
        rlSetup.setOnClickListener(onClickListener);

    }

    @Override
    protected void initData()
    {

    }
}
