package com.koi.qxqp.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.koi.qxqp.MyApplication;
import com.koi.qxqp.R;
import com.koi.qxqp.bean.PreOtherCostDetailVo;
import com.koi.qxqp.bean.PreProductDetailVo;
import com.koi.qxqp.bean.PreRepairItemDetailVo;
import com.koi.qxqp.bean.PredateVo;
import com.koi.qxqp.ui.order.OrderInfoActivity;
import com.koi.qxqp.util.StatusBarUtil;
import com.koi.qxqp.util.Tools;
import com.koi.qxqp.widget.MyActionBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class HomeResultActivity extends AppCompatActivity {
    private Activity myActivity;//上下文
    private MyApplication myApplication;//全局变量
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private PredateVo predateVo;//订单信息
    private List<PreRepairItemDetailVo> repairItemDetailVoList;//维修项目
    private List<PreProductDetailVo> productDetailVoList;//产品材料
    private List<PreOtherCostDetailVo> otherCostDetailVoList;//其他费用
    private LinearLayout llPredateNum;//订单信息
    private TextView tvPredateNum; //订单号
    private TextView tvOpenDate; //开单日期
    private TextView tvDescribe;//故障信息
    private TextView tvRemark;//备注
    private TextView tvWeiXiuNum;//维修项目数
    private TextView tvWeiXiuMoney;//维修项目金额
    private TextView tvChanPinNum;//产品材料数
    private TextView tvChanPinMoney;//产品材料金额
    private TextView tvFeiYongNum;//其他费用数
    private TextView tvFeiYongMoney;//其他费用金额
    private TextView tvDeduction;//抵扣金额
    private TextView tvMaintainData;//注意（维修日期）
    private TextView tvDeserveMoney;//合计

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myActivity=this;
        myApplication= (MyApplication) myActivity.getApplication();
        setContentView(R.layout.activity_home_result);
        llPredateNum=findViewById(R.id.ll_home_result_predateNum);
        tvPredateNum = findViewById(R.id.tv_home_result_predateNum);
        tvOpenDate = findViewById(R.id.tv_home_result_openDate);
        tvDescribe = findViewById(R.id.tv_home_result_describe);
        tvRemark = findViewById(R.id.tv_home_result_remark);
        tvWeiXiuNum = findViewById(R.id.tv_home_weixiu_num);
        tvWeiXiuMoney = findViewById(R.id.tv_home_weixiu_money);
        tvChanPinNum = findViewById(R.id.tv_home_chanpin_num);
        tvChanPinMoney = findViewById(R.id.tv_home_chanpin_money);
        tvFeiYongNum = findViewById(R.id.tv_home_feiyong_num);
        tvFeiYongMoney = findViewById(R.id.tv_home_feiyong_money);
        tvDeduction = findViewById(R.id.tv_home_deduction);
        tvMaintainData = findViewById(R.id.tv_home_result_maintainData);
        tvDeserveMoney = findViewById(R.id.tv_home_deserveMoney);

        //获取预订页面传递的订单信息订单明细
        String predateInfo = getIntent().getStringExtra("predateInfo");
        if (Tools.isNotNull(predateInfo) ) {
            //解析上一个页面传递的数据
            try {
                JSONObject jsonObject=new JSONObject(predateInfo);
                String predateList=jsonObject.getString("predateList");
                String listPreRepairItem=jsonObject.getString("listPreRepairItem");
                String listPreProduct=jsonObject.getString("listPreProduct");
                String listPreOtherCost=jsonObject.getString("listPreOtherCost");
                //反序列化数据
                predateVo=gson.fromJson(predateList,PredateVo.class);
                Type typePreRepairItemDetailVo = new TypeToken<List<PreRepairItemDetailVo>>() {}.getType();
                repairItemDetailVoList = gson.fromJson(listPreRepairItem, typePreRepairItemDetailVo);
                Type typePreProductDetailVo = new TypeToken<List<PreProductDetailVo>>() {}.getType();
                productDetailVoList = gson.fromJson(listPreProduct, typePreProductDetailVo);
                Type typePreOtherCostDetailVo = new TypeToken<List<PreOtherCostDetailVo>>() {}.getType();
                otherCostDetailVoList = gson.fromJson(listPreOtherCost, typePreOtherCostDetailVo);
                initView();//初始化
                setViewEventListener();//监听事件
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(myActivity, "无效访问", Toast.LENGTH_LONG).show();
                finish();//关闭activity
            }
        }else {
            Toast.makeText(myActivity, "无效访问", Toast.LENGTH_LONG).show();
            finish();//关闭activity
        }

        //======自定义myActionBar
        MyActionBar myActionBar=findViewById(R.id.myActionBar);
        myActionBar.setData("订单信息", R.drawable.ic_custom_actionbar_left_black, "", 1, 0, new MyActionBar.ActionBarClickListener() {
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
        tvPredateNum.setText(predateVo.getPredateNum().trim());
        tvOpenDate.setText(sf.format(predateVo.getOpenDate()));
        tvDescribe.setText(predateVo.getDescribe().trim());
        tvRemark.setText(predateVo.getRemark().trim());
        BigDecimal weiXiuAmount=new BigDecimal(0.0);//维修项目金额
        BigDecimal chanPinAmount=new BigDecimal(0.0);//产品材料金额
        BigDecimal feiYongAmount=new BigDecimal(0.0);//其他费用金额
        BigDecimal deductionAmount=new BigDecimal(0.0);//抵扣金额
        BigDecimal receivable=new BigDecimal(0.0);//应收金额
        for (int i =0;i<repairItemDetailVoList.size();i++) {
            weiXiuAmount = weiXiuAmount.add(new BigDecimal(repairItemDetailVoList.get(i).getAmountPaid()));
            if (repairItemDetailVoList.get(i).getMaintainabilityId() == 3 || repairItemDetailVoList.get(i).getMaintainabilityId() == 4 ||
                    repairItemDetailVoList.get(i).getMaintainabilityId() == 6) {//抵扣金额累加
                deductionAmount = deductionAmount.add(new BigDecimal(repairItemDetailVoList.get(i).getAmountPaid()));
            }
            tvWeiXiuNum.setText(String.format(Locale.getDefault(), "（%d项）", repairItemDetailVoList.size()));
        }
        for (int i=0;i<productDetailVoList.size();i++){
            chanPinAmount=chanPinAmount.add(new BigDecimal(productDetailVoList.get(i).getAmount()));
            if (productDetailVoList.get(i).getMaintainabilityId()==3||productDetailVoList.get(i).getMaintainabilityId()==4 ||
                    productDetailVoList.get(i).getMaintainabilityId()==6){//抵扣金额累加
                deductionAmount=deductionAmount.add(new BigDecimal(productDetailVoList.get(i).getAmount()));
            }
        }
        tvChanPinNum.setText(String.format(Locale.getDefault(),"（%d项）",productDetailVoList.size()));
        for (int i=0;i<otherCostDetailVoList.size();i++){
            feiYongAmount=feiYongAmount.add(new BigDecimal(otherCostDetailVoList.get(i).getAmountPaid()));
        }
        tvFeiYongNum.setText(String.format(Locale.getDefault(),"（%d项）",otherCostDetailVoList.size()));
        receivable=weiXiuAmount.add(chanPinAmount).add(feiYongAmount).subtract(deductionAmount);//应收金额
        tvWeiXiuMoney.setText(Tools.setMoneySize(String.format(Locale.getDefault(),"￥%.2f",weiXiuAmount)));//维修金额
        tvChanPinMoney.setText(Tools.setMoneySize(String.format(Locale.getDefault(),"￥%.2f",chanPinAmount)));//产品金额
        tvFeiYongMoney.setText(Tools.setMoneySize(String.format(Locale.getDefault(),"￥%.2f",feiYongAmount)));//费用金额
        if (deductionAmount.intValue()>0){
            tvDeduction.setText(Tools.setMoneySize(String.format(Locale.getDefault(),"￥-%.2f",deductionAmount)));//抵扣金额
        }else {
            tvDeduction.setText(Tools.setMoneySize(String.format(Locale.getDefault(),"￥%.2f",deductionAmount)));//抵扣金额
        }
        tvDeserveMoney.setText(Tools.setMoneySize(String.format(Locale.getDefault(),"￥%.2f",receivable)));//应收金额
        tvMaintainData.setText(String.format(Locale.getDefault(),"请于 %s 前到店支付金额进行维修",sf.format(predateVo.getMaintainData())));
        tvDeserveMoney.setText(Tools.setMoneySize(String.format(Locale.getDefault(),"￥%.2f",predateVo.getDeserveMoney())));
    }
    /**
     * 事件监听
     */
    private void setViewEventListener() {
        //订单信息
        llPredateNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(myActivity, OrderInfoActivity.class);
                intent.putExtra("predateId",predateVo.getPredateId());
                startActivity(intent);
                myApplication.setActivity(myActivity);
            }
        });
    }

}
