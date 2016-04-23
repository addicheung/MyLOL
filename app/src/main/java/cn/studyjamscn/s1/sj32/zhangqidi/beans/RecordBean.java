package cn.studyjamscn.s1.sj32.zhangqidi.beans;

/**
 * 战绩
 * Created by AddiCheung on 2016/4/19 0019.
 */
public class RecordBean {

    /**
     * message : 1
     * Record1 : 经典模式（951）（50.3%）人机对战（75）（93.3%）大乱斗（6）（50%）
     */

    private String message;
    private String Record;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecord() {
        return Record;
    }

    public void setRecord(String Record) {
        this.Record = Record;
    }
}
