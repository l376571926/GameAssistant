package com.qianfeng.gameassistant.module2.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Li Yiwei
 *
 * @date : 2016/1/12.
 */
public class Gift {

	/**
	 * id : 4595 name : 《王者荣耀》72G媒体礼包 gamename : 王者荣耀 icon :
	 * http://i3.72g.com/upload/201510/201510261436311061.png remain : 0
	 * gifttype : 1 consume : 0 content : 双倍经验卡：1日 双倍金币卡：1日 2级符文:吸收 2级符文:急速 88金币
	 */

	private String id;
	private String name;
	private String gamename;
	private String icon;
	private int remain;
	private String gifttype;
	private String consume;
	private String content;

	public static List<Gift> arrayGiftFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);
			Type listType = new TypeToken<ArrayList<Gift>>() {
			}.getType();

			return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return new ArrayList<Gift>();

	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}

	public void setGifttype(String gifttype) {
		this.gifttype = gifttype;
	}

	public void setConsume(String consume) {
		this.consume = consume;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getGamename() {
		return gamename;
	}

	public String getIcon() {
		return icon;
	}

	public int getRemain() {
		return remain;
	}

	public String getGifttype() {
		return gifttype;
	}

	public String getConsume() {
		return consume;
	}

	public String getContent() {
		return content;
	}
}
