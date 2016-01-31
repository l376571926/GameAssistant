package com.qianfeng.gameassistant.other.utils;

import java.util.HashMap;
import java.util.Map;

import com.qianfeng.gameassistant.other.utils.ZhuShouTask.Request;

/**
 * 请求版本等接口操作
 * 
 * Created by Li Yiwei
 *
 * @date : 2016/1/14.
 */
public class OtherHttpUtil
{
	/**
	 * 请求版本Url
	 */
	public static final String URL_UPGRADE = "http://zhushou.72g.com/app/common/upgrade";

	/**
	 * 执行请求版本操作
	 *
	 * @param version
	 *            版本号
	 * @param callback
	 *            请求回调
	 */
	public static void requestVersion(final String version, ZhuShouTask.RequestCallback callback)
	{
		ZhuShouTask.Request request = new ZhuShouTask.Request()
		{
			@Override
			public Object doRequest()
			{
				Map<String, String> params = new HashMap<>();
				params.put("platform", "2");
				params.put("ver", version);

				return ZhuShouHttpUtil.doPost(URL_UPGRADE, params);

			}
		};

		ZhuShouTask task = new ZhuShouTask(request, callback);
		task.execute();

	}

	/**
	 * 下载apk
	 * 
	 * @param url
	 *            下载地址
	 * @param callback
	 *            下载回调
	 */
	public static void downLoadApk(final String url, ZhuShouTask.RequestCallback callback,
			final ZhuShouTask.UpgradeListener progress)
	{
		ZhuShouTask.Request request = new Request()
		{
			@Override
			public Object doRequest()
			{
				// TODO Auto-generated method stub
				return ZhuShouHttpUtil.downLoadFile(FileUtil.APK_DIR, "zhushou72G.apk", url, progress);
			}
		};
		ZhuShouTask task = new ZhuShouTask(request, callback);
		task.execute();
	}
}
