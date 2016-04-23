package cn.studyjamscn.s1.sj32.zhangqidi.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.studyjamscn.s1.sj32.zhangqidi.R;
import cn.studyjamscn.s1.sj32.zhangqidi.View.ZhanDouLIView;
import cn.studyjamscn.s1.sj32.zhangqidi.activity.BaseApplication;
import cn.studyjamscn.s1.sj32.zhangqidi.beans.DuanWeiBean;
import cn.studyjamscn.s1.sj32.zhangqidi.beans.RecordBean;
import cn.studyjamscn.s1.sj32.zhangqidi.beans.UserInfoBean;
import cn.studyjamscn.s1.sj32.zhangqidi.utils.Conf;

/**
 * Created by AddiCheung on 2016/4/19 0019.
 */
public class InfoFragment extends BaseFragment {

    private String server = BaseApplication.getInstance().getServer();
    private String name = BaseApplication.getInstance().getName();
    private TextView tv_info_name;
    private TextView tv_info_level;
    private TextView tv_info_good;
    private ZhanDouLIView info_rangeView;
    private ImageView iv_duanwei_name;
    private ImageView iv_info_portrait;
    private TextView tv_duanwei_name;
    private TextView tv_duanwei_leaguepoints;
    private TextView tv_duanwei_warzoneupdated;
    private ZhanDouLIView info_record_jingdian;
    private ZhanDouLIView info_record_renji;
    private ZhanDouLIView info_record_luandou;
    private HttpParams params;

    public static InfoFragment getInstance() {
        return new InfoFragment();
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0X123) {
                Bundle b = msg.getData();
                byte[] data = (byte[]) b.get("data");
                iv_info_portrait.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        initView(view);
        params = new HttpParams();
        params.put("playerName", name);
        params.put("serverName", server);
        setInfoData();
        setDuanweiData();
        setRecordData();
        return view;
    }

    /**
     * innitial view
     */
    private void initView(View v) {
        tv_info_name = (TextView) v.findViewById(R.id.tv_info_name);
        tv_info_level = (TextView) v.findViewById(R.id.tv_info_level);
        tv_info_good = (TextView) v.findViewById(R.id.tv_info_good);
        iv_info_portrait = (ImageView) v.findViewById(R.id.iv_info_portrait);
        info_rangeView = (ZhanDouLIView) v.findViewById(R.id.info_rangeView);
        iv_duanwei_name = (ImageView) v.findViewById(R.id.iv_duanwei_name);
        tv_duanwei_name = (TextView) v.findViewById(R.id.tv_duanwei_name);
        tv_duanwei_leaguepoints = (TextView) v.findViewById(R.id.tv_duanwei_leaguepoints);
        tv_duanwei_warzoneupdated = (TextView) v.findViewById(R.id.tv_duanwei_warzoneupdated);
        info_record_jingdian = (ZhanDouLIView) v.findViewById(R.id.info_record_jingdian);
        info_record_renji = (ZhanDouLIView) v.findViewById(R.id.info_record_renji);
        info_record_luandou = (ZhanDouLIView) v.findViewById(R.id.info_record_luandou);
    }

