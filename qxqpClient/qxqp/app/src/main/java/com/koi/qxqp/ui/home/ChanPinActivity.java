package com.koi.qxqp.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koi.qxqp.MyApplication;
import com.koi.qxqp.R;
import com.koi.qxqp.adapter.RvChanPinAdapter;
import com.koi.qxqp.bean.PreProductDetailVo;
import com.koi.qxqp.util.RecycleItemTouchHelper;
import com.koi.qxqp.util.StatusBarUtil;
import com.koi.qxqp.widget.MyActionBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 产品
 */
public class ChanPinActivity extends AppCompatActivity {
    private Activity myActivity;//上下文
    private MyApplication myApplication;//全局变量
    private RvChanPinAdapter rvChanPinAdapter;//适配器
    private RecyclerView rvChanPinList;//列表
    private TextView tvNum;//项目数
    private LinearLayout llAdd;//添加
    private LinearLayout llEmpty;//空图片
    List<PreProductDetailVo> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myActivity=this;
        myApplication= (MyApplication) myActivity.getApplication();
        setContentView(R.layout.activity_home_chanpin);
        rvChanPinList = findViewById(R.id.rv_home_chanpin_list);
        tvNum=findViewById(R.id.tv_home_chanpin_num);
        llAdd=findViewById(R.id.ll_home_chanpin_add);
        llEmpty=findViewById(R.id.ll_home_chanpin_empty);
        initView();//初始化
        setViewEventListener();//监听事件
        //======自定义myActionBar
        MyActionBar myActionBar=findViewById(R.id.myActionBar);
        myActionBar.setData("产品材料", R.drawable.ic_custom_actionbar_left_black, "", 1, 0, new MyActionBar.ActionBarClickListener() {
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
        rvChanPinList.setLayoutManager(layoutManager);
        //==2、实例化适配器
        //=2.1、初始化适配器
        List<PreProductDetailVo> productDetailVoList=new ArrayList<>();
        rvChanPinAdapter=new RvChanPinAdapter(myActivity,productDetailVoList);
        rvChanPinList.setAdapter(rvChanPinAdapter);
        ItemTouchHelper.Callback callback=new RecycleItemTouchHelper(rvChanPinAdapter);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rvChanPinList);
        //==4、设置增加或删除条目的动画
        rvChanPinList.setItemAnimator(new DefaultItemAnimator());
        list=myApplication.getProductDetailVoList();
        rvChanPinAdapter.addItem(list,true);//整体刷新（回填）
        if (list.size()>0){
            llEmpty.setVisibility(View.GONE);
        }else {
            llEmpty.setVisibility(View.VISIBLE);
        }
        tvNum.setText(String.format(Locale.getDefault(),"%d个产品材料",list.size()));
        rvChanPinAdapter.setViewHint(tvNum,llEmpty);//设置页面控件提示
    }

    /**
     * 事件监听
     */
    private void setViewEventListener() {
        //新增
        llAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(myActivity, ChanPinSaveActivity.class);
                startActivity(intent);
            }
        });
        //修改
        rvChanPinAdapter.setOnItemClickListener(new RvChanPinAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PreProductDetailVo data, Integer position) {
                Intent intent=new Intent(myActivity, ChanPinSaveActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("preProductDetailVo",data);//传递产品材料值
                bundle.putInt("position",position);//产品材料列表索引
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        list=myApplication.getProductDetailVoList();
        rvChanPinAdapter.addItem(list,true);//整体刷新（回填）
        if (list.size()>0){
            llEmpty.setVisibility(View.GONE);
        }else {
            llEmpty.setVisibility(View.VISIBLE);
        }
        tvNum.setText(String.format(Locale.getDefault(),"%d个产品材料",list.size()));
        rvChanPinAdapter.setViewHint(tvNum,llEmpty);//设置页面控件提示
    }
}
