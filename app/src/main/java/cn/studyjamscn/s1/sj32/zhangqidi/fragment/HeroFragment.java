package cn.studyjamscn.s1.sj32.zhangqidi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import java.util.ArrayList;
import java.util.List;

import cn.studyjamscn.s1.sj32.zhangqidi.Adapter.HeroAdapter;
import cn.studyjamscn.s1.sj32.zhangqidi.R;
import cn.studyjamscn.s1.sj32.zhangqidi.activity.BaseApplication;
import cn.studyjamscn.s1.sj32.zhangqidi.beans.HeroBean;
import cn.studyjamscn.s1.sj32.zhangqidi.utils.Conf;
import cn.studyjamscn.s1.sj32.zhangqidi.utils.JsonUtil;


/**
 * Created by AddiCheung on 2016/4/19 0019.
 */
public class HeroFragment extends BaseFragment {

    List<HeroBean> mData = new ArrayList<>();
    private RecyclerView recyclerView;
    private HeroAdapter adapter;
    private String name = BaseApplication.getInstance().getName();
    private String server = BaseApplication.getInstance().getServer();
    private TextView textNone;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hero, container, false);
        adapter = new HeroAdapter();
        recyclerView = (RecyclerView) view.findViewById(R.id.rl_hero);
        textNone = (TextView) view.findViewById(R.id.tv_hero_none);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        // setUpAdapter();
        Log.i("HeroFragment返回View", "!!!!!!!!!!!!!!!!!!!!!!!!");
        return view;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            setUpAdapter();
        }

    }
    /**
     * get instance of HeroFragment
     * @return
     */
    public static HeroFragment getInstance() {
        return new HeroFragment();
    }

    /**
     * setup the recyclerview's adapter
     */
    private void setUpAdapter() {
        Log.i("setUpAdapter()", "Size为" + mData.size());
        if (mData.size() > 0) {
            adapter.setData(mData);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        } else {
            textNone.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

    }

    /**
     * get data from network
     */
    private void getData() {
        HttpParams p = new HttpParams();
        p.put("serverName", server);
        p.put("playerName", name);
        RxVolley.get(Conf.USER_HERO, p, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                Log.i("开始解析HeroJson", "~~~~~~~~~~~~~~" + t);
                mData = JsonUtil.getHeroList(t);
                Log.i("解析HeroJson结束", "Size为" + mData.size());
                setUpAdapter();
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
            }
        });
    }

}
