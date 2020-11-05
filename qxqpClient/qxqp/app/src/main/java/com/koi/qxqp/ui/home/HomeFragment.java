package com.koi.qxqp.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSONArray;
import com.bigkoo.pickerview.TimePickerView;
import com.bryant.selectorlibrary.DSelectorPopup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koi.qxqp.MyApplication;
import com.koi.qxqp.R;
import com.koi.qxqp.bean.AppendOptionVo;
import com.koi.qxqp.bean.MaintenanceCusVo;
import com.koi.qxqp.bean.PreOtherCostDetailVo;
import com.koi.qxqp.bean.PreProductDetailVo;
import com.koi.qxqp.bean.PreRepairItemDetailVo;
import com.koi.qxqp.bean.PredateVo;
import com.koi.qxqp.util.AppendOptionUtil;
import com.koi.qxqp.util.KeyBoardUtil;
import com.koi.qxqp.util.OkHttpTool;
import com.koi.qxqp.util.ServiceUrls;
import com.koi.qxqp.util.Tools;
import com.koi.qxqp.widget.MyActionBar;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HomeFragment extends Fragment {
    private Activity myActivity;//上下文
    private MyApplication myApplication;//全局变量
    private MaintenanceCusVo maintenanceCusVo;//客户信息
    private PredateVo predateVo=new PredateVo();
    private List<PredateVo> predate=new ArrayList<>();//订单主信息
    private List<PreRepairItemDetailVo> repairItemDetailVoList=new ArrayList<>();//维修项目
    private List<PreProductDetailVo> productDetailVoList=new ArrayList<>();//产品材料
    private List<PreOtherCostDetailVo> otherCostDetailVoList=new ArrayList<>();//其他费用
    private SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private List<AppendOptionVo>  list=null;//单选列表
    Map<String,List<AppendOptionVo>> map=null;//获取后台返回的集合
    private TextView ticker;//温馨提示
    private EditText etOwner;//车主
    private EditText etTelephone;//车主电话
    private EditText etCarNum;//车牌
    private EditText etVehicleType;//车型
    private EditText etContacts;//联系人
    private EditText etCarMasterPhone;//联系人电话
    private TextView tvCarderName;//接车人
    private TextView tvRepairName;//修理类别
    private TextView tvMaintainData;//维修日期
    private EditText etRemark;//备注
    private EditText etDescribe;//故障描述
    private TextView tvWeiXiuNum;//维修项目数
    private TextView tvWeiXiuMoney;//维修项目金额
    private TextView tvChanPinNum;//产品材料数
    private TextView tvChanPinMoney;//产品材料金额
    private TextView tvFeiYongNum;//其他费用数
    private TextView tvFeiYongMoney;//其他费用金额
    private TextView tvDeduction;//抵扣金额
    private TextView tvDeserveMoney;//总金额
    private LinearLayout llCarderName;//接车人
    private LinearLayout llRepairName;//修理类别
    private LinearLayout llMaintainData;//维修日期
    private LinearLayout llWeiXiu;//维修项目
    private LinearLayout llChanPin;//产品材料
    private LinearLayout llFeiYong;//其他费用
    private Button btnSubmit;//提交

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity=getActivity();
        myApplication= (MyApplication) myActivity.getApplication();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        //保证getActivity()可以获取到上下文
        if (myActivity==null){
            return view;
        }
        ticker = view.findViewById(R.id.ticker);
        etOwner = view.findViewById(R.id.et_home_owner);
        etTelephone = view.findViewById(R.id.et_home_telephone);
        etCarNum = view.findViewById(R.id.et_home_carnum);
        etVehicleType = view.findViewById(R.id.et_home_vehicletype);
        etContacts = view.findViewById(R.id.et_home_contacts);
        etCarMasterPhone = view.findViewById(R.id.et_home_carMasterPhone);
        tvCarderName = view.findViewById(R.id.tv_home_carderName);
        tvRepairName = view.findViewById(R.id.tv_home_repairName);
        tvMaintainData = view.findViewById(R.id.tv_home_maintainData);
        etRemark = view.findViewById(R.id.et_home_remark);
        etDescribe = view.findViewById(R.id.et_home_describe);
        tvWeiXiuNum = view.findViewById(R.id.tv_home_weixiu_num);
        tvWeiXiuMoney = view.findViewById(R.id.tv_home_weixiu_money);
        tvChanPinNum = view.findViewById(R.id.tv_home_chanpin_num);
        tvChanPinMoney = view.findViewById(R.id.tv_home_chanpin_money);
        tvFeiYongNum = view.findViewById(R.id.tv_home_feiyong_num);
        tvFeiYongMoney = view.findViewById(R.id.tv_home_feiyong_money);
        tvDeduction=view.findViewById(R.id.tv_home_deduction);
        tvDeserveMoney=view.findViewById(R.id.tv_home_deserveMoney);
        llCarderName = view.findViewById(R.id.ll_home_carderName);
        llRepairName = view.findViewById(R.id.ll_home_repairName);
        llMaintainData = view.findViewById(R.id.ll_home_maintainData);
        llWeiXiu = view.findViewById(R.id.ll_home_weixiu);
        llChanPin = view.findViewById(R.id.ll_home_chanpin);
        llFeiYong = view.findViewById(R.id.ll_home_feiyong);
        btnSubmit = view.findViewById(R.id.btn_home_submit);
        ticker.setSelected(true);//开启走马灯动画
        MyActionBar myActionBar=view.findViewById(R.id.myActionBar);
        myActionBar.setData("预约订单",0,"",0,1,null);
        initView();//初始化页面
        setViewListener();//监听事件
        return view;
    }

    private void setViewListener() {
        //提交订单
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String owner= Tools.toString(etOwner.getText().toString().trim());//车主
                String telephone= Tools.toString(etTelephone.getText().toString().trim());//车主电话
                String carNum= Tools.toString(etCarNum.getText().toString().trim());//车牌
                String vehicleType= Tools.toString(etVehicleType.getText().toString().trim());//车型
                String contacts= Tools.toString(etContacts.getText().toString().trim());//联系人
                String carMasterPhone= Tools.toString(etCarMasterPhone.getText().toString().trim());//联系人电话
                String carderName= Tools.toString(tvCarderName.getText().toString().trim());//接车人
                String repairName= Tools.toString(tvRepairName.getText().toString().trim());//修理类别
                String maintainData= Tools.toString(tvMaintainData.getText().toString().trim());//维修日期
                String remark= Tools.toString(etRemark.getText().toString().trim());//备注
                String describe= Tools.toString(etDescribe.getText().toString().trim());//故障描述
                Double deserveMoney=Tools.getMoney(tvDeserveMoney.getText().toString().trim());
                if (!Tools.isNotNull(owner)){
                    Toast.makeText(myActivity,"请填写车主姓名",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!Tools.isMobile(telephone)){
                    Toast.makeText(myActivity,"请填写正确的手机号",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!Tools.isCarNum(carNum)){
                    Toast.makeText(myActivity,"请填正确的车牌号",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!Tools.isNotNull(vehicleType)){
                    Toast.makeText(myActivity,"请填写车型",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!Tools.isNotNull(contacts)){
                    Toast.makeText(myActivity,"请填写联系人",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!Tools.isMobile(carMasterPhone)){
                    Toast.makeText(myActivity,"请填写正确的手机号",Toast.LENGTH_LONG).show();
                    return;
                }
                if (carderName.equals("未选择")){
                    Toast.makeText(myActivity,"请选择接车人",Toast.LENGTH_LONG).show();
                    return;
                }
                if (repairName.equals("未选择")){
                    Toast.makeText(myActivity,"请选择修理类别",Toast.LENGTH_LONG).show();
                    return;
                }
                if (maintainData.equals("未选择")){
                    Toast.makeText(myActivity,"请选择维修日期",Toast.LENGTH_LONG).show();
                    return;
                }
                if (myApplication.isLogin()){
                        predateVo.setOwner(owner);
                        predateVo.setTelephone(telephone);
                        predateVo.setCarNum(carNum);
                        predateVo.setVehicleType(vehicleType);
                        predateVo.setContacts(contacts);
                        predateVo.setCarMasterPhone(carMasterPhone);
                        predateVo.setRemark(remark);
                        predateVo.setDescribe(describe);
                        predateVo.setDeserveMoney(deserveMoney);
                        predate.add(predateVo);
                        String url= ServiceUrls.getClientMethodUrl("savePredate");
                        Map<String,Object> map=new HashMap<>();
                        map.put("pwPredates", JSONArray.toJSON(predate).toString());
                        map.put("sysPreRepairItemDetail",JSONArray.toJSON(repairItemDetailVoList).toString());
                        map.put("sysPreProductDetail",JSONArray.toJSON(productDetailVoList).toString());
                        map.put("sysPreOtherCostDetail",JSONArray.toJSON(otherCostDetailVoList).toString());
                        final LoadingDialog loadingDialog=new LoadingDialog(myActivity);
                        loadingDialog.show();
                        OkHttpTool.httpPost(url, map, new OkHttpTool.ResponseCallback() {
                            @Override
                            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {
                                myActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String strText = "网络环境不佳，请稍后再试";
                                        if (isSuccess && responseCode==200){
                                            try {
                                                JSONObject jsonObject = new JSONObject(response);
                                                int code = jsonObject.getInt("code");
                                                strText=jsonObject.getString("text");
                                                if (code == 200) {
                                                    Toast.makeText(myActivity, "预订成功", Toast.LENGTH_LONG).show();
                                                    //跳转到预订成功页面，并finish本页面
                                                    String strData = jsonObject.getString("data");
                                                    if (Tools.isNotNull(strData)) {
                                                        Intent intent = new Intent(myActivity, HomeResultActivity.class);
                                                        intent.putExtra("predateInfo", strData);//将返回的订单信息和支付方式列表传递到结果页面
                                                        startActivity(intent);
                                                        initView();//重置预定页面
                                                    }
                                                } else {
                                                    Toast.makeText(myActivity, strText, Toast.LENGTH_LONG).show();
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        loadingDialog.close();
                                        Toast.makeText(myActivity, strText, Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        });
                }else {
                    Toast.makeText(myActivity,"尚未登录，无法提交订单",Toast.LENGTH_LONG).show();
                }
            }
        });
        //接车人
        llCarderName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyBoardUtil.hideKeyboard(v);//关闭键盘
                list= (List<AppendOptionVo>) map.get("carder");
                final AppendOptionUtil appendOptionUtil=new AppendOptionUtil(list);
                int option=0;
                if (!tvCarderName.getText().toString().equals("未选择")){
                    option=appendOptionUtil.getItemCheckedByName(tvCarderName.getText().toString());
                }
                final DSelectorPopup dSelectorPopup=new DSelectorPopup(myActivity,appendOptionUtil.itemName);
                dSelectorPopup.setTextcolor_unchecked(getResources().getColor(R.color.colorPrimary))
                        .setOffset(option)//默认选中
                        .setButtonText("确定")
                        .setGradual_color(0xffD81B60)
                        .setTitleText("接车人")
                        .setTitleColor(getResources().getColor(R.color.colorPrimary)).build();
                dSelectorPopup.popOutShadow(v);
                dSelectorPopup.setSelectorListener(new DSelectorPopup.SelectorClickListener() {
                    @Override
                    public void onSelectorClick(int position, String text) {
                        tvCarderName.setText(text);
                        predateVo.setCarderId(appendOptionUtil.getItemIdByName(text));
                        dSelectorPopup.dismissPopup();
                    }
                });
            }
        });
        //修理类别
        llRepairName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyBoardUtil.hideKeyboard(v);//关闭键盘
                list= (List<AppendOptionVo>) map.get("repair");
                final AppendOptionUtil appendOptionUtil=new AppendOptionUtil(list);
                int option=0;
                if (!tvRepairName.getText().toString().equals("未选择")){
                    option=appendOptionUtil.getItemCheckedByName(tvRepairName.getText().toString());
                }
                final DSelectorPopup dSelectorPopup=new DSelectorPopup(myActivity,appendOptionUtil.getItemName());
                dSelectorPopup.setTextcolor_unchecked(getResources().getColor(R.color.colorPrimary))
                        .setOffset(option)//默认选中
                        .setButtonText("确定")
                        .setGradual_color(0xffD81B60)
                        .setTitleText("修理类别")
                        .setTitleColor(getResources().getColor(R.color.colorPrimary)).build();
                dSelectorPopup.popOutShadow(v);
                dSelectorPopup.setSelectorListener(new DSelectorPopup.SelectorClickListener() {
                    @Override
                    public void onSelectorClick(int position, String text) {
                        tvRepairName.setText(text);
                        predateVo.setRepairId(appendOptionUtil.getItemIdByName(text));
                        dSelectorPopup.dismissPopup();
                    }
                });
            }
        });
        //维修日期
        llMaintainData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    KeyBoardUtil.hideKeyboard(v);//关闭键盘
                    Calendar startDate = Calendar.getInstance();//设置起始年份
                    Calendar endDate = Calendar.getInstance();
                    endDate.set(2050,1,1);//设置结束年份
                    Calendar selectedCalendar =Calendar.getInstance();
                    if (!tvMaintainData.getText().toString().equals("未选择")){
                        selectedCalendar.setTime(sf.parse(tvMaintainData.getText().toString()));
                    }
                    TimePickerView pvTime = new TimePickerView.Builder(myActivity, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            tvMaintainData.setText(sf.format(date));
                            predateVo.setMaintainData(date);
                        }
                    }).setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                            .setCancelText("取消")//取消按钮文字
                            .setSubmitText("确定")//确认按钮文字
                            .setContentSize(15)//滚轮文字大小
                            .setTitleSize(20)//标题文字大小
                            .setTitleText("维修日期")//标题文字
                            .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                            .setSubmitColor(Color.parseColor("#fb7299"))//确定按钮文字颜色
                            .setCancelColor(Color.parseColor("#585858"))//取消按钮文字颜色*/
                            .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                            .setRangDate(startDate,endDate)
                            .setDate(selectedCalendar)// 如果不设置的话，默认是系统时间*/
                            .setLabel("年","月","日","时","分","秒")
                            .build();
                    pvTime.show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        //维修项目
        llWeiXiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(myActivity,WeiXiuActivity.class);
                startActivity(intent);
            }
        });
        //产品材料
        llChanPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(myActivity,ChanPinActivity.class);
                startActivity(intent);
            }
        });
        //其他费用
        llFeiYong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(myActivity,FeiYongActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化页面
     */
    private void initView() {
        if (myApplication.isLogin()){
            map=myApplication.getAppendOptionVoMap();
            maintenanceCusVo = myApplication.getLoginMaintenanceCus();
            etOwner.setText(maintenanceCusVo.getOwner().trim());//车主
            etTelephone.setText(maintenanceCusVo.getMobilePhone().trim());//车主电话
            etCarNum.setText(maintenanceCusVo.getLicenseCode().trim());//车牌
            etVehicleType.setText(maintenanceCusVo.getVehicleType().trim());//车型
            etContacts.setText(maintenanceCusVo.getRepairMan().trim());//联系人
            etCarMasterPhone.setText(maintenanceCusVo.getRepairTele().trim());//联系人电话
            tvCarderName.setText("未选择");
            tvRepairName.setText("未选择");
            tvMaintainData.setText("未选择");
            etRemark.setText("");
            etDescribe.setText("");
            myApplication.getRepairItemDetailVoList().clear();
            myApplication.getProductDetailVoList().clear();
            myApplication.getOtherCostDetailVoList().clear();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 页面显示时 计算金额明细和数量
     */
    @Override
    public void onResume() {
        BigDecimal weiXiuAmount=new BigDecimal(0.0);//维修项目金额
        BigDecimal chanPinAmount=new BigDecimal(0.0);//产品材料金额
        BigDecimal feiYongAmount=new BigDecimal(0.0);//其他费用金额
        BigDecimal deductionAmount=new BigDecimal(0.0);//抵扣金额
        BigDecimal receivable=new BigDecimal(0.0);//应收金额
        if (myApplication.getRepairItemDetailVoList()!=null){//维修项目
            repairItemDetailVoList=myApplication.getRepairItemDetailVoList();
            for (int i =0;i<repairItemDetailVoList.size();i++){
                weiXiuAmount=weiXiuAmount.add(new BigDecimal(repairItemDetailVoList.get(i).getAmountPaid()));
                if (repairItemDetailVoList.get(i).getMaintainabilityId()==3||repairItemDetailVoList.get(i).getMaintainabilityId()==4 ||
                    repairItemDetailVoList.get(i).getMaintainabilityId()==6){//抵扣金额累加
                    deductionAmount=deductionAmount.add(new BigDecimal(repairItemDetailVoList.get(i).getAmountPaid()));
                }
            }
            tvWeiXiuNum.setText(String.format(Locale.getDefault(),"（%d项）",repairItemDetailVoList.size()));
        }
        if (myApplication.getProductDetailVoList()!=null){//产品材料
            productDetailVoList=myApplication.getProductDetailVoList();
            for (int i=0;i<productDetailVoList.size();i++){
                chanPinAmount=chanPinAmount.add(new BigDecimal(productDetailVoList.get(i).getAmount()));
                if (productDetailVoList.get(i).getMaintainabilityId()==3||productDetailVoList.get(i).getMaintainabilityId()==4 ||
                        productDetailVoList.get(i).getMaintainabilityId()==6){//抵扣金额累加
                    deductionAmount=deductionAmount.add(new BigDecimal(productDetailVoList.get(i).getAmount()));
                }
            }
            tvChanPinNum.setText(String.format(Locale.getDefault(),"（%d项）",productDetailVoList.size()));
        }
        if (myApplication.getOtherCostDetailVoList()!=null){//产品材料
            otherCostDetailVoList=myApplication.getOtherCostDetailVoList();
            for (int i=0;i<otherCostDetailVoList.size();i++){
                feiYongAmount=feiYongAmount.add(new BigDecimal(otherCostDetailVoList.get(i).getAmountPaid()));
            }
            tvFeiYongNum.setText(String.format(Locale.getDefault(),"（%d项）",otherCostDetailVoList.size()));
        }
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
        super.onResume();
    }
}
