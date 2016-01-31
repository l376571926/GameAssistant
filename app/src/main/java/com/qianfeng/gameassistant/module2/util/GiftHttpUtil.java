package com.qianfeng.gameassistant.module2.util;


import com.qianfeng.gameassistant.other.utils.ZhuShouHttpUtil;
import com.qianfeng.gameassistant.other.utils.ZhuShouTask;

import java.util.HashMap;
import java.util.Map;

/**
 * 礼包模块网络请求都在这里
 * Created by Li Yiwei
 *
 * @date :  2016/1/13.
 */
public class GiftHttpUtil {
    /**
     * 请求礼包列表的url
     */
    public static final String URL_GIFT_LIST = "http://zhushou.72g.com/app/gift/gift_list/";

    /**
     * 礼包详情url
     */
    public static final String URL_GIFT_INFO = "http://zhushou.72g.com/app/gift/gift_info/";



    /**
     * 请求礼包列表
     *
     * @param callback 请求回调
     */
    public static void requestGiftList(final int type, final int page,ZhuShouTask.RequestCallback callback) {

        ZhuShouTask.Request request = new ZhuShouTask.Request() {
            @Override
            public Object doRequest() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("platform", "2");
                params.put("gifttype", "" + type);
                params.put("page", "" + page);
                return ZhuShouHttpUtil.doPost(URL_GIFT_LIST, params);
            }
        };
        //创建一个任务
        ZhuShouTask task = new ZhuShouTask(request, callback);
        //执行任务
        task.execute();
    }

    /**
     * 请求礼包详情
     *
     * @param id       礼包id
     * @param callback 请求回调
     */
    public static void requestGiftInfo(final String id, ZhuShouTask.RequestCallback callback) {
        ZhuShouTask.Request request = new ZhuShouTask.Request() {
            @Override
            public Object doRequest() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                return ZhuShouHttpUtil.doPost(URL_GIFT_INFO, params);
            }
        };
        //创建一个任务
        ZhuShouTask task = new ZhuShouTask(request, callback);
        //执行任务
        task.execute();
    }
}
