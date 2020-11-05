package com.koi.qxqp.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koi.qxqp.MyApplication;
import com.koi.qxqp.R;
import com.koi.qxqp.bean.MaintenanceCusVo;
import com.koi.qxqp.util.OkHttpTool;
import com.koi.qxqp.util.SPUtils;
import com.koi.qxqp.util.ServiceUrls;
import com.koi.qxqp.util.StatusBarUtil;
import com.koi.qxqp.util.Tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import me.leefeng.promptlibrary.PromptDialog;

public class LoginActivity extends AppCompatActivity {
    private Activity myActivity;//上下文
    private MyApplication myApplication;
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private TextView title;//标题
    private EditText etPhone;//手机号
    private EditText etPassword;//密码
    private EditText etIdCard;//身份证
    private EditText etVerification;//验证码
    private TextView tvGetCode;//获取验证码
    private Button btnLogin;//登录
    private Button btnRegister;//验证登录（注册）
    private TextView tvRegister;//账号注册
    private TextView tvLogin;//密码登录
    private LinearLayout llGetCode;//获取验证码
    private LinearLayout llPassword;//密码
    private LinearLayout llIdCard;//身份证
    private LinearLayout llVerification;//验证码
    PromptDialog promptDialog;//提示框

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myActivity=this;
        myApplication = (MyApplication) getApplication();
        setContentView(R.layout.activity_login);
        title=findViewById(R.id.tv_login_title);
        etPhone = findViewById(R.id.et_login_phone);
        etPassword = findViewById(R.id.et_login_password);
        etIdCard = findViewById(R.id.et_login_idCard);
        etVerification = findViewById(R.id.et_login_verification);
        tvGetCode=findViewById(R.id.tv_login_getCode);
        btnLogin = findViewById(R.id.btn_login_login);
        btnRegister = findViewById(R.id.btn_login_register);
        tvRegister = findViewById(R.id.tv_login_register);
        tvLogin = findViewById(R.id.tv_login_login);
        llGetCode=findViewById(R.id.ll_login_getCode);
        llPassword=findViewById(R.id.ll_login_password);
        llIdCard=findViewById(R.id.ll_login_idCard);
        llVerification=findViewById(R.id.ll_login_verification);
        initView();//初始化页面
        setContextListener();//设置监听事件
    }

    /**
     * 设置监听事件
     */
    private void setContextListener() {
        //登录
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (!Tools.isFastClick()){//判断是否连续点击
                   String phone= Tools.toString(etPhone.getText().toString());
                   String password=Tools.toString(etPassword.getText().toString());
                   if (!Tools.isMobile(phone)) {
                       Toast.makeText(myActivity,"请输入正确的手机号",Toast.LENGTH_LONG).show();
                       return;
                   }
                   if (!Tools.isNotNull(password)){
                       Toast.makeText(myActivity,"请输入密码",Toast.LENGTH_LONG).show();
                       return;
                   }
                   promptDialog=new PromptDialog(myActivity);
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
                                               MaintenanceCusVo maintenanceCus=gson.fromJson(strData,MaintenanceCusVo.class);
                                               if (maintenanceCus!=null){
                                                   String phone=maintenanceCus.getMobilePhone().trim();//手机
                                                   String password=maintenanceCus.getIdNumber().trim().substring(12,18);//密码
                                                   //将用户信息保存到Application（内存）
                                                   myApplication.setLoginMaintenanceCus(maintenanceCus);
                                                   //将phone和Password存入SharedPreferences
                                                   //！！！！*实际项目中避免这样做做，安全性不高
                                                   SPUtils.put(myActivity, ServiceUrls.SP_PHONE, phone);
                                                   SPUtils.put(myActivity, ServiceUrls.SP_PASSWORD, password);
                                                   Intent intent=new Intent(myActivity,MainActivity.class);
                                                   startActivity(intent);
                                                   finish();
                                                   promptDialog.showSuccess(text);

                                               }
                                           }else {
                                               promptDialog.showError(text);
                                           }
                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }
                                   }else {
                                       Toast.makeText(myActivity,text,Toast.LENGTH_LONG).show();
                                   }
                                   promptDialog.dismiss();//
                                   promptDialog.setViewAnimDuration(1000);//动画时间
                                   promptDialog.dismissImmediately();
                               }
                           });
                       }
                   });
               }
            }
        });
        //验证登录
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Tools.isFastClick()){//判断是否连续点击
                    String phone=Tools.toString(etPhone.getText().toString());
                    String idCard=Tools.toString(etIdCard.getText().toString());
                    String verification=Tools.toString(etVerification.getText().toString());
                    if (!Tools.isMobile(phone)) {
                        Toast.makeText(myActivity,"请输入正确的手机号",Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (!Tools.isIdCard(idCard)){
                        Toast.makeText(myActivity,"请输入正确的身份证号",Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (!Tools.isNotNull(verification)){
                        Toast.makeText(myActivity,"请输入验证码",Toast.LENGTH_LONG).show();
                        return;
                    }
                    promptDialog=new PromptDialog(myActivity);
                    promptDialog.showLoading("正在登录...");
                    String url=ServiceUrls.getClientMethodUrl("register");
                    MaintenanceCusVo maintenanceCusVo=new MaintenanceCusVo();
                    maintenanceCusVo.setMobilePhone(phone);
                    maintenanceCusVo.setIdNumber(idCard);
                    Map<String,Object> map=new HashMap<>();
                    map.put("maintenanceCusStr",gson.toJson(maintenanceCusVo));
                    map.put("smsValidCode",verification);
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
                                                MaintenanceCusVo maintenanceCus=gson.fromJson(strData,MaintenanceCusVo.class);
                                                if (maintenanceCus!=null){
                                                    String phone=maintenanceCus.getMobilePhone().trim();//手机
                                                    String password=maintenanceCus.getIdNumber().trim().substring(12,18);//密码
                                                    //将用户信息保存到Application（内存）
                                                    myApplication.setLoginMaintenanceCus(maintenanceCus);
                                                    //将phone和Password存入SharedPreferences
                                                    //！！！！*实际项目中避免这样做做，安全性不高
                                                    SPUtils.put(myActivity, ServiceUrls.SP_PHONE, phone);
                                                    SPUtils.put(myActivity, ServiceUrls.SP_PASSWORD, password);
                                                    Intent intent=new Intent(myActivity,MainActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                    promptDialog.showSuccess(text);

                                                }
                                            }else {
                                                promptDialog.showError(text);
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }else {
                                        Toast.makeText(myActivity,text,Toast.LENGTH_LONG).show();
                                    }
                                    promptDialog.dismiss();//
                                    promptDialog.setViewAnimDuration(1000);//动画时间
                                    promptDialog.dismissImmediately();
                                }
                            });
                        }
                    });
                }
            }
        });
        //获取验证码
        tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=Tools.toString(etPhone.getText().toString());
                if (!Tools.isMobile(phone)) {
                    Toast.makeText(myActivity,"请输入正确的手机号",Toast.LENGTH_LONG).show();
                    return;
                }
                String url=ServiceUrls.getClientMethodUrl("sendSmsValid");
                Map<String,Object> map=new HashMap<>();
                map.put("phone",phone);
                OkHttpTool.httpPost(url, map, new OkHttpTool.ResponseCallback() {
                    @Override
                    public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {
                        myActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (isSuccess && responseCode==200){
                                    try {
                                        JSONObject jsonObject=new JSONObject(response);
                                        int code=jsonObject.getInt("code");
                                        if (code == 200) {
                                            //发送成功
                                            Toast.makeText(myActivity, "验证码发送成功，请注意查收", Toast.LENGTH_LONG).show();
                                            CountDownTimer timer=new CountDownTimer(60*1000,1000) {
                                                @Override
                                                public void onTick(long millisUntilFinished) {
                                                    tvGetCode.setEnabled(false);//禁止
                                                    etPhone.setEnabled(false);
                                                    tvGetCode.setTextColor(Color.parseColor("#cccccc"));
                                                    tvGetCode.setText(String.format(Locale.getDefault(),"%ds秒后重试",(millisUntilFinished/1000)));
                                                }

                                                @Override
                                                public void onFinish() {
                                                    tvGetCode.setEnabled(true);//开启
                                                    etPhone.setEnabled(true);
                                                    tvGetCode.setTextColor(Color.parseColor("#fb7299"));
                                                    tvGetCode.setText("重新获取");
                                                }
                                            };
                                            //启动定时器
                                            timer.start();
                                        } else {
                                            //发送失败 json.text中存放发送失败原因
                                            String text = jsonObject.getString("text");
                                            Toast.makeText(myActivity, text, Toast.LENGTH_LONG).show();
                                        }
                                        return;
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }else {
                                    Toast.makeText(myActivity,"验证码发送失败，请稍后再试",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });
            }
        });
        //账号注册
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("汽修汽配注册");
                llGetCode.setVisibility(View.VISIBLE);
                llPassword.setVisibility(View.GONE);
                llIdCard.setVisibility(View.VISIBLE);
                llVerification.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.GONE);
                btnRegister.setVisibility(View.VISIBLE);
                tvLogin.setVisibility(View.VISIBLE);
                tvRegister.setVisibility(View.GONE);
            }
        });
        //密码登录
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("汽修汽配登录");
                llGetCode.setVisibility(View.GONE);
                llPassword.setVisibility(View.VISIBLE);
                llIdCard.setVisibility(View.GONE);
                llVerification.setVisibility(View.GONE);
                btnLogin.setVisibility(View.VISIBLE);
                btnRegister.setVisibility(View.GONE);
                tvLogin.setVisibility(View.GONE);
                tvRegister.setVisibility(View.VISIBLE);
            }
        });
        //手机号
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0 && etPassword.getText().length()>0){//手机号和密码不为空
                    btnLogin.setEnabled(true);//开启按钮
                }else {
                    btnLogin.setEnabled(false);//禁止按钮
                }
                if (s.length()>0 && etIdCard.getText().length()>0 && etVerification.getText().length()>0) {//手机号、身份证和验证码不为空
                    btnRegister.setEnabled(true);
                }else {
                    btnRegister.setEnabled(false);
                }
                if (s.length()>0){
                    tvGetCode.setTextColor(Color.parseColor("#fb7299"));
                    tvGetCode.setEnabled(true);//开启按钮
                }else {
                    tvGetCode.setTextColor(Color.parseColor("#cccccc"));
                    tvGetCode.setEnabled(false);//禁止按钮
                }
            }
        });
        //密码
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //手机号和密码不为空
                if (s.length()>0 && etPhone.getText().length()>0){
                    btnLogin.setEnabled(true);//开启按钮
                }else {
                    btnLogin.setEnabled(false);//禁止按钮
                }
            }
        });
        //身份证
        etIdCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //手机号、身份证和验证码不为空
                if (s.length()>0 && etPhone.getText().length()>0 && etVerification.getText().length()>0) {
                    btnRegister.setEnabled(true);
                }else {
                    btnRegister.setEnabled(false);
                }
            }
        });

        //验证码
        etVerification.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //手机号、身份证和验证码不为空
                if (s.length()>0 && etIdCard.getText().length()>0 && etPhone.getText().length()>0) {
                    btnRegister.setEnabled(true);
                }else {
                    btnRegister.setEnabled(false);
                }
            }
        });
    }

    /**
     * 初始化页面
     */
    private void initView() {
        StatusBarUtil.setStatusBar(myActivity,true);//设置当前界面是否是全屏模式（状态栏）
        StatusBarUtil.setStatusBarLightMode(myActivity,true);//状态栏文字颜色
    }

    //记录用户首次点击返回键的时间
    private long firstTime=0;

    @Override
    public boolean onKeyUp(int keyCode,
                           KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                long secondTime=System.currentTimeMillis();
                if(secondTime-firstTime>2000){
                    Toast.makeText(myActivity,"再按一次退出",Toast.LENGTH_SHORT).show();
                    firstTime=secondTime;
                    return true;
                }else{
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
}
