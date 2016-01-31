package com.qianfeng.gameassistant.module2.ui;

import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.qianfeng.gameassistant.R;
import com.qianfeng.gameassistant.module2.bean.GiftInfo;
import com.qianfeng.gameassistant.module2.util.GiftHttpUtil;
import com.qianfeng.gameassistant.other.ui.BaseActivity;
import com.qianfeng.gameassistant.other.utils.ZhuShouContants;
import com.qianfeng.gameassistant.other.utils.ZhuShouTask;
import com.qianfeng.gameassistant.other.widget.CircleImageView;
import com.qianfeng.gameassistant.other.widget.SharePopupWindow;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Li Yiwei
 *
 * @date : 2016/1/13.
 */
public class GiftInfoActivity extends BaseActivity
{
    @ViewInject(R.id.gift_info_icon_iv)
    private CircleImageView ivHeader;

    @ViewInject(R.id.gift_info_android_iv)
    private ImageView ivAndroid;

    @ViewInject(R.id.gift_info_ios_iv)
    private ImageView ivIos;

    @ViewInject(R.id.gift_info_name_tv)
    private TextView tvName;

    @ViewInject(R.id.gift_info_validity_tv)
    private TextView tvValidity;

    @ViewInject(R.id.gift_info_consume_tv)
    private TextView tvConsume;

    @ViewInject(R.id.gift_info_content_tv)
    private TextView tvContent;

    @ViewInject(R.id.gift_info_howget_tv)
    private TextView tvHowGet;

    @ViewInject(R.id.gift_info_game_type_tv)
    private TextView tvGameType;

    @ViewInject(R.id.gift_info_size_tv)
    private TextView tvSize;

    @ViewInject(R.id.gift_info_per_tv)
    private TextView tvPer;

    @ViewInject(R.id.gift_info_game_type_rl)
    private RelativeLayout giftTypeGroup;

    @ViewInject(R.id.gift_info_pb)
    private ProgressBar progressBar;

    @Override
    protected int getLayout()
    {
        return R.layout.activity_gift_info;
    }

    @Override
    protected void initView()
    {

        ViewUtils.inject(this);//用注解的方式替代findViewById必须在Activity初始化时调用此方法,否则报空指针
        setTitleText(R.string.gift_info_detail);
        showLeftImage();
        setRightImage(R.drawable.selector_share);

    }

    @Override
    protected void initEvents()
    {
        setShareOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 显示分享菜单

                SharePopupWindow popupWindow = new SharePopupWindow(GiftInfoActivity.this);
                // popupWindow.showAsDropDown(ivHeader);
                popupWindow.showAtLocation(llContent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
    }

    @Override
    protected void initData()
    {
        String id = getIntent().getStringExtra("id");
        GiftHttpUtil.requestGiftInfo(id, new ZhuShouTask.RequestCallback()
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
                        GiftInfo giftInfo = GiftInfo.objectFromData(info.toString());

                        LogUtils.e("giftInfo" + giftInfo);

                        String url = giftInfo.getIcon();
                        // 用HttpUrlConnection下载图片并显示
                        ivHeader.setImageUrl(url);

                        tvName.setText(giftInfo.getName());

                        String consume = getResources().getString(R.string.gift_info_consume, giftInfo.getConsume());
                        tvConsume.setText(consume);

                        // 设置剩余百分比
                        String per = getString(R.string.gift_info_per, giftInfo.getRemain(), giftInfo.getTotal());
                        tvPer.setText(per);

                        int total = 0;
                        try
                        {
                            total = Integer.parseInt(giftInfo.getTotal());
                        } catch (NumberFormatException e)
                        {
                            e.printStackTrace();
                        }
                        progressBar.setMax(total);
                        int remain = giftInfo.getRemain();
                        progressBar.setProgress(remain);

                        // 设置有效期
                        String validity = getString(R.string.gift_info_validity, giftInfo.getStime(),
                                giftInfo.getEtime());
                        tvValidity.setText(validity);

                        String platform = giftInfo.getPlatform();
                        // 如果等于1表示适用于安卓
                        if ("1".equals(platform))
                        {
                            ivAndroid.setVisibility(View.VISIBLE);
                            ivIos.setVisibility(View.GONE);
                        }
                        // 如果等于2表示适用于IOS
                        else if ("2".equals(platform))
                        {
                            // 移除Android图标控件,让IOS图标控件左移到它的位置
                            ivAndroid.setVisibility(View.GONE);
                            ivIos.setVisibility(View.VISIBLE);
                        }
                        // 其他表示责任两种平台都适用
                        else
                        {
                            ivAndroid.setVisibility(View.VISIBLE);
                            ivIos.setVisibility(View.VISIBLE);
                        }

                        // 如果游戏类型或者游戏大小为"null",那么不显示下载这一栏
                        String gameType = giftInfo.getGame_type();
                        String gameSize = giftInfo.getSize();
                        if (!(gameType == null || gameSize == null))
                        {
                            giftTypeGroup.setVisibility(View.VISIBLE);
                            // 设置游戏类型
                            gameType = getString(R.string.gift_info_type, gameType);
                            tvGameType.setText(gameType);
                            // 设置游戏大小
                            gameSize = getString(R.string.gift_info_size, gameSize);
                            tvSize.setText(gameSize);
                        }
                        // 去掉字符串中的html标签
                        Spanned content = Html.fromHtml(giftInfo.getContent());
                        tvContent.setText(content.toString());

                        Spanned get = Html.fromHtml(giftInfo.getHowget());
                        tvHowGet.setText(get.toString());
                    }
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg)
            {
                LogUtils.e(msg);
            }
        });
    }
}
