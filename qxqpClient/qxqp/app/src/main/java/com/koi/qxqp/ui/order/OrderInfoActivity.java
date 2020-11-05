package com.koi.qxqp.ui.order;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bin.david.form.core.SmartTable;

import com.bin.david.form.data.style.FontStyle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.koi.qxqp.MyApplication;
import com.koi.qxqp.R;
import com.koi.qxqp.bean.OrderDetail;
import com.koi.qxqp.bean.PredateVo;
import com.koi.qxqp.dialog.CommomDialog;
import com.koi.qxqp.ui.LoginActivity;
import com.koi.qxqp.util.OkHttpTool;
import com.koi.qxqp.util.ServiceUrls;
import com.koi.qxqp.util.StatusBarUtil;
import com.koi.qxqp.util.Tools;
import com.koi.qxqp.widget.MyActionBar;
import com.mingle.widget.LoadingView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class OrderInfoActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private Activity myActivity;
    private MyApplication myApplication;
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Long predateId;//订单ID
    private OrderDetail listDetail;//接收后台返回的明细信息
    private LoadingView llLoadView;//加载中页面
    private LinearLayout llFailure;//加载失败页面
    private LinearLayout llEmpty;//数据为空图片
    private ScrollView svContent;//内容页面
    private TextView tvPredateNum; //订单号
    private TextView tvOwner;//车主
    private TextView tvPhone;//车主电话
    private TextView tvCarNum;//车牌
    private TextView tvVehicleType;//车型
    private TextView tvContacts;//联系人
    private TextView tvCarMasterPhone;//联系人电话
    private TextView tvCarderName;//接车人
    private TextView tvRepairName;//修理类别
    private TextView tvRemark;//备注
    private ImageView ivToAudit;//审核状态
    private TextView tvDescribe;//故障信息
    private TextView tvMaintainData;//维修日期
    private TextView tvOpenDate;//开单日期
    private TextView tvAmount;//总金额
    private TabHost tabhost;//切换卡
    private SmartTable tableWeiXiu;//维修项目
    private SmartTable tableChanPin;//产品材料
    private SmartTable tableFeiYong;//其他费用
    private LinearLayout llWeiXiuEmpty;
    private LinearLayout llChanPinEmpty;
    private LinearLayout llFeiYongEmpty;
    private Button btnDelete;//删除订单
    //控件
    private SmartRefreshLayout srlOrderInfo;//下拉加载，上拉刷新控件
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        myActivity=this;
        myApplication= (MyApplication) getApplication();
        llLoadView = findViewById(R.id.loadView);
        llFailure=findViewById(R.id.ll_failure);
        llEmpty = findViewById(R.id.ll_empty);
        svContent = findViewById(R.id.sv_order_content);
        tvPredateNum = findViewById(R.id.tv_order_info_predateNum);
        tvOwner = findViewById(R.id.tv_order_info_owner);
        tvPhone = findViewById(R.id.tv_order_info_phone);
        tvCarNum = findViewById(R.id.tv_order_info_carNum);
        tvVehicleType = findViewById(R.id.ll_order_info_vehicleType);
        tvContacts = findViewById(R.id.tv_order_info_contacts);
        tvCarMasterPhone = findViewById(R.id.tv_order_info_carMasterPhone);
        tvCarderName = findViewById(R.id.tv_order_info_carderName);
        tvRepairName = findViewById(R.id.tv_order_info_repairName);
        tvRemark = findViewById(R.id.tv_order_info_remark);
        ivToAudit = findViewById(R.id.tv_order_info_toAudit);
        tvDescribe = findViewById(R.id.tv_order_info_describe);
        tvMaintainData = findViewById(R.id.tv_order_info_maintainData);
        tvOpenDate = findViewById(R.id.tv_order_info_openDate);
        tvAmount=findViewById(R.id.tv_order_info_amount);
        tableWeiXiu = findViewById(R.id.table_weixiu);
        tableChanPin = findViewById(R.id.table_chanpin);
        tableFeiYong = findViewById(R.id.table_feiyong);
        llWeiXiuEmpty=findViewById(R.id.ll_order_weixiu_empty);
        llChanPinEmpty=findViewById(R.id.ll_order_chanpin_empty);
        llFeiYongEmpty=findViewById(R.id.ll_order_feiyong_empty);
        btnDelete=findViewById(R.id.btn_order_feiyong_delete);
        srlOrderInfo=findViewById(R.id.srl_order_info);
        //得到TabHost对象实例  切换卡
        tabhost =(TabHost) findViewById(R.id.tabhost);
        //调用 TabHost.setup()
        tabhost.setup();
        //创建Tab标签
        tabhost.addTab(tabhost.newTabSpec("one").setIndicator("维修项目").setContent(R.id.ll_order_weixiu));
        tabhost.addTab(tabhost.newTabSpec("two").setIndicator("产品材料").setContent(R.id.ll_order_chanpin));
        tabhost.addTab(tabhost.newTabSpec("three").setIndicator("其他费用").setContent(R.id.ll_order_feiyong));
        ((RadioGroup) findViewById(R.id.tab_radiogroup)).setOnCheckedChangeListener(this);
        //======自定义myActionBar
        MyActionBar myActionBar=findViewById(R.id.myActionBar);
        myActionBar.setData("订单详情", R.drawable.ic_custom_actionbar_left_black, "", 1, 0, new MyActionBar.ActionBarClickListener() {
            @Override
            public void onLeftClick() {//返回
                finish();//关闭页面
            }

            @Override
            public void onRightClick() {
            }
        });
        predateId=getIntent().getLongExtra("predateId",0);
        if (predateId>0){
            initView();//初始化
            setViewListener();//监听事件
        }else {
            Toast.makeText(myActivity,"数据异常，请稍后再试",Toast.LENGTH_LONG).show();
            finish();
        }
    }

    /**
     * 监听事件
     */
    private void setViewListener() {
        //加载失败点击
        llFailure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llLoadView.setVisibility(View.VISIBLE);//显示加载中动画
                llFailure.setVisibility(View.GONE);//隐藏加载失败动画
                svContent.setVisibility(View.GONE);//隐藏显示内容
                initView();//初始化
            }
        });
        //是否启用下拉刷新（默认启用）
        srlOrderInfo.setEnableRefresh(true);
        //改变上拉更多
        srlOrderInfo.setEnableLoadMore(false);
        //下拉监听
        srlOrderInfo.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initView();//初始化
            }
        });
        //删除订单事件
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CommomDialog commomDialog= new CommomDialog(myActivity, R.style.dialog, "确定删除当前订单吗?", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm==true){//确定按钮
                            dialog.dismiss();
                            final LoadingDialog loadingDialog=new LoadingDialog(myActivity);
                            loadingDialog.show();
                            String url=ServiceUrls.getClientMethodUrl("deletePredate");
                            Map<String,Object> map=new HashMap<>();
                            map.put("predateID",predateId);
                            OkHttpTool.httpPost(url, map, new OkHttpTool.ResponseCallback() {
                                @Override
                                public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {
                                    myActivity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (isSuccess && responseCode==200){
                                                if (Boolean.parseBoolean(response)){
                                                    Toast.makeText(myActivity,"订单删除成功",Toast.LENGTH_LONG).show();
                                                    svContent.setVisibility(View.GONE);//显示内容
                                                    llEmpty.setVisibility(View.VISIBLE);
                                                }else {
                                                    Toast.makeText(myActivity,"订单删除失败，请稍后再试",Toast.LENGTH_LONG).show();
                                                }
                                            }else {
                                                Toast.makeText(myActivity,"订单删除失败，请稍后再试",Toast.LENGTH_LONG).show();
                                            }
                                            loadingDialog.close();
                                        }
                                    });
                                }
                            });
                        }
                    }
                });
                commomDialog.setTitle("删除订单不能恢复哦");
                commomDialog.show();//显示弹出框
            }
        });
    }

    /**
     * 初始化
     */
    private void initView() {
        StatusBarUtil.setStatusBar(myActivity, true);//设置当前界面是否是全屏模式（状态栏）
        StatusBarUtil.setStatusBarLightMode(myActivity, true);//状态栏文字颜色
        myApplication.closeActivity();//改变从预订成功页面进来的页面
        //请求数据
        String url= ServiceUrls.getClientMethodUrl("getOrderDetail");
        Map<String, Object> map = new HashMap<>();
        map.put("predateId", predateId);
        OkHttpTool.httpGet(url, map, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {
                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isSuccess && responseCode==200){
                            Type type = new TypeToken<OrderDetail>() {}.getType();
                            listDetail=gson.fromJson(response,type);
                            PredateVo predateVo= listDetail.getPredateVo();
                            if (predateVo!=null){
                                //回填订单信息
                                tvPredateNum.setText(predateVo.getPredateNum().trim());//订单号
                                tvOwner.setText(predateVo.getOwner().trim());//车主
                                tvPhone.setText(predateVo.getTelephone().trim());//车主电话
                                tvCarNum.setText(predateVo.getCarNum().trim());//车牌
                                tvVehicleType.setText(predateVo.getVehicleType().trim());//车型
                                tvContacts.setText(predateVo.getContacts().trim());//联系人
                                tvCarMasterPhone.setText(predateVo.getCarMasterPhone().trim());//联系人电话
                                tvCarderName.setText(predateVo.getCarderName().trim());//接车人
                                tvRepairName.setText(predateVo.getRepairName().trim());//修理类别
                                tvRemark.setText(predateVo.getRemark().trim());//备注
                                if (predateVo.getToAudit()==true){//审核状态
                                    ivToAudit.setImageResource(R.drawable.ic_order_toaudit);
                                }else {
                                    ivToAudit.setImageResource(R.drawable.ic_order_tonoaudit);
                                }
                                tvDescribe.setText(predateVo.getDescribe().trim());//故障信息
                                tvMaintainData.setText(sf.format(predateVo.getMaintainData()));//维修日期
                                tvOpenDate.setText(sf.format(predateVo.getOpenDate()));//开单日期
                                tvAmount.setText(Tools.setMoneySize(String.format(Locale.getDefault(), "￥%.2f", predateVo.getDeserveMoney())));//总金额
                                //订单明细（表格）
                                if (listDetail.getPreRepairItemDetailVoList().size()!=0){
                                    tableWeiXiu.setData(listDetail.getPreRepairItemDetailVoList());//设置数据到维修项目
                                    tableWeiXiu.getConfig().setShowTableTitle (false).setShowXSequence(false).setShowYSequence(false)
                                            .setMinTableWidth(1900).setVerticalPadding(25).setColumnTitleVerticalPadding(25)
                                            .setColumnTitleStyle(new FontStyle(35,Color.BLACK));
                                }else {
                                    llWeiXiuEmpty.setVisibility(View.VISIBLE);
                                }
                                if (listDetail.getPreProductDetailVoList().size()!=0){
                                    tableChanPin.setData(listDetail.getPreProductDetailVoList());//设置数据到产品材料
                                    tableChanPin.getConfig().setShowTableTitle(false).setShowXSequence(false).setShowYSequence(false)
                                            .setMinTableWidth(1900).setVerticalPadding(25).setColumnTitleVerticalPadding(25)
                                            .setColumnTitleStyle(new FontStyle(35,Color.BLACK));
                                }else {
                                    llChanPinEmpty.setVisibility(View.VISIBLE);
                                }
                                if (listDetail.getPreOtherCostDetailVoList().size()!=0){
                                    tableFeiYong.setData(listDetail.getPreOtherCostDetailVoList());//设置数据到其他费用
                                    tableFeiYong.getConfig().setShowTableTitle (false).setShowXSequence(false).setShowYSequence(false)
                                            .setMinTableWidth(1400).setVerticalPadding(25).setColumnTitleVerticalPadding(25)
                                            .setColumnTitleStyle(new FontStyle(35,Color.BLACK));
                                }else {
                                    llFeiYongEmpty.setVisibility(View.VISIBLE);
                                }
                                llLoadView.setVisibility(View.GONE);//隐藏加载中动画
                                llFailure.setVisibility(View.GONE);//隐藏加载失败动画
                                svContent.setVisibility(View.VISIBLE);//显示内容
                                srlOrderInfo.finishRefresh();//刷新完成
                            }else {
                                llLoadView.setVisibility(View.GONE);//隐藏加载中动画
                                llFailure.setVisibility(View.GONE);//隐藏加载失败动画
                                svContent.setVisibility(View.GONE);//显示内容
                                llEmpty.setVisibility(View.VISIBLE);
                                srlOrderInfo.finishRefresh();//刷新完成
                            }
                        }else {
                            llLoadView.setVisibility(View.GONE);//隐藏加载中动画
                            llFailure.setVisibility(View.VISIBLE);//显示加载失败动画
                            svContent.setVisibility(View.GONE);//隐藏显示内容
                            srlOrderInfo.finishRefresh();//刷新失败
                        }
                    }
                });
            }
        });
    }

    /**
     * 监听切换卡
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_order_weixiu:
                tabhost.setCurrentTabByTag("one");
                break;
            case R.id.rb_order_chanpin:
                tabhost.setCurrentTabByTag("two");
                break;
            case R.id.rb_order_feiyong:
                tabhost.setCurrentTabByTag("three");
                break;
        }
    }
}
