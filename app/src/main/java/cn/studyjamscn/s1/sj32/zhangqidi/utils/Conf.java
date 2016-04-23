package cn.studyjamscn.s1.sj32.zhangqidi.utils;

/**
 * APIs and some setting paramters
 *
 * Created by AddiCheung on 2016/4/18 0018.
 */
public class Conf {

    /**
     *玩家信息
     *parameters:
     *serverName=服务器名&playerName=玩家名
     *    message:1(message返回值由系统控制 如果返回1那么正常)
         portrait:头像
         level:等级
         zhandouli:战斗力
         good:被赞次数
     *
     */
    public static String USRR_INFO = "http://API.xunjob.cn/playerinfo.php";

    /**
     * 玩家战绩
     * parameters:
     * serverName=服务器名&playerName=玩家名
     */
    public static String USER_RECORD = "http://API.xunjob.cn/Record.php";

    /**
     * 玩家常用英雄
     * parameters:
     * serverName=服务器名&playerName=玩家名
     */
    public static String USER_HERO = "http://API.xunjob.cn/hero.php";


    /**
     * 英雄数据
     * parameters:
     * serverName=服务器名&playerName=玩家名
     */

    public static String HROE_DATA ="http://lolbox.duowan.com/new/api/index.php?_do=personal/championslist&";
    /**
     * 段位数据
     * parameters:
     * serverName=服务器名&playerName=玩家名
     */
    public static String USER_DUANWEI = "http://API.xunjob.cn/s5str.php";

    /**
     * parameters:
     * serverName=服务器名&playerName=玩家名
     */
    public static String RANK = "http://API.xunjob.cn/rank.php";


    /**
     * PreferanceName
     */
    public static String PREF = "mylol";


}
