package com.qianfeng.gameassistant.module1.util;


import com.qianfeng.gameassistant.other.utils.ZhuShouHttpUtil;
import com.qianfeng.gameassistant.other.utils.ZhuShouTask;

import java.util.HashMap;
import java.util.Map;

/**
 * 赚钱模块网络请求都在这里
 * Created by Li Yiwei
 *
 * @date :  2016/1/13.
 */
public class ProfitHttpUtil
{
    /**
     * 赚钱模块ListView游戏列表的url
     */
    public static final String URL_GAME_LIST = "http://zhushou.72g.com/app/game/game_list/";

    /**
     * 请求游戏列表
     *
     * @param callback 请求回调
     */
    public static void requestProfitList(ZhuShouTask.RequestCallback callback)
    {

        ZhuShouTask.Request request = new ZhuShouTask.Request()
        {
            @Override
            public Object doRequest()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("platform", "2");
                params.put("compare", "" + "f76a177e2a04a43572c51ce222517a6f");
                return ZhuShouHttpUtil.doPost(URL_GAME_LIST, params);
            }
        };
        //创建一个任务
        ZhuShouTask task = new ZhuShouTask(request, callback);
        //执行任务
        task.execute();
    }
}
