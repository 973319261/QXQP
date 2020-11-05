package com.koi.qxqp.ui.order;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.koi.qxqp.MyApplication;
import com.koi.qxqp.R;
import com.koi.qxqp.adapter.RvOrderAdapter;
import com.koi.qxqp.bean.Pagination;
import com.koi.qxqp.bean.PredateVo;
import com.koi.qxqp.util.OkHttpTool;
import com.koi.qxqp.util.ServiceUrls;
import com.mingle.widget.LoadingView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderListFragment extends Fragment {
    private Activity myActivity;
    private MyApplication myApplication;
    private int toAudit;
    private String phone;
    private int pageSize = 7;//分页大小
    private int currentPage = 1;//当前页数
    private RvOrderAdapter rvOrderAdapter;
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    //控件
    private SmartRefreshLayout srlOrderList;//下拉加载，上拉刷新控件
    private RecyclerView rvOrderList;//列表
    private LinearLayout llEmpty;//数据为空图片
    private LoadingView llLoadView;//加载中动画
    private LinearLayout llFailure;//加载失败页面
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity= (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order_list,container,false);
         Bundle bundle=getArguments();
         if (bundle!=null){
             toAudit=bundle.getInt("toAudit");
             myApplication= (MyApplication) myActivity.getApplication();
             phone=myApplication.getLoginMaintenanceCus().getMobilePhone().trim();
             srlOrderList=view.findViewById(R.id.srl_order_list);
             llLoadView = view.findViewById(R.id.loadView);
             llFailure=view.findViewById(R.id.ll_failure);
             rvOrderList = view.findViewById(R.id.rv_order_list);
             llEmpty = view.findViewById(R.id.ll_order_list_empty);
             //============RecyclerView 初始化=========
             //===1、设置布局控制器
             //=1.1、创建布局管理器
             LinearLayoutManager layoutManager=new LinearLayoutManager(myActivity);
             //=1.2、设置为垂直排列，用setOrientation方法设置(默认为垂直布局)
             layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
             //=1.3、设置recyclerView的布局管理器
             rvOrderList.setLayoutManager(layoutManager);
             //==2、实例化适配器
             //=2.1、初始化适配器
             List<PredateVo> predateVoList=new ArrayList<>();
             rvOrderAdapter=new RvOrderAdapter(myActivity,predateVoList);
             //=2.2设置列表项点击事件
             rvOrderAdapter.setOnItemClickListener(new RvOrderAdapter.OnItemClickListener() {
                 @Override
                 public void onItemClick(View view, PredateVo data, int position) {
                     Intent intent=new Intent(myActivity,OrderInfoActivity.class);
                     intent.putExtra("predateId",data.getPredateId());
                     startActivity(intent);
                 }
             });
             //=2.3、设置recyclerView的适配器
             rvOrderList.setAdapter(rvOrderAdapter);
             //==4、设置增加或删除条目的动画
             rvOrderList.setItemAnimator(new DefaultItemAnimator());
             //==5、初始化加载数据
             loadListData(true);

             srlOrderList.setOnRefreshListener(new OnRefreshListener() {
                 @Override
                 public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                     loadListData(true);
                 }
             });
             srlOrderList.setOnLoadMoreListener(new OnLoadMoreListener() {
                 @Override
                 public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                     loadListData(false);
                 }
             });
             setViewListener();//监听事件
         }
        return view;
    }

    /**
     * 监听事件
     */
    private void setViewListener() {
        //加载失败点击
        llFailure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvOrderList.setVisibility(View.GONE);//隐藏列表
                llLoadView.setVisibility(View.VISIBLE);//隐藏加载动画
                llFailure.setVisibility(View.GONE);//加载失败
                llEmpty.setVisibility(View.GONE);//显示图片
                loadListData(true);//初始化
            }
        });
    }

    /**
     * 加载列表数据
     * @param isRefresh
     */
    private void loadListData(final boolean isRefresh) {
        if (isRefresh){
            currentPage=1;
        }else {
            currentPage++;
        }
        String url= ServiceUrls.getClientMethodUrl("getOrder");
        Map<String,Object> map=new HashMap<>();
        map.put("phone",phone);
        map.put("toAudit",toAudit);
        map.put("pageSize",pageSize);
        map.put("currentPage",currentPage);

        OkHttpTool.httpPost(url, map, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {
                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isSuccess && responseCode==200){
                            Pagination<PredateVo> pagination;
                            Type type=new TypeToken<Pagination<PredateVo>>(){}.getType();
                            pagination=gson.fromJson(response,type);
                            if (pagination.getTotalRows()!=0){
                                llLoadView.setVisibility(View.GONE);//隐藏加载动画
                                llEmpty.setVisibility(View.GONE);//有数据时隐藏图片
                                rvOrderList.setVisibility(View.VISIBLE);//显示列表
                                llFailure.setVisibility(View.GONE);//加载失败
                                List<PredateVo> list=pagination.getData();
                                rvOrderAdapter.addItem(list,currentPage);
                                if (isRefresh) {
                                    srlOrderList.finishRefresh();//刷新完成
                                } else {
                                    srlOrderList.finishLoadMore();//加载更多完成
                                }
                                int totalPage=pagination.getTotalPage();
                                if (currentPage==totalPage){
                                    srlOrderList.finishLoadMoreWithNoMoreData();
                                }
                            }else {
                                rvOrderList.setVisibility(View.GONE);//隐藏列表
                                llLoadView.setVisibility(View.GONE);//隐藏加载动画
                                llFailure.setVisibility(View.GONE);//加载失败
                                llEmpty.setVisibility(View.VISIBLE);//显示图片
                                //加载失败
                                if (isRefresh) {
                                    srlOrderList.finishRefresh();//刷新失败
                                } else {
                                    srlOrderList.finishLoadMore();//加载更多失败
                                }
                            }
                        }else {
                            rvOrderList.setVisibility(View.GONE);//隐藏列表
                            llLoadView.setVisibility(View.GONE);//隐藏加载动画
                            llFailure.setVisibility(View.VISIBLE);//加载失败
                            llEmpty.setVisibility(View.GONE);//显示图片
                            //加载失败
                            if (isRefresh) {
                                srlOrderList.finishRefresh(false);//刷新失败
                            } else {
                                srlOrderList.finishLoadMore(false);//加载更多失败
                            }
                        }
                    }
                });
            }
        });
    }
}
