package cn.studyjamscn.s1.sj32.zhangqidi.activity;

import android.app.Application;
import android.util.Log;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.http.RequestQueue;

import java.io.File;

/**
 * BaseApplication
 * Created by AddiCheung on 2016/4/21 0021.
 */
public class BaseApplication extends Application {

    public String server;
    public String name;
    public static BaseApplication instance;

    public void setServer(String server) {
        this.server = server;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public String getName() {
        return name;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String path = getFilesDir()+"/cache";
        File file  = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        Log.i("设置缓存路径",""+file.getAbsolutePath());
        RxVolley.setRequestQueue(RequestQueue.newRequestQueue(file));
    }

    public static BaseApplication getInstance(){
        if(instance==null){
            instance = new BaseApplication();
        }
        return instance;
    }
}
