package cn.studyjamscn.s1.sj32.zhangqidi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.studyjamscn.s1.sj32.zhangqidi.R;
import cn.studyjamscn.s1.sj32.zhangqidi.activity.BaseApplication;


/**
 * Created by AddiCheung on 2016/4/19 0019.
 */
public class HomeFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String name = BaseApplication.getInstance().getName();
    private String server =  BaseApplication.getInstance().getServer();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle b = getArguments();
        server = b.getString("server");
        name = b.getString("name");
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_home);
        viewPager = (ViewPager) view.findViewById(R.id.vp_home);
        setupAdapter();
        return view;
    }

    /**
     * setup the adapter of recyclerview
     */
    private void setupAdapter() {
        String []mTitle = new String[]{getResources().getString(R.string.pager_fir),getResources().getString(R.string.pager_sec),
                };
        HomeAdatpter adaper= new HomeAdatpter(getChildFragmentManager());
        adaper.addFragments(InfoFragment.getInstance(),mTitle[0]);
        adaper.addFragments(HeroFragment.getInstance(),mTitle[1]);
        viewPager.setAdapter(adaper);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * the adapter
     */
    public static class HomeAdatpter extends FragmentPagerAdapter {
        List<String>mTitle = new ArrayList<>();
        List<Fragment> mFragment = new ArrayList<>();

        public void addFragments(Fragment fragment, String title){
            mFragment.add(fragment);
            mTitle.add(title);

        }
        public HomeAdatpter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragment.get(position);
        }

        @Override
        public int getCount() {
            return mFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle.get(position);
        }
    }
}
