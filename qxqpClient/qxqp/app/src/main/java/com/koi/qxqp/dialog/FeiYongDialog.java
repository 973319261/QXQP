package com.koi.qxqp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.koi.qxqp.MyApplication;
import com.koi.qxqp.R;
import com.koi.qxqp.bean.AppendOptionVo;
import com.koi.qxqp.bean.ExpensesVo;
import com.koi.qxqp.bean.PreOtherCostDetailVo;
import com.koi.qxqp.util.AppendOptionUtil;
import com.koi.qxqp.util.OkHttpTool;
import com.koi.qxqp.util.ServiceUrls;
import com.koi.qxqp.widget.FlowRadioGroup;
import com.nineoldandroids.view.ViewHelper;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeiYongDialog extends DialogFragment {
    private Context context;//上下文
    private MyApplication myApplication;//全局变量
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private PreOtherCostDetailVo preOtherCostDetailVo;//数据
    private Dialog dialog;//弹框
    private TextView tvTitle; //标题
    private FlowRadioGroup frgExpensesName;//修理项目选项父容器
    private EditText etAmount; //金额
    private EditText etDiscount;//折扣
    private EditText etAmountPaid;//实收金额
    private EditText etRemark;//备注
    private TextView tvCancel; //取消
    private TextView tvSave; //保存
    Map<String, List<AppendOptionVo>> mapData=null;//获取后台返回的集合
    private OnSaveClickListener onSaveClickListener;//存放对外部的开发的点击事件
    private View view;
    public FeiYongDialog(@NonNull Context context , PreOtherCostDetailVo preOtherCostDetailVo, OnSaveClickListener onSaveClickListener) {
        this.context=context;
        this.preOtherCostDetailVo=preOtherCostDetailVo;
        this.onSaveClickListener=onSaveClickListener;
    }
    //外部接口
    public interface OnSaveClickListener{
        void onSaveClick(Dialog dialog,PreOtherCostDetailVo preOtherCostDetailVo);
    }

    /**
     * 创建dialog
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        myApplication= (MyApplication) getActivity().getApplication();
        mapData=myApplication.getAppendOptionVoMap();//获取下拉列表
        dialog=new Dialog(context, R.style.dialog);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//软键盘就会把dialog弹起，有的手机则会遮住dialog布局。
        view = View.inflate(getActivity(),R.layout.dialog_feiyong,null);
        dialog.setContentView(view);
        tvTitle = view.findViewById(R.id.tv_dialog_feiyong_title);
        frgExpensesName=view.findViewById(R.id.frg_dialog_feiyong_expensesName);
        etAmount = view.findViewById(R.id.et_dialog_feiyong_amount);
        etDiscount = view.findViewById(R.id.et_dialog_feiyong_discount);
        etAmountPaid = view.findViewById(R.id.et_dialog_feiyong_amountPaid);
        etRemark = view.findViewById(R.id.et_dialog_feiyong_remark);
        tvCancel = view.findViewById(R.id.tv_dialog_feiyong_cancel);
        tvSave = view.findViewById(R.id.tv_dialog_feiyong_save);
        initView();//初始化
        setViewListener();//事件监听
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.alpha = 1;
        lp.dimAmount = 0.5f;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.windowAnimations=R.style.dialog_bottom_top;//设置弹窗动画
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        return dialog;
    }

    /**
     * 初始化
     */
    private void initView() {
        //绑定选项信息
        AppendOptionUtil.appendOption(getContext(),frgExpensesName,mapData.get("expenses"),30,true);//其他费用
        if (preOtherCostDetailVo!=null){//修改维修项目
            tvTitle.setText("修改其他费用");
            if (preOtherCostDetailVo.getExpensesId()>0){
                frgExpensesName.check(preOtherCostDetailVo.getExpensesId());
            }
            if (preOtherCostDetailVo.getAmount()!=null){
                etAmount.setText(String.format(Locale.getDefault(),"%.2f",preOtherCostDetailVo.getAmount()));
            }
            if (preOtherCostDetailVo.getDiscount()!=null){
                etDiscount.setText(String.format(Locale.getDefault(),"%.0f",preOtherCostDetailVo.getDiscount()));
            }
            if (preOtherCostDetailVo.getAmountPaid()!=null){
                etAmountPaid.setText(String.format(Locale.getDefault(),"%.2f",preOtherCostDetailVo.getAmountPaid()));
            }
            if (preOtherCostDetailVo.getRemark()!=null){
                etRemark.setText(preOtherCostDetailVo.getRemark().trim());
            }
        }else {//添加维修项目
            tvTitle.setText("添加其他费用");
            preOtherCostDetailVo=new PreOtherCostDetailVo();//实例化
        }
    }
    /**
     * 事件监听
     */
    private void setViewListener(){

        //其他费用
        frgExpensesName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String url= ServiceUrls.getCommonMethodUrl("selectExpensesChange");
                Map<String,Object> map=new HashMap<>();
                map.put("expensesID",checkedId);
                OkHttpTool.httpPost(url, map, new OkHttpTool.ResponseCallback() {
                    @Override
                    public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (isSuccess && responseCode==200){
                                    Type type = new TypeToken<ExpensesVo>() {}.getType();
                                    ExpensesVo expensesVo = gson.fromJson(response,type);
                                    if (expensesVo!=null){
                                        etAmount.setText(String.valueOf(expensesVo.getPrice()));//维修费
                                        if (expensesVo.getPrice()!=null && !TextUtils.isEmpty(etDiscount.getText().toString())){
                                            BigDecimal discount=new BigDecimal(etDiscount.getText().toString()).divide(new BigDecimal(100));//折扣
                                            etAmountPaid.setText(String.valueOf(new BigDecimal(expensesVo.getPrice()).
                                                    multiply(discount)));//实收金额
                                        }else {
                                            etAmountPaid.setText("");
                                        }
                                    }
                                }else {
                                    Toast.makeText(context,"加载失败,请稍后再试",Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                    }
                });
            }
        });
        //修理费改变事件
        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) &&!TextUtils.isEmpty(etDiscount.getText().toString())){
                    BigDecimal discount=new BigDecimal(etDiscount.getText().toString()).divide(new BigDecimal(100));//折扣
                    etAmountPaid.setText(String.valueOf(new BigDecimal(s.toString()).
                            multiply(discount)));//实收金额
                }else {
                    etAmountPaid.setText("");
                }
            }
        });
        //折扣改变事件
        etDiscount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String words = s.toString();
                //首先内容进行非空判断，空内容（""和null）不处理
                if (!TextUtils.isEmpty(words) &&!TextUtils.isEmpty(etAmount.getText().toString())){
                    //1-100的正则验证
                    Pattern p = Pattern.compile("^(100|[1-9]\\d|\\d)$");
                    Matcher m = p.matcher(words);
                    if (m.find() || ("").equals(words)) {
                        if (!"".equals(etAmount.getText().toString())){
                            BigDecimal discount=new BigDecimal(etDiscount.getText().toString()).divide(new BigDecimal(100));//折扣
                            etAmountPaid.setText(String.valueOf(new BigDecimal(etAmount.getText().toString()).
                                    multiply(discount)));//实收金额
                        }else {
                            etAmountPaid.setText("");
                        }
                    }else {
                        if (words.length() > 2) {
                            etDiscount.setText("100");
                        }else if ("00".equals(words)){
                            etAmountPaid.setText("");
                        }
                    }
                }else {
                    etAmountPaid.setText("");
                }

            }
        });
        //保存事件
        if (onSaveClickListener!=null){
            tvSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (preOtherCostDetailVo!=null){
                        Integer expensesId=frgExpensesName.getCheckedRadioButtonId();//其他费用ID
                        String amountStr=etAmount.getText().toString().trim();//修理费
                        String discountStr=etDiscount.getText().toString().trim();//折扣
                        String amountPaidStr=etAmountPaid.getText().toString().trim();//实收金额
                        Integer maintainabilityId=frgExpensesName.getCheckedRadioButtonId();//维修项目ID
                        if (expensesId==-1){
                            Toast.makeText(context,"请选择其他费用",Toast.LENGTH_LONG).show();
                            return;
                        }
                        if ("".equals(amountStr)){
                            Toast.makeText(context,"请填写金额",Toast.LENGTH_LONG).show();;
                            return;
                        }
                        if ("".equals(discountStr)){
                            Toast.makeText(context,"请填写折扣",Toast.LENGTH_LONG).show();;
                            return;
                        }
                        if ("".equals(amountPaidStr)){
                            Toast.makeText(context,"请填写实收金额",Toast.LENGTH_LONG).show();;
                            return;
                        }
                        Double amount=Double.parseDouble(amountStr);//修理费
                        Double discount=Double.parseDouble(discountStr);//折扣
                        Double amountPaid=Double.parseDouble(amountPaidStr);//实收金额
                        String remark=etRemark.getText().toString().trim();//备注
                        preOtherCostDetailVo.setExpensesId(expensesId);
                        preOtherCostDetailVo.setAmount(amount);
                        preOtherCostDetailVo.setDiscount(discount);
                        preOtherCostDetailVo.setAmountPaid(amountPaid);
                        preOtherCostDetailVo.setRemark(remark);
                        onSaveClickListener.onSaveClick(dialog,preOtherCostDetailVo);
                    }else {

                    }
                }
            });
        }
        //取消事件
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        //手指滑动监听
        view.setOnTouchListener(new View.OnTouchListener() {
            int lastY,offsetY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int y=(int)event.getRawY();
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:// 当手指按下的时候调用此方法
                        lastY=(int)event.getRawY();//记录手指按下位置
                        break;
                    case MotionEvent.ACTION_MOVE://当手指滑动的时候调用此方法
                        offsetY=y-lastY;//记录Y轴移动的距离
                        if (offsetY>0){
                            ViewHelper.setTranslationY(view,offsetY); //跟随手指滑动，向下移动View
                        }
                        break;
                    case MotionEvent.ACTION_UP://当手指按下的时候调用此方法
                        if (offsetY>0){
                            if (offsetY<view.getHeight()/2){// 判断当前移动的位置是否超过屏幕的一半时
                                ViewHelper.setTranslationY(view,0);//滑动最初位置
                            }else {
                                dialog.dismiss();//关闭弹窗
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }
}
