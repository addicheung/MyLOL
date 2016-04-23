package cn.studyjamscn.s1.sj32.zhangqidi.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.studyjamscn.s1.sj32.zhangqidi.beans.HeroBean;

/**
 * Util class used to analyze JSONString
 * Created by AddiCheung on 2016/4/21 0021.
 */
public class JsonUtil  {

    public static List<HeroBean> getHeroList(String jsonString){
        List<HeroBean> heroBean = new ArrayList<>();
        Log.i("getHeroList解析HeroJson","~~~~~"+jsonString);
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray herostr = jsonObject.getJSONArray("herostr");
            if(herostr==null){
                Log.i("解析HeroJson出差错","~~~~~");
            }else{
                for(int i=0;i<herostr.length();i++){
                    JSONObject heroFa= herostr.getJSONObject(i);
                    JSONObject heraCh= heroFa.getJSONObject("attr");
                    HeroBean bean = new HeroBean();
                    Log.i("这是一个jsonObject",""+heraCh.getString("title")+":"+heraCh.getString("src"));
                    bean.setTitle(heraCh.getString("title"));
                    bean.setSrc(heraCh.getString("src"));
                    bean.setAlt(heraCh.getString("alt"));
                    heroBean.add(bean);
                }
            }
            if(heroBean.size()==0){
                Log.i("herobean为0","~~~");
            }
            return heroBean;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
