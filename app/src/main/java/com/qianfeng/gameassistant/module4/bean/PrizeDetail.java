package com.qianfeng.gameassistant.module4.bean;

import com.google.gson.Gson;

/**
 * Created by Li Yiwei
 *
 * @date :  2016/1/17.
 */
public class PrizeDetail
{

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

    public static PrizeDetail objectFromData(String str)
    {

        return new Gson().fromJson(str, PrizeDetail.class);
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setConsume(String consume)
    {
        this.consume = consume;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public void setRemain(String remain)
    {
        this.remain = remain;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getConsume()
    {
        return consume;
    }

    public String getIcon()
    {
        return icon;
    }

    public String getRemain()
    {
        return remain;
    }
}
