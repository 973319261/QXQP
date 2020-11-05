package com.koi.qxqp.ui.home;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koi.qxqp.MyApplication;
import com.koi.qxqp.R;
import com.koi.qxqp.adapter.RvWeiXiuAdapter;
import com.koi.qxqp.bean.PreRepairItemDetailVo;
import com.koi.qxqp.dialog.WeiXiuDialog;
import com.koi.qxqp.util.RecycleItemTouchHelper;
import com.koi.qxqp.util.StatusBarUtil;
import com.koi.qxqp.widget.MyActionBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WeiXiuActivity extends AppCompatActivity {
    private Activity myActivity;//上下文
    private MyApplication myApplication;//全局变量
    private RvWeiXiuAdapter rvWeiXiuAdapter;//适配器
    private RecyclerView rvWeiXiuList;//列表
    private TextView tvNum;//项目数
    private LinearLayout llAdd;//添加
    private LinearLayout llEmpty;//空图片
    List<PreRepairItemDetailVo> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myActivity=this;
        myApplication= (MyApplication) myActivity.getApplication();
        setContentView(R.layout.activity_home_weixiu);
        rvWeiXiuList = findViewById(R.id.rv_home_weixiu_list);
        tvNum=findViewById(R.id.tv_home_weixiu_num);
        llAdd=findViewById(R.id.ll_home_weixiu_add);
        llEmpty=findViewById(R.id.ll_home_weixiu_empty);
        initView();//初始化
        setViewEventListener();//监听事件
        //======自定义myActionBar
        MyActionBar myActionBar=findViewById(R.id.myActionBar);
        myActionBar.setData("维修项目", R.drawable.ic_custom_actionbar_left_black, "", 1, 0, new MyActionBar.ActionBarClickListener() {
            @Override
            public void onLeftClick() {//返回
                finish();//关闭页面
            }

            @Override
            public void onRightClick() {
            }
        });
    }

    /**
     * 初始化
     */
    private void initView() {
        StatusBarUtil.setStatusBar(myActivity, true);//设置当前界面是否是全屏模式（状态栏）
        StatusBarUtil.setStatusBarLightMode(myActivity, true);//状态栏文字颜色
        //=1.1、创建布局管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(myActivity);
        //=1.2、设置为垂直排列，用setOrientation方法设置(默认为垂直布局)
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //=1.3、设置recyclerView的布局管理器
        rvWeiXiuList.setLayoutManager(layoutManager);
        //==2、实例化适配器
        //=2.1、初始化适配器
        List<PreRepairItemDetailVo> repairItemDetailVoList=new ArrayList<>();
        rvWeiXiuAdapter=new RvWeiXiuAdapter(myActivity,repairItemDetailVoList);
        rvWeiXiuList.setAdapter(rvWeiXiuAdapter);
        ItemTouchHelper.Callback callback=new RecycleItemTouchHelper(rvWeiXiuAdapter);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rvWeiXiuList);
        //==4、设置增加或删除条目的动画
        rvWeiXiuList.setItemAnimator(new DefaultItemAnimator());
        list=myApplication.getRepairItemDetailVoList();
        rvWeiXiuAdapter.addItem(list,true);//整体刷新（回填）
        if (list.size()>0){
            llEmpty.setVisibility(View.GONE);
        }else {
            llEmpty.setVisibility(View.VISIBLE);
        }
        tvNum.setText(String.format(Locale.getDefault(),"%d个维修项目",list.size()));
        rvWeiXiuAdapter.setViewHint(tvNum,llEmpty);//设置页面控件提示
    }

    /**
     * 监听事件
     */
    private void setViewEventListener() {
        //添加
        llAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeiXiuDialog weiXiuDialog=new WeiXiuDialog(myActivity, null, new WeiXiuDialog.OnSaveClickListener() {
                    @Override
                    public void onSaveClick(Dialog dialog, PreRepairItemDetailVo preRepairItemDetailVo) {
                        List<PreRepairItemDetailVo> repairItemDetailVoList=new ArrayList<>();
                        repairItemDetailVoList.add(preRepairItemDetailVo);
                        list.add(preRepairItemDetailVo);
                        rvWeiXiuAdapter.addItem(repairItemDetailVoList,false);
                        tvNum.setText(String.format(Locale.getDefault(),"%d个维修项目",list.size()));
                        llEmpty.setVisibility(View.GONE);
                        Toast.makeText(myActivity,"添加成功",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                weiXiuDialog.show(getSupportFragmentManager(),"");
            }
        });
        //修改
        rvWeiXiuAdapter.setOnItemClickListener(new RvWeiXiuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PreRepairItemDetailVo data, final Integer position) {
                WeiXiuDialog weiXiuDialog=new WeiXiuDialog(myActivity, data , new WeiXiuDialog.OnSaveClickListener() {
                    @Override
                    public void onSaveClick(Dialog dialog, PreRepairItemDetailVo preRepairItemDetailVo) {
                        rvWeiXiuAdapter.modifyItem(preRepairItemDetailVo,position);
                        Toast.makeText(myActivity,"修改成功",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                weiXiuDialog.show(getSupportFragmentManager(),"");
            }
        });
    }
}
