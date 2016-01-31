package com.qianfeng.gameassistant.module1.bean;

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
 * @date :  2016/1/15.
 */
public class Profit
{


    /**
     * id : 779
     * name : 火影忍者
     * score : 7.8
     * icon : http://i3.72g.com/upload/201601/201601120954055269.jpg
     * size : 362.40MB
     * dl_back_jifen : 600
     * h5src :
     * android_dl : http://dlied5.myapp.com/myapp/1104307008/KiHan/10012578_Naruto-1.5.4.12.apk
     * ios_dl :
     * count_dl : 3093
     * all_prize : 3000
     */

    private String id;
    private String name;
    private String score;
    private String icon;
    private String size;
    private String dl_back_jifen;
    private String h5src;
    private String android_dl;
    private String ios_dl;
    private String count_dl;
    private int all_prize;

    public static List<Profit> arrayProfitFromData(String str, String key)
    {

        try
        {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Profit>>()
            {
            }.getType();

            /**
             * 为什么要把.getString(str)改为.getJSONArray(key).toString()才能得到正确的结果
             */
            return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setScore(String score)
    {
        this.score = score;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public void setDl_back_jifen(String dl_back_jifen)
    {
        this.dl_back_jifen = dl_back_jifen;
    }

    public void setH5src(String h5src)
    {
        this.h5src = h5src;
    }

    public void setAndroid_dl(String android_dl)
    {
        this.android_dl = android_dl;
    }

    public void setIos_dl(String ios_dl)
    {
        this.ios_dl = ios_dl;
    }

    public void setCount_dl(String count_dl)
    {
        this.count_dl = count_dl;
    }

    public void setAll_prize(int all_prize)
    {
        this.all_prize = all_prize;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getScore()
    {
        return score;
    }

    public String getIcon()
    {
        return icon;
    }

    public String getSize()
    {
        return size;
    }

    public String getDl_back_jifen()
    {
        return dl_back_jifen;
    }

    public String getH5src()
    {
        return h5src;
    }

    public String getAndroid_dl()
    {
        return android_dl;
    }

    public String getIos_dl()
    {
        return ios_dl;
    }

    public String getCount_dl()
    {
        return count_dl;
    }

    public int getAll_prize()
    {
        return all_prize;
    }
}
