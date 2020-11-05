package com.koi.qxqp.ui.user;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.koi.qxqp.MyApplication;
import com.koi.qxqp.R;
import com.koi.qxqp.bean.MaintenanceCusVo;
import com.koi.qxqp.dialog.CommomDialog;
import com.koi.qxqp.ui.LoginActivity;

public class UserFragment extends Fragment {
    private Activity myActivity;//上下文
    private MyApplication myApplication;
    private ImageView ivHeaderImage;//图片 头像
    private ImageView ivBackImage;//图片 背景图片
    private TextView tvName; //用戶名
    private Button btnLogout; //退出
    private MaintenanceCusVo sysMaintenanceCus;//用户信息
    private LinearLayout llModify;//修改资料
    private LinearLayout llPeiJian;//配件信息
    private LinearLayout llAbout;//关于

    private RequestOptions headerRO = new RequestOptions().circleCrop();//圆角变换
    private RequestOptions backgroundRO = new RequestOptions().centerCrop();//会缩放图片让图片充满整个ImageView的边框,然后裁掉超出的部分
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity= (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user,container,false);
        myApplication= (MyApplication) myActivity.getApplication();
        ivHeaderImage = view.findViewById(R.id.iv_user_header_image);
        ivBackImage = view.findViewById(R.id.iv_user_back_image);
        tvName = view.findViewById(R.id.tv_user_name);//用戶名
        llModify = view.findViewById(R.id.ll_user_modify);//修改资料
        llPeiJian=view.findViewById(R.id.ll_user_peijian);//配件信息
        llAbout = view.findViewById(R.id.ll_user_about);//关于
        btnLogout = view.findViewById(R.id.btn_user_logout); //退出
        initView();
        setViewEventListener();//监听事件
        return view;
    }
    /**
     * 初始化页面
     */
    private void initView() {
        sysMaintenanceCus=myApplication.getLoginMaintenanceCus();
        if(myApplication.isLogin()){
            tvName.setText(sysMaintenanceCus.getOwner().equals("")?"未设置":sysMaintenanceCus.getOwner());
        }
    //使用Glide加载头像信息
        //设置头像加载失败时的默认头像
        Glide.with(this)
                .load(R.drawable.bg_login)
                .apply(headerRO.error(R.drawable.bg_login))
                .into(ivHeaderImage);
        Glide.with(this)
                .load(R.drawable.bg_login)
                .apply(backgroundRO)
                .into(ivBackImage);
    }
    /**
     * 监听事件
     */
    private void setViewEventListener() {
        ivHeaderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(myActivity,ModifyActivity.class);
                startActivity(intent);
            }
        });
        //修改资料
        llModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(myActivity,ModifyActivity.class);
                startActivity(intent);
            }
        });
        llPeiJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(myActivity, PeiJianActivity.class);
                intent.putExtra("isToUser",true);//判断是否是从用户页进来的
                startActivity(intent);
            }
        });
        //关于
        llAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(myActivity,AboutActivity.class);
                startActivity(intent);
            }
        });
        //退出登录按钮
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommomDialog commomDialog= new CommomDialog(myActivity, R.style.dialog, "确定退出登录吗?", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm==true){//确定按钮
                            dialog.dismiss();
                            Intent intent=new Intent(myActivity, LoginActivity.class);
                            startActivity(intent);
                            myApplication.closeLoginActivity();
                            Toast.makeText(myActivity,"退出成功",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                commomDialog.setTitle("退出登录");
                commomDialog.show();//显示弹出框
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //刷新用户页面
        initView();
    }
}
