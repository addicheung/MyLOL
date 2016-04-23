package cn.studyjamscn.s1.sj32.zhangqidi.beans;

/**
 * 玩家信息
 * Created by AddiCheung on 2016/4/19 0019.
 */
public class UserInfoBean {

    /**
     * message : 1
     * portrait : http://img.lolbox.duowan.com/profileIcon/profileIcon1113.jpg
     * level : 30
     * zhandouli : 4816
     * good : 被赞 227 次
     */

    private String message;
    private String portrait;
    private String level;
    private String zhandouli;
    private String good;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getZhandouli() {
        return zhandouli;
    }

    public void setZhandouli(String zhandouli) {
        this.zhandouli = zhandouli;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }
}
