package cn.studyjamscn.s1.sj32.zhangqidi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.studyjamscn.s1.sj32.zhangqidi.R;

/**
 * Created by AddiCheung on 2016/4/19 0019.
 */
public class AboutFragment extends BaseFragment {


    /**
     * get instance of AboutFragment
     * @return
     */
    public static AboutFragment getInstance(){
        return new AboutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_about,container,false);
        return view;
    }
}
