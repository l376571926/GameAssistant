package com.qianfeng.gameassistant.module1.bean;

import com.google.gson.Gson;

/**
 * Created by Li Yiwei
 *
 * @date :  2016/1/17.
 */
public class GameDetail
{

    /**
     * id : 779
     * name : 火影忍者
     * score : 7.8
     * version : 1.5.2.9
     * icon : http://i3.72g.com/upload/201601/201601120954055269.jpg
     * snapshot : http://i3.72g.com/upload/201601/201601111830205273.jpg,http://i3.72g.com/upload/201601/201601111830208193.jpg,http://i3.72g.com/upload/201601/201601111830203641.jpg
     * size : 362.40MB
     * dl_back_jifen : 600
     * android_dl : http://dlied5.myapp.com/myapp/1104307008/KiHan/10012578_Naruto-1.5.4.12.apk
     * ios_dl : null
     * count_dl : 3174
     * game_desc : 《火影忍者》手游是一款格斗游戏，原汁原味是这款游戏最基本的情怀。它独有的忍术格斗系统，能够让玩家尽情的释放炫酷的忍术技能。炸裂的打击感成就了完美的格斗体验。同时，丰富的忍者角色可以让玩家随意切换，第一人称扮演各种心爱的角色，除了能延续动漫的经典，还能弥补玩家们的遗憾。火影迷将能在多终端的跨屏游戏中，体验原汁原味的火影世界。
     * swxx :
     * game_task_state : 0
     * limit_number : 0
     */

    private String id;
    private String name;
    private String score;
    private String version;
    private String icon;
    private String snapshot;
    private String size;
    private String dl_back_jifen;
    private String android_dl;
    private Object ios_dl;
    private String count_dl;
    private String game_desc;
    private String swxx;
    private String game_task_state;
    private String limit_number;

    public static GameDetail objectFromData(String str)
    {

        return new Gson().fromJson(str, GameDetail.class);
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

    public void setVersion(String version)
    {
        this.version = version;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public void setSnapshot(String snapshot)
    {
        this.snapshot = snapshot;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public void setDl_back_jifen(String dl_back_jifen)
    {
        this.dl_back_jifen = dl_back_jifen;
    }

    public void setAndroid_dl(String android_dl)
    {
        this.android_dl = android_dl;
    }

    public void setIos_dl(Object ios_dl)
    {
        this.ios_dl = ios_dl;
    }

    public void setCount_dl(String count_dl)
    {
        this.count_dl = count_dl;
    }

    public void setGame_desc(String game_desc)
    {
        this.game_desc = game_desc;
    }

    public void setSwxx(String swxx)
    {
        this.swxx = swxx;
    }

    public void setGame_task_state(String game_task_state)
    {
        this.game_task_state = game_task_state;
    }

    public void setLimit_number(String limit_number)
    {
        this.limit_number = limit_number;
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

    public String getVersion()
    {
        return version;
    }

    public String getIcon()
    {
        return icon;
    }

    public String getSnapshot()
    {
        return snapshot;
    }

    public String getSize()
    {
        return size;
    }

    public String getDl_back_jifen()
    {
        return dl_back_jifen;
    }

    public String getAndroid_dl()
    {
        return android_dl;
    }

    public Object getIos_dl()
    {
        return ios_dl;
    }

    public String getCount_dl()
    {
        return count_dl;
    }

    public String getGame_desc()
    {
        return game_desc;
    }

    public String getSwxx()
    {
        return swxx;
    }

    public String getGame_task_state()
    {
        return game_task_state;
    }

    public String getLimit_number()
    {
        return limit_number;
    }
}
