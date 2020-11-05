package com.koi.qxqp.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.koi.qxqp.MyApplication;
import com.koi.qxqp.R;
import com.koi.qxqp.bean.AppendOptionVo;
import com.koi.qxqp.bean.MaintenanceCusVo;
import com.koi.qxqp.ui.home.HomeFragment;
import com.koi.qxqp.ui.order.OrderFragment;
import com.koi.qxqp.ui.user.UserFragment;
import com.koi.qxqp.util.OkHttpTool;
import com.koi.qxqp.util.SPUtils;
import com.koi.qxqp.util.ServiceUrls;
import com.koi.qxqp.util.StatusBarUtil;
import com.koi.qxqp.util.Tools;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import me.leefeng.promptlibrary.PromptDialog;

public class MainActivity extends AppCompatActivity {
    private Activity myActivity;//上下文
    private MyApplication myApplication;
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    Map<String,List<AppendOptionVo>> map=null;//获取后台返回的集合
    private Fragment[] fragments;//存放Fragment
    private HomeFragment homeFragment;//预约
    private OrderFragment orderFragment;//订单
    private UserFragment userFragment;//我的
    private BottomNavigationView navigationView;//底部导航栏
    private int lastfragment=0;//记录隐藏fragment的索引
    PromptDialog promptDialog;//提示框
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myActivity=this;//设置上下文
        myApplication= (MyApplication) getApplication();
        //获取控件
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.nv_main_navigation);//导航栏
        promptDialog=new PromptDialog(myActivity);
        //实例化
        homeFragment = new HomeFragment();
        orderFragment = new OrderFragment();
        userFragment = new UserFragment();
        fragments = new Fragment[]{homeFragment,orderFragment,userFragment};//实例Fragment
        //fragment管理器
        transaction = getSupportFragmentManager().beginTransaction();
        initView();//初始化页面
        setViewListener();//设置监听事件
    }

    /*页面初始化*/
    private void initView() {
        StatusBarUtil.setStatusBar(myActivity, true);//设置当前界面是否是全屏模式（状态栏）
        StatusBarUtil.setStatusBarLightMode(myActivity, true);//状态栏文字颜色
        //获取是否已经登录过--如果已经登录就自动登录
        String phone = (String) SPUtils.get(myActivity, ServiceUrls.SP_PHONE, "");
        String password = (String) SPUtils.get(myActivity, ServiceUrls.SP_PASSWORD, "");
        if (Tools.isNotNull(phone)) {
            promptDialog.showLoading("正在登录...");
            String url= ServiceUrls.getClientMethodUrl("login");
            Map<String,Object> map=new HashMap<>();
            map.put("phone",phone);
            map.put("password",password);
            OkHttpTool.httpGet(url, map, new OkHttpTool.ResponseCallback() {
                @Override
                public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {
                    myActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String text="无法连接服务器，请稍后重试";
                            if (isSuccess && responseCode==200){
                                try {
                                    JSONObject jsonObject=new JSONObject(response);
                                    text=jsonObject.getString("text");
                                    int code=jsonObject.getInt("code");
                                    if (code==200){
                                        String strData=jsonObject.getString("data");
                                        MaintenanceCusVo sysMaintenanceCus=gson.fromJson(strData, MaintenanceCusVo.class);
                                        if (sysMaintenanceCus!=null){
                                            myApplication.setLoginMaintenanceCus(sysMaintenanceCus);
                                            myApplication.setLoginActivity(myActivity);
                                            promptDialog.showSuccess(text);
                                            choiceList();//查询单选列表
                                        }
                                    }else {
                                        promptDialog.showError(text);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }else {
                                //设置fragment到布局
                                loginListener();
                                Toast.makeText(myActivity,text,Toast.LENGTH_LONG).show();
                            }
                            promptDialog.dismiss();//
                            promptDialog.setViewAnimDuration(500);//动画时间
                            promptDialog.dismissImmediately();
                        }
                    });
                }
            });
        }else{
            Intent intent=new Intent(myActivity,LoginActivity.class);
            startActivity(intent);
        }
    }
    /**
     * 查询单选列表
     */
    private void choiceList() {
        String url=ServiceUrls.getClientMethodUrl("choiceList");
        OkHttpTool.httpPost(url, null, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {
                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isSuccess && responseCode==200){
                            Type type = new TypeToken<Map<String, List<AppendOptionVo>>>() {}.getType();
                            map=gson.fromJson(response,type);
                            if (map!=null){
                                myApplication.setAppendOptionVoMap(map);//把下拉列表保存到全局变量
                            }
                            //设置fragment到布局
                            transaction.replace(R.id.fl_main_fragment, homeFragment).show(homeFragment).commit();//首页
                        }
                    }
                });
            }
        });
    }
    /*监听事件*/
    private void setViewListener() {
        //导航栏点击事件
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){//获取当前点击的菜单id
                    case R.id.navigation_home:
                        if (lastfragment != 0) {
                            switchFragment(lastfragment, 0);
                            lastfragment = 0;
                        }
                        return true;
                    case R.id.navigation_order:
                       if (myApplication.isLogin()){
                           if (lastfragment != 1) {
                               switchFragment(lastfragment, 1);
                               lastfragment = 1;
                           }
                       }else {
                           Toast.makeText(myActivity,"需要登录才能查看哦",Toast.LENGTH_LONG).show();
                       }
                        return true;
                    case R.id.navigation_user:
                        if (lastfragment != 2) {
                            switchFragment(lastfragment, 2);
                            lastfragment = 2;
                        }
                        return true;
                }
                return false;
            }
        });
    }
    //监听登录
    private void loginListener(){
       Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!myApplication.isLogin()) {
                            //获取是否已经登录过--如果已经登录就自动登录
                            String phone = (String) SPUtils.get(myActivity, ServiceUrls.SP_PHONE, "");
                            String password = (String) SPUtils.get(myActivity, ServiceUrls.SP_PASSWORD, "");
                            if (Tools.isNotNull(phone)) {
                                String url = ServiceUrls.getClientMethodUrl("login");
                                Map<String, Object> map = new HashMap<>();
                                map.put("phone", phone);
                                map.put("password", password);
                                OkHttpTool.httpGet(url, map, new OkHttpTool.ResponseCallback() {
                                    @Override
                                    public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {
                                        myActivity.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                String text = "无法连接服务器，请稍后重试";
                                                if (isSuccess && responseCode == 200) {
                                                    try {
                                                        JSONObject jsonObject = new JSONObject(response);
                                                        text = jsonObject.getString("text");
                                                        int code = jsonObject.getInt("code");
                                                        if (code == 200) {
                                                            String strData = jsonObject.getString("data");
                                                            MaintenanceCusVo sysMaintenanceCus = gson.fromJson(strData, MaintenanceCusVo.class);
                                                            if (sysMaintenanceCus != null) {
                                                                myApplication.setLoginMaintenanceCus(sysMaintenanceCus);
                                                                myApplication.setLoginActivity(myActivity);
                                                                choiceList();//查询单选列表
                                                                //设置fragment到布局
                                                                transaction.replace(R.id.fl_main_fragment, homeFragment).show(homeFragment).commit();//首页
                                                            }
                                                        }
                                                        Toast.makeText(myActivity, text, Toast.LENGTH_LONG).show();
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                } else {
                                                    //设置fragment到布局
                                                    Toast.makeText(myActivity, text, Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    }
                });
            }
        },0,10000);//每隔一秒使用handler发送一下消息,也就是每隔一秒执行一次,一直重复执行
    }
    /**
     *切换fragment
     */
    private void switchFragment(int lastfragment, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //隐藏上个Fragment
        transaction.hide(fragments[lastfragment]);
        if (fragments[index].isAdded() == false) {
            transaction.add(R.id.fl_main_fragment, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }


    /**
     * 双击返回键退出
     */
    private boolean isExit;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit) {
                this.finish();

            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                isExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit= false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
