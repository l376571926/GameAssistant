package com.qianfeng.gameassistant.module3.util;


import com.qianfeng.gameassistant.other.utils.ZhuShouHttpUtil;
import com.qianfeng.gameassistant.other.utils.ZhuShouTask;

import java.util.HashMap;
import java.util.Map;

/**
 * 活动模块网络请求都在这里
 * Created by Li Yiwei
 *
 * @date :  2016/1/13.
 */
public class ActiveHttpUtil
{
    /**
     * 活动模块ListView活动列表的url
     */
    public static final String URL_ACTIVE_LIST = "http://zhushou.72g.com/app/activity/activity_list/";
    /**
     * 横幅广告
     */
    public static final String URL_BANNER_LIST = "http://zhushou.72g.com/app/common/banner_info/";



    /**
     * 请求活动列表
     *
     * @param callback 请求回调
     */
    public static void requestActiveList(ZhuShouTask.RequestCallback callback)
    {

        ZhuShouTask.Request request = new ZhuShouTask.Request()
        {
            @Override
            public Object doRequest()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("platform", "2");
                return ZhuShouHttpUtil.doPost(URL_ACTIVE_LIST, params);
            }
        };
        //创建一个任务
        ZhuShouTask task = new ZhuShouTask(request, callback);
        //执行任务
        task.execute();
    }

    public static void requestBannerList(ZhuShouTask.RequestCallback callback)
    {
        ZhuShouTask.Request request = new ZhuShouTask.Request()
        {
            @Override
            public Object doRequest()
            {
                Map<String,String> params = new HashMap<>();
                /**
                 *
                 */
                params.put("platform", "2");
                return ZhuShouHttpUtil.doPost(URL_BANNER_LIST,params);
            }
        };
        ZhuShouTask task = new ZhuShouTask(request,callback);
        task.execute();
    }
}
