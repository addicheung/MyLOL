package cn.studyjamscn.s1.sj32.zhangqidi.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * Created by AddiCheung on 2016/4/18 0018.
 */
public class SharePreferanceUtil {

    private static String PreferanceFile = Conf.PREF;

    public static SharedPreferences.Editor getEditor(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PreferanceFile,Context.MODE_PRIVATE);
        if(preferences!=null){
            return preferences.edit();
        }
        return null;
    }

    public static void setData(Context context,HashMap<String,String> data){
        Log.i("将基本信息写入pref","~~~~~~~~~~~~~~~~");
        SharedPreferences.Editor editor = SharePreferanceUtil.getEditor(context);
        Set<String> keySet = data.keySet();
        String []keyArray = new String[keySet.size()];
        Iterator<String> iterator = keySet.iterator();
        int i =0;
        while(iterator.hasNext()){
            String s = iterator.next();
            keyArray[i] = s;
            i++;
        }
        for(i = 0;i<keyArray.length;i++){
            editor.putString(keyArray[i],data.get(keyArray[i]));
        }
        editor.commit();
    }

    public static HashMap<String,String> getData(Context context,String []key){
        Log.i("从pref获取信息","~~~~~~~~~~~~~~~~");

        SharedPreferences sharedPreferences  = context.getSharedPreferences(PreferanceFile,Context.MODE_PRIVATE);
        HashMap<String,String> map = new HashMap<>();
        for(int i=0;i<key.length;i++){
            String s = sharedPreferences.getString(key[i],null);
            if(s!=null){
                map.put(key[i],s);
            }
        }
        return map;
    }
    public static void clearAll(Context context){
        SharedPreferences.Editor editor = SharePreferanceUtil.getEditor(context);
        editor.clear();
        editor.commit();

    }




}
