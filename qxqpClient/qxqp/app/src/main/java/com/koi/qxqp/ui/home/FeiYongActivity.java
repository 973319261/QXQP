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
import com.koi.qxqp.adapter.RvFeiYongAdapter;
import com.koi.qxqp.bean.PreOtherCostDetailVo;
import com.koi.qxqp.dialog.FeiYongDialog;
import com.koi.qxqp.util.RecycleItemTouchHelper;
import com.koi.qxqp.util.StatusBarUtil;
import com.koi.qxqp.widget.MyActionBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FeiYongActivity extends AppCompatActivity {
    private Activity myActivity;//上下文
    private MyApplication myApplication;//全局变量
    private RvFeiYongAdapter rvFeiYongAdapter;//适配器
    private RecyclerView rvFeiYongList;//列表
    private TextView tvNum;//项目数
    private LinearLayout llAdd;//添加
    private LinearLayout llEmpty;//空图片
    List<PreOtherCostDetailVo> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myActivity=this;
        myApplication= (MyApplication) myActivity.getApplication();
        setContentView(R.layout.activity_home_feiyong);
        rvFeiYongList = findViewById(R.id.rv_home_feiyong_list);
        tvNum=findViewById(R.id.tv_home_feiyong_num);
        llAdd=findViewById(R.id.ll_home_feiyong_add);
        llEmpty=findViewById(R.id.ll_home_feiyong_empty);
        initView();//初始化
        setViewEventListener();//监听事件
        //======自定义myActionBar
        MyActionBar myActionBar=findViewById(R.id.myActionBar);
        myActionBar.setData("其他费用", R.drawable.ic_custom_actionbar_left_black, "", 1, 0, new MyActionBar.ActionBarClickListener() {
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
        rvFeiYongList.setLayoutManager(layoutManager);
        //==2、实例化适配器
        //=2.1、初始化适配器
        List<PreOtherCostDetailVo> otherCostDetailVoList=new ArrayList<>();
        rvFeiYongAdapter=new RvFeiYongAdapter(myActivity,otherCostDetailVoList);
        rvFeiYongList.setAdapter(rvFeiYongAdapter);
        ItemTouchHelper.Callback callback=new RecycleItemTouchHelper(rvFeiYongAdapter);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rvFeiYongList);
        //==4、设置增加或删除条目的动画
        rvFeiYongList.setItemAnimator(new DefaultItemAnimator());
        list=myApplication.getOtherCostDetailVoList();
        rvFeiYongAdapter.addItem(list,true);//整体刷新（回填）
        if (list.size()>0){
            llEmpty.setVisibility(View.GONE);
        }else {
            llEmpty.setVisibility(View.VISIBLE);
        }
        tvNum.setText(String.format(Locale.getDefault(),"%d个其他费用",list.size()));
        rvFeiYongAdapter.setViewHint(tvNum,llEmpty);//设置页面控件提示
    }

    /**
     * 监听事件
     */
    private void setViewEventListener() {
        //添加
        llAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeiYongDialog feiYongDialog=new FeiYongDialog(myActivity, null, new FeiYongDialog.OnSaveClickListener() {
                    @Override
                    public void onSaveClick(Dialog dialog, PreOtherCostDetailVo preOtherCostDetailVo) {
                        List<PreOtherCostDetailVo> otherCostDetailVoList=new ArrayList<>();
                        otherCostDetailVoList.add(preOtherCostDetailVo);
                        list.add(preOtherCostDetailVo);
                        rvFeiYongAdapter.addItem(otherCostDetailVoList,false);
                        tvNum.setText(String.format(Locale.getDefault(),"%d个其他费用",list.size()));
                        llEmpty.setVisibility(View.GONE);
                        Toast.makeText(myActivity,"添加成功",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                feiYongDialog.show(getSupportFragmentManager(),"");
            }
        });
        //修改
        rvFeiYongAdapter.setOnItemClickListener(new RvFeiYongAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PreOtherCostDetailVo data, final Integer position) {
                FeiYongDialog feiYongDialog=new FeiYongDialog(myActivity, data , new FeiYongDialog.OnSaveClickListener() {
                    @Override
                    public void onSaveClick(Dialog dialog, PreOtherCostDetailVo preOtherCostDetailVo) {
                        rvFeiYongAdapter.modifyItem(preOtherCostDetailVo,position);
                        Toast.makeText(myActivity,"修改成功",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                feiYongDialog.show(getSupportFragmentManager(),"");
            }
        });
    }
}
