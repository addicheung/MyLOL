package cn.studyjamscn.s1.sj32.zhangqidi.utils;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

/**
 * Created by AddiCheung on 2016/4/19 0019.
 */
public class Net {

    public static byte[] loadBitmap(String url){
        final byte[][] data = new byte[1][1];
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccessInAsync(byte[] t) {
                data[0] = t;
            }
        });
        return data[0];
    }

}
