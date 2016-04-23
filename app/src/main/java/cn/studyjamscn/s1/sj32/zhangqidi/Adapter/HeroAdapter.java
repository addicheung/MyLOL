package cn.studyjamscn.s1.sj32.zhangqidi.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import java.util.ArrayList;
import java.util.List;

import cn.studyjamscn.s1.sj32.zhangqidi.beans.HeroBean;
import cn.studyjamscn.s1.sj32.zhangqidi.R;

/**
 * the hero adapter
 * Created by AddiCheung on 2016/4/20 0020.
 */
public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder> {

    List<HeroBean> mData = new ArrayList<>();


    public void setData(List<HeroBean> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("设置HeroAdapter出错", "mData为空");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hero, parent, false);
        HeroViewHolder holder = new HeroViewHolder(view);
        return holder;

    }
    @Override
    public void onBindViewHolder(HeroViewHolder holder, int position) {
        Bitmap bitmap =  downImage(mData.get(position).getSrc());
        holder.imageView.setImageBitmap(bitmap);
        //holder.imageView.setImageResource(R.mipmap.ic_launcher);
        holder.textView.setText(mData.get(position).getTitle());
    }

    private Bitmap downImage(String src) {
        final Bitmap[] bitmap = {null};
        byte[] data = RxVolley.getCache(src);
        if (data.length == 0) {
            new RxVolley.Builder().url(src).httpMethod(RxVolley.Method.GET).shouldCache(true).callback(new HttpCallback() {
                @Override
                public void onSuccessInAsync(byte[] t) {
                    bitmap[0] = BitmapFactory.decodeByteArray(t, 0, t.length);
                    notifyDataSetChanged();
                }
            }).doTask();
        } else {
            bitmap[0] = BitmapFactory.decodeByteArray(data, 0, data.length);
        }
        return bitmap[0];
    }

    @Override
    public int getItemCount() {
        if(mData==null){
            return 0;
        }
        return mData.size();
    }

    public static class HeroViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public HeroViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.iv_hero_pic);
            textView = (TextView) v.findViewById(R.id.tv_hero_nametime);
        }
    }
}
