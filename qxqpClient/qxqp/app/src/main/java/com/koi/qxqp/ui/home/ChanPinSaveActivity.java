package com.koi.qxqp.ui.home;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.koi.qxqp.MyApplication;
import com.koi.qxqp.R;
import com.koi.qxqp.bean.AppendOptionVo;
import com.koi.qxqp.bean.FittingsInfoVo;
import com.koi.qxqp.bean.PreProductDetailVo;
import com.koi.qxqp.dialog.CommomDialog;
import com.koi.qxqp.ui.user.PeiJianActivity;
import com.koi.qxqp.util.AppendOptionUtil;
import com.koi.qxqp.util.StatusBarUtil;
import com.koi.qxqp.widget.FlowRadioGroup;
import com.koi.qxqp.widget.MyActionBar;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ChanPinSaveActivity extends AppCompatActivity {
    private Activity myActivity;//上下文
    private MyApplication myApplication;//全局变量
    private TextView tvFittingsCode;//配件编码
    private LinearLayout llFittingsCode;//配件编码选择
    private TextView tvFittingsName;//配件名称
    private TextView tvVehicleType;//车型
    private EditText etQuantity;//数量
    private TextView tvSystemUnit;//单位
    private EditText etUnitPrice;//单价
    private EditText etDiscount;//折扣
    private EditText etAmount;//金额
    private EditText etRemark;//备注
    private FlowRadioGroup frgMaintainability;//维修性质
    private String title="添加产品材料";//标题
    Map<String, List<AppendOptionVo>> mapData=null;//获取后台返回的集合
    List<PreProductDetailVo> list;
    private BigDecimal discount;//折扣
    private Integer position=-1;//产品配件列表索引 用来判断是否修改
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myActivity=this;
        myApplication= (MyApplication) myActivity.getApplication();
        setContentView(R.layout.activity_home_chanpin_add);
        tvFittingsCode = findViewById(R.id.tv_home_chanpin_fittingsCode);
        llFittingsCode=findViewById(R.id.ll_home_chanpin_fittingsCode);
        tvFittingsName = findViewById(R.id.tv_home_chanpin_fittingsName);
        tvVehicleType = findViewById(R.id.tv_home_chanpin_vehicleType);
        etQuantity = findViewById(R.id.et_home_chanpin_quantity);
        tvSystemUnit = findViewById(R.id.tv_home_chanpin_systemUnit);
        etUnitPrice = findViewById(R.id.et_home_chanpin_unitPrice);
        etDiscount = findViewById(R.id.et_home_chanpin_discount);
        etAmount = findViewById(R.id.et_home_chanpin_amount);
        etRemark = findViewById(R.id.et_home_chanpin_remark);
        frgMaintainability = findViewById(R.id.frg_home_chanpin__maintainability);
        mapData=myApplication.getAppendOptionVoMap();
        list=myApplication.getProductDetailVoList();//保存到内存中
        initView();//初始化
        setViewEventListener();//监听事件
        //======自定义myActionBar
        MyActionBar myActionBar=findViewById(R.id.myActionBar);
        myActionBar.setData(title, R.drawable.ic_custom_actionbar_left_black, "保存", 1, 0, new MyActionBar.ActionBarClickListener() {
            @Override
            public void onLeftClick() {//用户按返回
                if (!("请选择").equals(tvFittingsCode.getText().toString().trim())){
                    CommomDialog commomDialog= new CommomDialog(myActivity, R.style.dialog, "确定要退出吗?", new CommomDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm==true){//确定按钮
                                finish();
                            }
                        }
                    });
                    commomDialog.setTitle("当前数据没有保存");
                    commomDialog.show();//显示弹出框
                }else {
                    finish();//关闭页面
                }
            }
            @Override
            public void onRightClick() {//保存
                if (tvFittingsCode.getText().toString().trim().equals("请选择")){
                    Toast.makeText(myActivity,"请选择配件编号",Toast.LENGTH_LONG).show();
                    return;
                }
                if ("".equals(etQuantity.getText().toString())){
                    Toast.makeText(myActivity,"请填写数量",Toast.LENGTH_LONG).show();
                    return;
                }
                if ("".equals(etUnitPrice.getText().toString())){
                    Toast.makeText(myActivity,"请填写单价",Toast.LENGTH_LONG).show();
                    return;
                }
                if ("".equals(etDiscount.getText().toString())){
                    Toast.makeText(myActivity,"请填写折扣",Toast.LENGTH_LONG).show();
                    return;
                }
                if ("".equals(etAmount.getText().toString())){
                    Toast.makeText(myActivity,"请填写金额",Toast.LENGTH_LONG).show();
                    return;
                }
                if (frgMaintainability.getCheckedRadioButtonId()==-1){
                    Toast.makeText(myActivity,"请选择维修性质",Toast.LENGTH_LONG).show();
                    return;
                }
                if (position>-1){//修改 通过产品材料列表索引修改
                    list.get(position).setFittingsCode(tvFittingsCode.getText().toString().trim());
                    list.get(position).setFittingsName(tvFittingsName.getText().toString().trim());
                    list.get(position).setVehicleType(tvVehicleType.getText().toString().trim());
                    list.get(position).setQuantity(Double.parseDouble(etQuantity.getText().toString().trim()));
                    list.get(position).setSystemUnit(tvSystemUnit.getText().toString().trim());
                    list.get(position).setUnitPrice(Double.parseDouble(etUnitPrice.getText().toString().trim()));
                    list.get(position).setDiscount(Double.parseDouble(etDiscount.getText().toString().trim()));
                    list.get(position).setAmount(Double.parseDouble(etAmount.getText().toString().trim()));
                    list.get(position).setMaintainabilityId(frgMaintainability.getCheckedRadioButtonId());
                    list.get(position).setRemark(etRemark.getText().toString().trim());
                }else {//新增
                    PreProductDetailVo preProductDetailVo=new PreProductDetailVo();
                    preProductDetailVo.setFittingsCode(tvFittingsCode.getText().toString().trim());
                    preProductDetailVo.setFittingsName(tvFittingsName.getText().toString().trim());
                    preProductDetailVo.setVehicleType(tvVehicleType.getText().toString().trim());
                    preProductDetailVo.setQuantity(Double.parseDouble(etQuantity.getText().toString().trim()));
                    preProductDetailVo.setSystemUnit(tvSystemUnit.getText().toString().trim());
                    preProductDetailVo.setUnitPrice(Double.parseDouble(etUnitPrice.getText().toString().trim()));
                    preProductDetailVo.setDiscount(Double.parseDouble(etDiscount.getText().toString().trim()));
                    preProductDetailVo.setAmount(Double.parseDouble(etAmount.getText().toString().trim()));
                    preProductDetailVo.setMaintainabilityId(frgMaintainability.getCheckedRadioButtonId());
                    preProductDetailVo.setRemark(etRemark.getText().toString().trim());
                    list.add(preProductDetailVo);//保存到内存中
                }
                Toast.makeText(myActivity,"保存成功",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
    /**
     * 初始化
     */
    private void initView() {
        StatusBarUtil.setStatusBar(myActivity, true);//设置当前界面是否是全屏模式（状态栏）
        StatusBarUtil.setStatusBarLightMode(myActivity, true);//状态栏文字颜色
        AppendOptionUtil.appendOption(myActivity,frgMaintainability,mapData.get("maintainability"),50,true);//维修性质
        //获取传递的参数 FittingsInfoVo
        Intent intent=getIntent();
        PreProductDetailVo preProductDetailVo= (PreProductDetailVo) intent.getSerializableExtra("preProductDetailVo");//获取产品材料信息
        position = intent.getIntExtra("position",-1);//获取产品材料列表的索引 判断是否修改
        //折扣
        discount = new BigDecimal(etDiscount.getText().toString()).divide(new BigDecimal(100));
        if (preProductDetailVo!=null){//产品材料（修改回填）
            tvFittingsCode.setText(preProductDetailVo.getFittingsCode().trim());
            tvFittingsName.setText(preProductDetailVo.getFittingsName().trim());
            tvVehicleType.setText(preProductDetailVo.getVehicleType().trim());
            etQuantity.setText(preProductDetailVo.getQuantity().toString());
            tvSystemUnit.setText(preProductDetailVo.getSystemUnit().trim());
            etUnitPrice.setText(preProductDetailVo.getUnitPrice().toString());
            etDiscount.setText(preProductDetailVo.getDiscount().toString());
            etAmount.setText(preProductDetailVo.getAmount().toString());
            frgMaintainability.check(preProductDetailVo.getMaintainabilityId());
            etRemark.setText(preProductDetailVo.getRemark().trim());
            title="修改产品材料";
        }
    }

    /**
     * 事件监听
     */
    private void setViewEventListener() {
        //配件编号选择
        llFittingsCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(myActivity,PeiJianActivity.class);
                startActivity(intent);
                myApplication.setActivity(myActivity);
            }
        });
        //配件编号选择
        tvFittingsCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(myActivity, PeiJianActivity.class);
                startActivity(intent);
                myApplication.setActivity(myActivity);
            }
        });
        //数量改变监听
        etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(etUnitPrice.getText().toString()) && !TextUtils.isEmpty(etDiscount.getText().toString())){
                    etAmount.setText(String.format(Locale.getDefault(),"%.2f",new BigDecimal(s.toString()).
                            multiply(new BigDecimal(etUnitPrice.getText().toString())).multiply(discount)));//金额
                }else {
                    etAmount.setText("");
                }
            }
        });
        //单价改变监听
        etUnitPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(etQuantity.getText().toString()) && !TextUtils.isEmpty(etDiscount.getText().toString())){
                    etAmount.setText(String.format(Locale.getDefault(),"%.2f",new BigDecimal(s.toString()).
                            multiply(new BigDecimal(etQuantity.getText().toString())).multiply(discount)));//金额
                }else {
                    etAmount.setText("");
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
                if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(etQuantity.getText().toString()) && !TextUtils.isEmpty(etUnitPrice.getText().toString())){
                    etAmount.setText(String.format(Locale.getDefault(),"%.2f",new BigDecimal(s.toString()).divide(new BigDecimal(100)).
                            multiply(new BigDecimal(etUnitPrice.getText().toString()))));//金额
                }else {
                    etAmount.setText("");
                }
            }
        });
    }
    /**
     * 用户进入或者返回页面时
     */
    @Override
    protected void onResume() {
        super.onResume();
        FittingsInfoVo fittingsInfoVo= myApplication.getFittingsInfoVo();//获取配件信息
        if (myApplication.getFittingsInfoVo()!=null){//配件信息（选择回填）
            tvFittingsCode.setText(fittingsInfoVo.getFittingsCode().trim());//配件编号
            tvFittingsName.setText(fittingsInfoVo.getFittingsName().trim());//配件名称
            tvVehicleType.setText(fittingsInfoVo.getVehicleType().trim());//车型
            etQuantity.setText("1");//数量
            tvSystemUnit.setText(fittingsInfoVo.getSystemUnit().trim());//单位
            etUnitPrice.setText(String.format(Locale.getDefault(),"%.2f",fittingsInfoVo.getSalesPrice()));//单价
            etAmount.setText(String.valueOf(new BigDecimal(etQuantity.getText().toString()).multiply(new BigDecimal(etUnitPrice.getText().toString())).multiply(discount)));//金额
            myApplication.setFittingsInfoVo(null);
        }
    }
    /**
     * 用户按返回键时
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!("请选择").equals(tvFittingsCode.getText().toString().trim())){
            if (keyCode==KeyEvent.KEYCODE_BACK){
                CommomDialog commomDialog= new CommomDialog(myActivity, R.style.dialog, "确定要退出吗?", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm==true){//确定按钮
                            finish();
                        }
                    }
                });
                commomDialog.setTitle("当前数据没有保存");
                commomDialog.show();//显示弹出框
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
