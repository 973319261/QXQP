package com.koi.qxqp.ui.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.koi.qxqp.MyApplication;
import com.koi.qxqp.R;
import com.koi.qxqp.adapter.RvPeiJianAdapter;
import com.koi.qxqp.bean.FittingsInfoVo;
import com.koi.qxqp.bean.Pagination;
import com.koi.qxqp.util.KeyBoardUtil;
import com.koi.qxqp.util.OkHttpTool;
import com.koi.qxqp.util.ServiceUrls;
import com.koi.qxqp.util.StatusBarUtil;
import com.koi.qxqp.widget.LoadingView;
import com.koi.qxqp.widget.MyActionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PeiJianActivity extends AppCompatActivity {
    private Activity myActivity;//上下文
    private MyApplication myApplication;//全局变量
    private Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    private RecyclerView rvPeiJianList;//配件类别
    private RvPeiJianAdapter rvPeiJianAdapter;//配件适配器
    private SmartRefreshLayout srlPeiJianList;//下拉加载，上拉刷新控件
    private LinearLayout llEmpty;//数据为空图片
    private LoadingView llLoadView;//加载中动画
    private LinearLayout llFailure;//加载失败页面
    private int pageSize = 5;//分页大小
    private int currentPage = 1;//当前页数
    private EditText etValue;//搜索框
    private ImageView ivSearch;//搜索
    private Boolean isToUser;//判断是否是从用户页进来的

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myActivity=this;
        myApplication= (MyApplication) myActivity.getApplication();
        setContentView(R.layout.activity_peijian);
        rvPeiJianList=findViewById(R.id.rv_peijian_list);
        srlPeiJianList=findViewById(R.id.srl_peijian_list);
        llFailure=findViewById(R.id.ll_failure);
        llLoadView = findViewById(R.id.loadView);
        llEmpty = findViewById(R.id.ll_order_list_empty);
        etValue=findViewById(R.id.et_peijian_value);
        ivSearch=findViewById(R.id.iv_peijian_search);
        initView();//初始化
        setViewEventListener();//监听事件
        //======自定义myActionBar
        MyActionBar myActionBar=findViewById(R.id.myActionBar);
        myActionBar.setData("配件信息", R.drawable.ic_custom_actionbar_left_black, "", 1, 0, new MyActionBar.ActionBarClickListener() {
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
        isToUser = getIntent().getBooleanExtra("isToUser",false);
        llLoadView.start();//开始加载动画
        //=1.1、创建布局管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(myActivity);
        //=1.2、设置为垂直排列，用setOrientation方法设置(默认为垂直布局)
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //=1.3、设置recyclerView的布局管理器
        rvPeiJianList.setLayoutManager(layoutManager);
        //==2、实例化适配器
        //=2.1、初始化适配器
        List<FittingsInfoVo> fittingsInfoVoList=new ArrayList<>();
        rvPeiJianAdapter=new RvPeiJianAdapter(myActivity,fittingsInfoVoList);
        rvPeiJianAdapter.setShowChekc(isToUser);
        rvPeiJianList.setAdapter(rvPeiJianAdapter);
        loadListData(true);

        srlPeiJianList.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadListData(true);
            }
        });
        srlPeiJianList.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadListData(false);
            }
        });
    }

    /**
     * 监听事件
     */
    private void setViewEventListener() {
        //搜索
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvPeiJianList.setVisibility(View.GONE);//隐藏列表
                llLoadView.setVisibility(View.VISIBLE);//隐藏加载动画
                llFailure.setVisibility(View.GONE);//加载失败
                llEmpty.setVisibility(View.GONE);//显示图片
                loadListData(true);//加载数据
                KeyBoardUtil.hideKeyboard(ivSearch);//隐藏键盘
            }
        });
        //点击软键盘中的搜索
        etValue.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //点击搜索的时候隐藏软键盘
                    rvPeiJianList.setVisibility(View.GONE);//隐藏列表
                    llLoadView.setVisibility(View.VISIBLE);//隐藏加载动画
                    llFailure.setVisibility(View.GONE);//加载失败
                    llEmpty.setVisibility(View.GONE);//显示图片
                    loadListData(true);//加载数据
                    KeyBoardUtil.hideKeyboard(ivSearch);//隐藏键盘
                    return true;
                }
                return false;
            }
        });
        //加载失败点击
        llFailure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvPeiJianList.setVisibility(View.GONE);//隐藏列表
                llLoadView.setVisibility(View.VISIBLE);//加载动画
                llFailure.setVisibility(View.GONE);//加载失败
                llEmpty.setVisibility(View.GONE);//显示图片
                loadListData(true);//初始化
            }
        });
        if (isToUser){
            //配件点击事件
            rvPeiJianAdapter.setOnItemClickListener(new RvPeiJianAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(FittingsInfoVo fittingsInfoVo) {
                    Intent intent=new Intent(myActivity,PeiJianInfoActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("fittingsInfoVo",fittingsInfoVo);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }else {
            //配件选择事件
            rvPeiJianAdapter.setOnItemCheckListener(new RvPeiJianAdapter.OnItemCheckListener() {
                @Override
                public void onItemCheck(FittingsInfoVo fittingsInfoVo) {
                    myApplication.setFittingsInfoVo(fittingsInfoVo);//保存到内存中
                    finish();
                }
            });
        }
    }

    /**
     * 加载类别数据
     * @param isRefresh
     */
    private void loadListData(final boolean isRefresh){
        if (isRefresh){
            currentPage=1;
        }else {
            currentPage++;
        }
        String url= ServiceUrls.getClientMethodUrl("getFittingsInfo");
        Map<String,Object> map=new HashMap<>();
        map.put("value",etValue.getText().toString());
        map.put("pageSize",pageSize);
        map.put("currentPage",currentPage);
        OkHttpTool.httpPost(url, map, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {
                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isSuccess && responseCode==200){
                            Pagination<FittingsInfoVo> pagination;
                            Type type=new TypeToken<Pagination<FittingsInfoVo>>(){}.getType();
                            pagination=gson.fromJson(response,type);
                            if (pagination.getTotalRows()!=0){
                                llLoadView.setVisibility(View.GONE);//隐藏加载动画
                                llEmpty.setVisibility(View.GONE);//有数据时隐藏图片
                                rvPeiJianList.setVisibility(View.VISIBLE);//显示列表
                                llFailure.setVisibility(View.GONE);//加载失败
                                List<FittingsInfoVo> list=pagination.getData();
                                rvPeiJianAdapter.addItem(list,currentPage);
                                if (isRefresh) {
                                    srlPeiJianList.finishRefresh();//刷新完成
                                } else {
                                    srlPeiJianList.finishLoadMore();//加载更多完成
                                }
                                int totalPage=pagination.getTotalPage();
                                if (currentPage==totalPage){
                                    srlPeiJianList.finishLoadMoreWithNoMoreData();
                                }
                                Toast.makeText(myActivity,String.format(Locale.getDefault(),"更新了%d条数据",list.size()),Toast.LENGTH_LONG).show();
                            }else {
                                rvPeiJianList.setVisibility(View.GONE);//隐藏列表
                                llLoadView.setVisibility(View.GONE);//隐藏加载动画
                                llFailure.setVisibility(View.GONE);//加载失败
                                llEmpty.setVisibility(View.VISIBLE);//显示图片
                                //加载失败
                                if (isRefresh) {
                                    srlPeiJianList.finishRefresh();//刷新失败
                                } else {
                                    srlPeiJianList.finishLoadMore();//加载更多失败
                                }
                                Toast.makeText(myActivity,"暂无数据",Toast.LENGTH_LONG).show();
                            }
                        }else {
                            rvPeiJianList.setVisibility(View.GONE);//隐藏列表
                            llLoadView.setVisibility(View.GONE);//隐藏加载动画
                            llFailure.setVisibility(View.VISIBLE);//加载失败
                            llEmpty.setVisibility(View.GONE);//显示图片
                            //加载失败
                            if (isRefresh) {
                                srlPeiJianList.finishRefresh(false);//刷新失败
                            } else {
                                srlPeiJianList.finishLoadMore(false);//加载更多失败
                            }
                        }
                    }
                });
            }
        });
    }
}
