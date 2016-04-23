package cn.studyjamscn.s1.sj32.zhangqidi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.studyjamscn.s1.sj32.zhangqidi.fragment.AboutFragment;
import cn.studyjamscn.s1.sj32.zhangqidi.fragment.HomeFragment;
import cn.studyjamscn.s1.sj32.zhangqidi.utils.SharePreferanceUtil;
import cn.studyjamscn.s1.sj32.zhangqidi.R;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private AboutFragment aboutFragment;
    private HomeFragment homeFragment;
    private ImageView image;
    private TextView text;
    private String name = BaseApplication.getInstance().getName();
    private String server =  BaseApplication.getInstance().getServer();
    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle drawToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        aboutFragment =AboutFragment.getInstance();
        homeFragment = new HomeFragment();

        switch2Home();

    }

    /**
     * Initial all views of MainActivity
     */
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout  = (DrawerLayout) findViewById(R.id.dl_main);
        navigationView = (NavigationView) findViewById(R.id.nv_main);
        View view = navigationView.getHeaderView(0);
        image = (ImageView) view.findViewById(R.id.iv_user_image);
        text = (TextView) view.findViewById(R.id.tv_user_name);
        setDrawer();
    }

    /**
     * switch to the HomeFragment
     */
    private void switch2Home() {
        Log.i("切换home_fragment","!!!!!!!!!!!!!!!!!!!!");
        if(!homeFragment.isAdded()) {
            Bundle b = new Bundle();
            b.putString("server",server);
            b.putString("name",name);
            homeFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, homeFragment).commit();
        }else if (aboutFragment.isResumed()){
            getSupportFragmentManager().beginTransaction().hide(aboutFragment).show(homeFragment).commit();
        }
    }

    /**
     *switch to AboutFragment
     */
    private void switch2About() {
        Log.i("切换about_fragment","!!!!!!!!!!!!!!!!!!!!");
     /*   getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,aboutFragment).commit();
     */   if(!aboutFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().hide(homeFragment).add(R.id.fl_content, aboutFragment).commit();
        }else{
            getSupportFragmentManager().beginTransaction().hide(homeFragment).show(aboutFragment).commit();
        }
    }

    /**
     * exit the whole app
     */
    private void exitApplication() {
        finish();
        System.exit(0);
    }

    /**
     * onNavigationItemSelected
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:
                switch2Home();
                break;
            case R.id.nav_about:
                switch2About();
                break;
            case R.id.nav_change:
                SharePreferanceUtil.clearAll(this);
                Intent intent = new Intent(this,BindingActivity.class);
                startActivity(intent);
                aboutFragment = null;
                finish();
                break;
            case R.id.nav_exit:
                exitApplication();
                break;
        }
        drawerLayout.closeDrawers();
        return true;
    }

    /**
     * setDrawer’s name
     */
    private void setDrawer(){
        text.setText(name);
    }

    /**
     * exit press
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void exit() {
        if(!isExit){
            isExit = true;
            // 结束上拉加载...
            Toast.makeText(MainActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
            myhandler.sendEmptyMessageDelayed(0,2000);
        }else{
            finish();
            System.exit(0);
        }
    }


    Handler myhandler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            isExit = false;
        }
    };

    /**
     * create option menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }
}
