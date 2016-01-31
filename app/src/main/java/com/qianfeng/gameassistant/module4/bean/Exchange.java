package com.qianfeng.gameassistant.module4.bean;


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
 * @date :  2016/1/12.
 */
public class Exchange {


    /**
     * id : 573
     * name : 运河电热毯 双档调温安全保护单人斑点花 150*70cm
     * consume : 80000
     * icon : http://i3.265g.com/images/201510/201510211650104413.jpg
     * remain : 4
     */

    private String id;
    private String name;
    private String consume;
    private String icon;
    private String remain;

    public static Exchange objectFromData(String str) {

        return new Gson().fromJson(str, Exchange.class);
    }



    public static List<Exchange> arrayExchangeFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Exchange>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList<Exchange>();


    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getConsume() {
        return consume;
    }

    public String getIcon() {
        return icon;
    }

    public String getRemain() {
        return remain;
    }
}
