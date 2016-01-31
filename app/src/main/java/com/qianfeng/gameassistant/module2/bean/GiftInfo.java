package com.qianfeng.gameassistant.module2.bean;


import com.google.gson.Gson;

/**
 * Created by Li Yiwei
 *
 * @date :  2016/1/13.
 */
public class GiftInfo
{


    /**
     * id : 4595
     * name : 《王者荣耀》72G媒体礼包
     * gamename : 王者荣耀
     * icon : http://i3.72g.com/upload/201510/201510261436311061.png
     * total : 220
     * remain : 0
     * stime : 2015-10-26
     * etime : 2016-10-15
     * consume : 0
     * platform : 3
     * content : 双倍经验卡：1日
     * 双倍金币卡：1日
     * 2级符文:吸收
     * 2级符文:急速
     * 88金币
     * howget : 请登陆http://pvp.qq.com/webplat/info/news_version3/15592/15748/15749/15751/m12014/201508/370256.shtml网站，使用游戏内帐号登录手机QQ或微信，用手机扫描对应二维码。打开兑换页面后输入礼包卡密码，点击确认再返回游戏内领取。
     * 注意事项：
     * 1.兑换前请确认已创建游戏角色；
     * 2.进入兑换中心后，正确填写相关信息，兑换成功后进入游戏查收礼包；
     * 3.微信玩家只能通过微信专属领奖页面兑换，手机QQ玩家只能通过QQ专属领奖页面兑换；
     * （已领取礼包可在个人信息-“我的礼包”中查看，领取到的礼包请尽快使用避免其过久未使用进入淘号。）
     * <p/>
     * <p/>
     * game_id : 773
     * game_type : 角色扮演
     * size : 199MB
     * android_dl : http://dlied5.myapp.com/myapp/1104466820/sgame/10005823_SGame1027.apk
     * ios_dl : null
     */

    private String id;
    private String name;
    private String gamename;
    private String icon;
    private String total;
    private int remain;
    private String stime;
    private String etime;
    private String consume;
    private String platform;
    private String content;
    private String howget;
    private String game_id;
    private String game_type;
    private String size;
    private String android_dl;
    private Object ios_dl;

    public static GiftInfo objectFromData(String str)
    {

        return new Gson().fromJson(str, GiftInfo.class);
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setGamename(String gamename)
    {
        this.gamename = gamename;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public void setTotal(String total)
    {
        this.total = total;
    }

    public void setRemain(int remain)
    {
        this.remain = remain;
    }

    public void setStime(String stime)
    {
        this.stime = stime;
    }

    public void setEtime(String etime)
    {
        this.etime = etime;
    }

    public void setConsume(String consume)
    {
        this.consume = consume;
    }

    public void setPlatform(String platform)
    {
        this.platform = platform;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public void setHowget(String howget)
    {
        this.howget = howget;
    }

    public void setGame_id(String game_id)
    {
        this.game_id = game_id;
    }

    public void setGame_type(String game_type)
    {
        this.game_type = game_type;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public void setAndroid_dl(String android_dl)
    {
        this.android_dl = android_dl;
    }

    public void setIos_dl(Object ios_dl)
    {
        this.ios_dl = ios_dl;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getGamename()
    {
        return gamename;
    }

    public String getIcon()
    {
        return icon;
    }

    public String getTotal()
    {
        return total;
    }

    public int getRemain()
    {
        return remain;
    }

    public String getStime()
    {
        return stime;
    }

    public String getEtime()
    {
        return etime;
    }

    public String getConsume()
    {
        return consume;
    }

    public String getPlatform()
    {
        return platform;
    }

    public String getContent()
    {
        return content;
    }

    public String getHowget()
    {
        return howget;
    }

    public String getGame_id()
    {
        return game_id;
    }

    public String getGame_type()
    {
        return game_type;
    }

    public String getSize()
    {
        return size;
    }

    public String getAndroid_dl()
    {
        return android_dl;
    }

    public Object getIos_dl()
    {
        return ios_dl;
    }
}
