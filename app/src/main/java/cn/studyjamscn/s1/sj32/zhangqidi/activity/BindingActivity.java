package cn.studyjamscn.s1.sj32.zhangqidi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import java.util.HashMap;

import cn.studyjamscn.s1.sj32.zhangqidi.utils.Conf;
import cn.studyjamscn.s1.sj32.zhangqidi.utils.SharePreferanceUtil;
import cn.studyjamscn.s1.sj32.zhangqidi.R;

/**
 * Created by AddiCheung on 2016/4/19 0019.
 */
public class BindingActivity extends BaseActivity {

    private Button btn_login;
    private Spinner spinner;
    private EditText editText;
    private ProgressBar progress;
    private String name;
    private String server;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_binding);
        initView();
        initEvent();
        HashMap<String, String> map = SharePreferanceUtil.getData(this, new String[]{"server", "name"});
        if (!map.isEmpty()) {
            server = map.get("server");
            name = map.get("name");
            switch2MainActivity();
        }
    }

    /**
     * init Views
     */
    private void initView() {
        spinner = (Spinner) findViewById(R.id.spn_bingding);
        editText = (EditText) findViewById(R.id.et_binding);
        btn_login = (Button) findViewById(R.id.btn_binding);
        progress = (ProgressBar) findViewById(R.id.pb_bind);
    }

    /**
     * setup event
     */
    private void initEvent() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] server_list = getResources().getStringArray(R.array.server_list);
                server = server_list[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Toast.makeText(BindingActivity.this, "请选择大区", Toast.LENGTH_SHORT).show();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editText.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(BindingActivity.this, "请输入玩家昵称", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (server.equals("选择大区")) {
                        Toast.makeText(BindingActivity.this, "请选择大区", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        progress.setVisibility(View.VISIBLE);
                        try {
                            Thread.currentThread().sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        bind(server, name);
                    }
                }
            }
        });
    }

    /**
     * test if the information is right
     */
    private void bind(final String server, String name) {
        HttpParams params = new HttpParams();
        final HashMap<String, String> map = new HashMap<>();
        params.put("serverName", server);
        params.put("playerName", name);
        map.put("server", server);
        map.put("name", name);
        RxVolley.get(Conf.USRR_INFO, params, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                Log.i("返回玩家信息---", t);
                if (t.equals("null")) {
                    progress.setVisibility(View.INVISIBLE);
                    Toast.makeText(BindingActivity.this, "没有找到玩家，请确认输入信息是否有误", Toast.LENGTH_SHORT).show();
                } else {
                    progress.setVisibility(View.INVISIBLE);
                    SharePreferanceUtil.clearAll(BindingActivity.this);
                    SharePreferanceUtil.setData(BindingActivity.this, map);
                    switch2MainActivity();

                }
            }
        });
    }

    /**
     * switch to MainActivity
     */
    private void switch2MainActivity() {
        BaseApplication.getInstance().setName(name);
        BaseApplication.getInstance().setServer(server);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
