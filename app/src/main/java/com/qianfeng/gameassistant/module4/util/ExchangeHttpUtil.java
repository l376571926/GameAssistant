package com.qianfeng.gameassistant.module4.util;

import com.qianfeng.gameassistant.other.utils.ZhuShouContants;
import com.qianfeng.gameassistant.other.utils.ZhuShouHttpUtil;
import com.qianfeng.gameassistant.other.utils.ZhuShouTask;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Li Yiwei
 *
 * @date :  2016/1/17.
 */
public class ExchangeHttpUtil
{

    public static void requestExchangeList(final int type, ZhuShouTask.RequestCallback callback)
    {
        ZhuShouTask.Request request = new ZhuShouTask.Request()
        {
            @Override
            public Object doRequest()
            {
                Map<String, String> params = new HashMap<String, String>();
                //1最新奖品2最热奖品
                params.put("type", "" + type);
                //约定参数f76a177e2a04a43572c51ce222517a6f
                params.put("compare", "f76a177e2a04a43572c51ce222517a6f");

                return ZhuShouHttpUtil.doPost(ZhuShouContants.EXCHANGE_LIST, params);
            }
        };
        ZhuShouTask task = new ZhuShouTask(request, callback);
        task.execute();
    }

    /**
     * 请求奖品详情
     *
     * @param id       奖品id
     * @param callback 请求回调
     */
    public static void requestPrizeDetail(final String id, ZhuShouTask.RequestCallback callback)
    {
        ZhuShouTask.Request request = new ZhuShouTask.Request()
        {
            @Override
            public Object doRequest()
            {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return ZhuShouHttpUtil.doPost(ZhuShouContants.URL_PRIZE_DETAIL, params);
            }
        };
        ZhuShouTask task = new ZhuShouTask(request, callback);
        task.execute();
    }

}