    private void setInfoData() {
        final String[] picUrl = new String[1];
        RxVolley.get(Conf.USRR_INFO, params, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                if (!t.isEmpty()) {
                    UserInfoBean bean;
                    bean = new Gson().fromJson(t, UserInfoBean.class);
                    tv_info_name.setText(name);
                    tv_info_level.setText(bean.getLevel() + "级");
                    tv_info_good.setText(bean.getGood());
                    info_rangeView.setProgressRange(Integer.parseInt(bean.getZhandouli()),10000);
                    picUrl[0] = bean.getPortrait();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            RxVolley.get(picUrl[0], new HttpCallback() {
                                @Override
                                public void onSuccessInAsync(byte[] t) {
                                    Bundle b = new Bundle();
                                    b.putByteArray("data", t);
                                    Message msg = new Message();
                                    msg.setData(b);
                                    msg.what = 0X123;
                                    handler.sendMessage(msg);
                                }
                            });
                        }
                    }).start();
                } else {
                    Log.i("userinfo为空", "~~~~~~~");
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
            }
        });

        // iv_info_portrait.setImageBitmap(Net.loadBitmap(bean[0].getPortrait()));

    }

    private void setDuanweiData() {
        final DuanWeiBean[] bean = {new DuanWeiBean()};
        String realServerName = getRealServerName();
        HttpParams p = new HttpParams();
        p.put("playerName", name);
        p.put("serverName", realServerName);
        RxVolley.get(Conf.USER_DUANWEI, p, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                if (!t.isEmpty()) {
                    bean[0] = new Gson().fromJson(t, DuanWeiBean.class);
                    tv_duanwei_name.setText(bean[0].getTier());
                    tv_duanwei_leaguepoints.setText(bean[0].getLeague_points());
                    tv_duanwei_warzoneupdated.setText(bean[0].getWarzone_updated());
                    if (bean[0].getTier() != null) {
                        setDuanweiPic(bean[0].getTier());
                    } else {
                        setDuanweiPic("null");
                    }
                } else {
                    Log.i("userinfo为空", "~~~~~~~");
                }
            }
        });
    }

    /**
     * set record cardview data
     */
    private void setRecordData() {
        Log.i("setRecordData函数", "进行中");
        RxVolley.get(Conf.USER_RECORD, params, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                if (!t.isEmpty()) {
                    RecordBean bean = new Gson().fromJson(t, RecordBean.class);
                    String record = bean.getRecord();
                    setRecordDataBySring(record);
                }
            }
        });

    }

    /**
     * set record cardview data
     */
    private String getRealServerName() {
        int i;
        String[] serverList = getResources().getStringArray(R.array.server_list);
        String[] realServerList = getResources().getStringArray(R.array.server_list2);
        for (i = 0; i < serverList.length; i++) {
            if (server.equals(serverList[i])) {
                break;
            }
        }
        return realServerList[i];
    }

    public void setDuanweiPic(String duanweiPic) {
        switch (duanweiPic) {
            case "null":
                iv_duanwei_name.setImageResource(R.drawable.dan_none);
                tv_duanwei_leaguepoints.setText("0");
                tv_duanwei_warzoneupdated.setText("暂无更新日期");
                tv_duanwei_name.setText("暂无段位");
                break;
            case "最强王者":
                iv_duanwei_name.setImageResource(R.drawable.dan_challenger);
                break;
            case "超凡大师":
                iv_duanwei_name.setImageResource(R.drawable.dan_master);
                break;
            case "钻石":
                iv_duanwei_name.setImageResource(R.drawable.dan_diamond);
                break;
            case "白金":
                iv_duanwei_name.setImageResource(R.drawable.dan_platinum);
                break;
            case "黄金":
                iv_duanwei_name.setImageResource(R.drawable.dan_gold);
                break;
            case "白银":
                iv_duanwei_name.setImageResource(R.drawable.dan_silver);
                break;
            case "黄铜":
                iv_duanwei_name.setImageResource(R.drawable.dan_bronze);
                break;
            default:
                iv_duanwei_name.setImageResource(R.drawable.dan_none);
        }
    }


    public void setRecordDataBySring(String record) {
        Log.i("正则表达式输出",record);
        Pattern p  = Pattern.compile("\\d+(\\.\\d+)?");
        int range[] = new int[10];
        int i =0;
        Matcher match = p.matcher(record);
        while (match.find()){
            Log.i("正则表达式输出",match.group());
            range[i++] = (int) Float.parseFloat(match.group());
        }
        i=0;
        info_record_jingdian.setProgressRange(range[1],100);
        info_record_renji.setProgressRange(range[3],100);
        info_record_luandou.setProgressRange(range[5],100);
    }
}
