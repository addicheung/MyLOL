package cn.studyjamscn.s1.sj32.zhangqidi.beans;

/**
 * Created by AddiCheung on 2016/4/19 0019.
 */
public class RankBean {

    /**
     * message : 1
     * name : 周杰伦
     * duanwei : 白银III
     * rank : 1283
     * rank6 : 白银Ⅲ
     * matchstr : 10胜0负（黄金Ⅲ）9胜1负（黄金Ⅳ）8胜2负（黄金Ⅴ）7胜3负（白银Ⅰ）6胜4负（白银Ⅱ）5胜5负（白银Ⅲ）4胜6负（白银Ⅲ）3胜7负（白银Ⅳ）2胜8负（白银Ⅴ）1胜9负（青铜Ⅰ）0胜10负（青铜Ⅱ）
     */

    private String message;
    private String name;
    private String duanwei;
    private String rank;
    private String rank6;
    private String matchstr;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuanwei() {
        return duanwei;
    }

    public void setDuanwei(String duanwei) {
        this.duanwei = duanwei;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRank6() {
        return rank6;
    }

    public void setRank6(String rank6) {
        this.rank6 = rank6;
    }

    public String getMatchstr() {
        return matchstr;
    }

    public void setMatchstr(String matchstr) {
        this.matchstr = matchstr;
    }
}
