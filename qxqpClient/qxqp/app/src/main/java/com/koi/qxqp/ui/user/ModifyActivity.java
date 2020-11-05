package com.koi.qxqp.ui.user;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;

import com.bigkoo.pickerview.TimePickerView;
import com.bryant.selectorlibrary.DSelectorPopup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.koi.qxqp.MyApplication;
import com.koi.qxqp.R;
import com.koi.qxqp.bean.AppendOptionVo;
import com.koi.qxqp.bean.MaintenanceCusVo;
import com.koi.qxqp.dialog.CommomDialog;
import com.koi.qxqp.dialog.InputDialog;
import com.koi.qxqp.ui.LoginActivity;
import com.koi.qxqp.util.AddressResolutionUtil;
import com.koi.qxqp.util.AppendOptionUtil;
import com.koi.qxqp.util.OkHttpTool;
import com.koi.qxqp.util.ServiceUrls;
import com.koi.qxqp.util.StatusBarUtil;
import com.koi.qxqp.util.Tools;
import com.koi.qxqp.widget.MyActionBar;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.cityjd.JDCityConfig;
import com.lljjcoder.style.cityjd.JDCityPicker;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.mingle.widget.LoadingView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModifyActivity extends AppCompatActivity {
    private Activity myActivity;
    private MyApplication myApplication;
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
    private Calendar selectedCalendar= Calendar.getInstance();
    private Date selectedDate=null;//选择数据
    private List<AppendOptionVo>  list=null;//单选列表
    Map<String,List<AppendOptionVo>> map=null;//获取后台返回的集合
    private TextView tvName;//姓名
    private TextView tvBirthday;//生日
    private TextView tvPhone;//手机号
    private TextView tvIdNumber;//身份证
    private TextView tvAddress;//地址
    private TextView tvVehicleType;//车型
    private TextView tvLicenseCode;//车牌
    private TextView tvFrameNum;//车架号
    private TextView tvEngineNum;//发动机号
    private TextView tvCustomerNum;//客户编号
    private TextView tvRepairMan;//送修人
    private TextView tvRepairTele;//送修人电话
    private TextView tvRegion;//所属区域
    private TextView tvDepartment;//所属部门
    private TextView tvCarder;//所属员工
    private TextView tvInsuranceCom;//保险公司
    private TextView tvInsuranceSpe;//保险种类
    private TextView tvInitialStartDate;//保险起始日
    private TextView tvInitialEndDate;//保险结算日
    private TextView tvDriveDate;//行驶证年审
    private TextView tvCustomerLevel;//客户等级
    private TextView tvCustomerSou;//客户来源
    private TextView tvCustomerType;//客户类别
    private TextView tvDrivingDate;//驾驶证年审
    private TextView tvInputPerson;//录入人

    private LinearLayout llName;//姓名
    private LinearLayout llBirthday;//生日
    private LinearLayout llPhone;//手机号
    private LinearLayout llIdNumber;//身份证
    private LinearLayout llAddress;//地址
    private LinearLayout llVehicleType;//车型
    private LinearLayout llLicenseCode;//车牌
    private LinearLayout llFrameNum;//车架号
    private LinearLayout llEngineNum;//发动机号
    private LinearLayout llCustomerNum;//客户编号
    private LinearLayout llRepairMan;//送修人
    private LinearLayout llRepairTele;//送修人电话
    private LinearLayout llRegion;//所属区域
    private LinearLayout llDepartment;//所属部门
    private LinearLayout llCarder;//所属员工
    private LinearLayout llInsuranceCom;//保险公司
    private LinearLayout llInsuranceSpe;//保险种类
    private LinearLayout llInitialStartDate;//保险起始日
    private LinearLayout llInitialEndDate;//保险结算日
    private LinearLayout llDriveDate;//行驶证年审
    private LinearLayout llCustomerLevel;//客户等级
    private LinearLayout llCustomerSou;//客户来源
    private LinearLayout llCustomerType;//客户类别
    private LinearLayout llDrivingDate;//驾驶证年审
    private LinearLayout llInputPerson;//录入人
    private LoadingView llLoadView;//加载中页面
    private LinearLayout llFailure;//加载失败页面
    private ScrollView svContent;//内容页面
    private MaintenanceCusVo maintenanceCusVo;//数据

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myActivity=this;
        myApplication= (MyApplication) getApplication();
        setContentView(R.layout.activity_modify);
        tvName = findViewById(R.id.tv_modify_name);
        tvBirthday = findViewById(R.id.tv_modify_birthday);
        tvPhone = findViewById(R.id.tv_modify_phone);
        tvIdNumber = findViewById(R.id.tv_modify_idNumber);
        tvAddress = findViewById(R.id.tv_modify_address);
        tvVehicleType = findViewById(R.id.tv_modify_vehicleType);
        tvLicenseCode = findViewById(R.id.tv_modify_licenseCode);
        tvFrameNum = findViewById(R.id.tv_modify_frameNum);
        tvEngineNum = findViewById(R.id.tv_modify_engineNum);
        tvCustomerNum = findViewById(R.id.tv_modify_customerNum);
        tvRepairMan = findViewById(R.id.tv_modify_repairMan);
        tvRepairTele = findViewById(R.id.tv_modify_repairTele);
        tvRegion = findViewById(R.id.tv_modify_region);
        tvDepartment = findViewById(R.id.tv_modify_department);
        tvCarder = findViewById(R.id.tv_modify_carder);
        tvInsuranceCom = findViewById(R.id.tv_modify_insuranceCom);
        tvInsuranceSpe = findViewById(R.id.tv_modify_insuranceSpe);
        tvInitialStartDate = findViewById(R.id.tv_modify_initialStartDate);
        tvInitialEndDate = findViewById(R.id.tv_modify_initialEndDate);
        tvDriveDate = findViewById(R.id.tv_modify_driveDate);
        tvCustomerLevel = findViewById(R.id.tv_modify_customerLevel);
        tvCustomerSou = findViewById(R.id.tv_modify_customerSou);
        tvCustomerType = findViewById(R.id.tv_modify_customerType);
        tvDrivingDate = findViewById(R.id.tv_modify_drivingDate);
        tvInputPerson = findViewById(R.id.tv_modify_inputPerson);

        llName = findViewById(R.id.ll_modify_name);
        llBirthday = findViewById(R.id.ll_modify_birthday);
        llPhone = findViewById(R.id.ll_modify_phone);
        llIdNumber = findViewById(R.id.ll_modify_idNumber);
        llAddress = findViewById(R.id.ll_modify_address);
        llVehicleType = findViewById(R.id.ll_modify_vehicleType);
        llLicenseCode = findViewById(R.id.ll_modify_licenseCode);
        llFrameNum = findViewById(R.id.ll_modify_frameNum);
        llEngineNum = findViewById(R.id.ll_modify_engineNum);
        llCustomerNum = findViewById(R.id.ll_modify_customerNum);
        llRepairMan = findViewById(R.id.ll_modify_repairMan);
        llRepairTele = findViewById(R.id.ll_modify_repairTele);
        llRegion = findViewById(R.id.ll_modify_region);
        llDepartment = findViewById(R.id.ll_modify_department);
        llCarder = findViewById(R.id.ll_modify_carder);
        llInsuranceCom = findViewById(R.id.ll_modify_insuranceCom);
        llInsuranceSpe = findViewById(R.id.ll_modify_insuranceSpe);
        llInitialStartDate = findViewById(R.id.ll_modify_initialStartDate);
        llInitialEndDate = findViewById(R.id.ll_modify_initialEndDate);
        llDriveDate = findViewById(R.id.ll_modify_driveDate);
        llCustomerLevel = findViewById(R.id.ll_modify_customerLevel);
        llCustomerSou = findViewById(R.id.ll_modify_customerSou);
        llCustomerType = findViewById(R.id.ll_modify_customerType);
        llDrivingDate = findViewById(R.id.ll_modify_drivingDate);
        llInputPerson = findViewById(R.id.ll_modify_inputPerson);
        llLoadView = findViewById(R.id.loadView);
        llFailure=findViewById(R.id.ll_failure);
        svContent = findViewById(R.id.sv_order_content);
        setViewEventListener();//监听事件
        initView();

        //======自定义myActionBar
        MyActionBar myActionBar=findViewById(R.id.myActionBar);
        myActionBar.setData("编辑资料", R.drawable.ic_custom_actionbar_left_black, "", 1, 0, new MyActionBar.ActionBarClickListener() {
            @Override
            public void onLeftClick() {//返回
                finish();//关闭页面
            }

            @Override
            public void onRightClick() {
            }
        });
    }


    /*页面初始化*/
    private void initView() {
        StatusBarUtil.setStatusBar(myActivity, true);//设置当前界面是否是全屏模式（状态栏）
        StatusBarUtil.setStatusBarLightMode(myActivity, true);//状态栏文字颜色
        //回填修改资料数据
        if (myApplication.isLogin()){
            maintenanceCusVo = myApplication.getLoginMaintenanceCus();
            map=myApplication.getAppendOptionVoMap();
            tvName.setText(maintenanceCusVo.getOwner().trim());
            tvBirthday.setText(maintenanceCusVo.getBirthday().trim());
            tvPhone.setText(maintenanceCusVo.getMobilePhone().trim());
            tvIdNumber.setText(maintenanceCusVo.getIdNumber().trim());
            tvAddress.setText(maintenanceCusVo.getAddress().trim());
            tvVehicleType.setText(maintenanceCusVo.getVehicleType().trim());
            tvLicenseCode.setText(maintenanceCusVo.getLicenseCode().trim());
            tvFrameNum.setText(maintenanceCusVo.getFrameNum().trim());
            tvEngineNum.setText(maintenanceCusVo.getEngineNum().trim());
            tvCustomerNum.setText(maintenanceCusVo.getCustomerNum().trim());
            tvRepairMan.setText(maintenanceCusVo.getRepairMan().trim());
            tvRepairTele.setText(maintenanceCusVo.getRepairTele().trim());
            tvRegion.setText(maintenanceCusVo.getRegionName().trim());
            tvDepartment.setText(maintenanceCusVo.getDepartmentName().trim());
            tvCarder.setText(maintenanceCusVo.getCarderName().trim());
            tvInsuranceCom.setText(maintenanceCusVo.getInsuranceComName().trim());
            tvInsuranceSpe.setText(maintenanceCusVo.getInsuranceSpeName().trim());
            tvInitialStartDate.setText(maintenanceCusVo.getInitialStartDate().trim());
            tvInitialEndDate.setText(maintenanceCusVo.getInitialEndDate().trim());
            tvDriveDate.setText(maintenanceCusVo.getDriveDate().trim());
            tvCustomerLevel.setText(maintenanceCusVo.getCustomerLevel().trim());
            tvCustomerSou.setText(maintenanceCusVo.getCustomerSou().trim());
            tvCustomerType.setText(maintenanceCusVo.getCustomerType().trim());
            tvDrivingDate.setText(maintenanceCusVo.getDrivingDate().trim());
            tvInputPerson.setText(maintenanceCusVo.getInputPerson().trim());
            llLoadView.setVisibility(View.GONE);//隐藏加载动画
            llFailure.setVisibility(View.GONE);//隐藏加载失败动画
            svContent.setVisibility(View.VISIBLE);//显示内容
        }else {
            llLoadView.setVisibility(View.GONE);//加载动画
            llFailure.setVisibility(View.VISIBLE);//加载失败动画
            svContent.setVisibility(View.GONE);//内容
        }
    }


    /**
     * 监听事件
     */
    private void setViewEventListener() {
        //姓名
        llName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final InputDialog inputDialog = new InputDialog("请输入名字",tvName.getText().toString(),InputType.TYPE_CLASS_TEXT,5,new InputDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick(Dialog dialog,String content) {
                        maintenanceCusVo.setOwner(content);
                        inputSubmit(dialog,content,tvName);
                    }
                });
                inputDialog.show(getSupportFragmentManager(),"");
            }
        });
        //生日
        llBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    selectedDate = sf.parse(tvBirthday.getText().toString());
                    selectedCalendar.setTime(selectedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                TimePickerView pvTime = new TimePickerView.Builder(myActivity, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        maintenanceCusVo.setBirthday(sf.format(date));
                        inputSubmit(null,sf.format(date),tvBirthday);
                    }
                }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
                        .setContentSize(18)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("出生日期")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .setSubmitColor(Color.parseColor("#fb7299"))//确定按钮文字颜色
                        .setCancelColor(Color.parseColor("#585858"))//取消按钮文字颜色*/
                        .setDate(selectedCalendar)// 如果不设置的话，默认是系统时间*/
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .build();
                pvTime.show();
            }
        });
       /* //手机号
        llPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final InputDialog inputDialog = new InputDialog("请输入手机号",tvPhone.getText().toString(),InputType.TYPE_CLASS_PHONE,11,new InputDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick(Dialog dialog,String content) {
                        maintenanceCusVo.setMobilePhone(content);
                        inputSubmit(dialog,content,tvPhone);
                    }
                });
                inputDialog.show(getSupportFragmentManager(),"");
            }
        });*/
        //身份证号
        llIdNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final InputDialog inputDialog = new InputDialog("请输入身份证号",tvIdNumber.getText().toString(),InputType.TYPE_CLASS_TEXT,18,new InputDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick(Dialog dialog,String content) {
                        maintenanceCusVo.setIdNumber(content);
                        inputSubmit(dialog,content,tvIdNumber);
                    }
                });
                inputDialog.show(getSupportFragmentManager(),"");
            }
        });
        //地址
        llAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //截取地址（省市区）
                Map<String,String> addressMap = AddressResolutionUtil.addressResolution(tvAddress.getText().toString());
                String province="",city="",district="";
                if (addressMap!=null){
                    province=addressMap.get("province")==null?"":addressMap.get("province");
                    city=addressMap.get("city")==null?"":addressMap.get("city");
                    district=addressMap.get("district")==null?"":addressMap.get("district");
                }
                //申明对象
                CityPickerView mPicker = new CityPickerView();
                mPicker.init(myActivity);
                CityConfig cityConfig = new CityConfig.Builder()
                        .title("选择家庭地址")//标题
                        .titleTextSize(18)//标题文字大小
                        .titleTextColor("#585858")//标题文字颜  色
                        .titleBackgroundColor("#E9E9E9")//标题栏背景色
                        .confirTextColor("#fb7299")//确认按钮文字颜色
                        .confirmText("确定")//确认按钮文字
                        .confirmTextSize(16)//确认按钮文字大小
                        .cancelTextColor("#585858")//取消按钮文字颜色
                        .cancelText("取消")//取消按钮文字
                        .cancelTextSize(16)//取消按钮文字大小
                        .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)//显示类，只显示省份一级，显示省市两级还是显示省市区三级
                        .showBackground(true)//是否显示半透明背景
                        .visibleItemsCount(7)//显示item的数量
                        .province(province)//默认显示的省份
                        .city(city)//默认显示省份下面的城市
                        .district(district)//默认显示省市下面的区县数据
                        .provinceCyclic(true)//省份滚轮是否可以循环滚动
                        .cityCyclic(true)//城市滚轮是否可以循环滚动
                        .districtCyclic(true)//区县滚轮是否循环滚动
                        .drawShadows(true)//滚轮不显示模糊效果
                        .setLineColor("#fb7299")//中间横线的颜色
                        .setLineHeigh(0)//中间横线的高度
                        .setShowGAT(true)//是否显示港澳台数据，默认不显示
                        .build();
                //设置自定义的属性配置
                mPicker.setConfig(cityConfig);
                //监听选择点击事件及返回结果
                mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        String content = province.getName() + city.getName() + district.getName();
                        maintenanceCusVo.setAddress(content);
                        inputSubmit(null, content, tvAddress);
                    }
                });
                //显示
                mPicker.showCityPicker();
            }
        });
        //车型
        llVehicleType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final InputDialog inputDialog = new InputDialog("请输入车型",tvVehicleType.getText().toString(),InputType.TYPE_CLASS_TEXT,10,new InputDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick(Dialog dialog,String content) {
                        maintenanceCusVo.setVehicleType(content);
                        inputSubmit(dialog,content,tvVehicleType);
                    }
                });
                inputDialog.show(getSupportFragmentManager(),"");
            }
        });
        //车牌
        llLicenseCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final InputDialog inputDialog = new InputDialog("请输入车牌",tvLicenseCode.getText().toString(),InputType.TYPE_CLASS_TEXT,7,new InputDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick(Dialog dialog,String content) {
                        if (Tools.isCarNum(content)){
                            inputSubmit(dialog,content,tvLicenseCode);
                        }else {
                            Toast.makeText(myActivity,"请输入正确的车牌号",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                inputDialog.show(getSupportFragmentManager(),"");
            }
        });
        //车架号
        llFrameNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final InputDialog inputDialog = new InputDialog("请输入车架号",tvFrameNum.getText().toString(),InputType.TYPE_CLASS_TEXT,15,new InputDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick(Dialog dialog,String content) {
                        maintenanceCusVo.setFrameNum(content);
                        inputSubmit(dialog,content,tvFrameNum);
                    }
                });
                inputDialog.show(getSupportFragmentManager(),"");
            }
        });
        //发动机号
        llEngineNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final InputDialog inputDialog = new InputDialog("请输入发动机号",tvEngineNum.getText().toString(),InputType.TYPE_CLASS_TEXT,15,new InputDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick(Dialog dialog,String content) {
                        maintenanceCusVo.setEngineNum(content);
                        inputSubmit(dialog,content,tvEngineNum);
                    }
                });
                inputDialog.show(getSupportFragmentManager(),"");
            }
        });
        //送修人
        llRepairMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final InputDialog inputDialog = new InputDialog("请输入送修人",tvRepairMan.getText().toString(),InputType.TYPE_CLASS_TEXT,5,new InputDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick(Dialog dialog,String content) {
                        maintenanceCusVo.setRepairMan(content);
                        inputSubmit(dialog,content,tvRepairMan);
                    }
                });
                inputDialog.show(getSupportFragmentManager(),"");
            }
        });
        //送修人电话
        llRepairTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final InputDialog inputDialog = new InputDialog("请输入送修人电话",tvRepairTele.getText().toString(),InputType.TYPE_CLASS_PHONE,11,new InputDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick(Dialog dialog,String content) {
                        maintenanceCusVo.setRepairTele(content);
                        inputSubmit(dialog,content,tvRepairTele);
                    }
                });
                inputDialog.show(getSupportFragmentManager(),"");
            }
        });
        //所属区域
        llRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list= (List<AppendOptionVo>) map.get("region");
                final AppendOptionUtil appendOptionUtil=new AppendOptionUtil(list);
                int option=appendOptionUtil.getItemCheckedByName(tvRegion.getText().toString());
                final DSelectorPopup dSelectorPopup=new DSelectorPopup(myActivity,appendOptionUtil.getItemName());
                dSelectorPopup.setTextcolor_unchecked(getResources().getColor(R.color.colorPrimary))
                        .setOffset(option)//默认选中
                        .setButtonText("确定")
                        .setGradual_color(0xffD81B60)
                        .setTitleText("所属区域")
                        .setTitleColor(getResources().getColor(R.color.colorPrimary)).build();
                dSelectorPopup.popOutShadow(v);
                dSelectorPopup.setSelectorListener(new DSelectorPopup.SelectorClickListener() {
                    @Override
                    public void onSelectorClick(int position, String text) {
                        maintenanceCusVo.setRegionId(appendOptionUtil.getItemIdByName(text));
                        maintenanceCusVo.setRegionName(text);
                        inputSubmit(null,text,tvRegion);
                        dSelectorPopup.dismissPopup();
                    }
                });
            }
        });
        //所属部门
        llDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list= (List<AppendOptionVo>) map.get("department");
                final AppendOptionUtil appendOptionUtil=new AppendOptionUtil(list);
                int option=appendOptionUtil.getItemCheckedByName(tvDepartment.getText().toString());
                final DSelectorPopup dSelectorPopup=new DSelectorPopup(myActivity,appendOptionUtil.getItemName());
                dSelectorPopup.setTextcolor_unchecked(getResources().getColor(R.color.colorPrimary))
                        .setOffset(option)//默认选中
                        .setButtonText("确定")
                        .setGradual_color(0xffD81B60)
                        .setTitleText("所属部门")
                        .setTitleColor(getResources().getColor(R.color.colorPrimary)).build();
                dSelectorPopup.popOutShadow(v);
                dSelectorPopup.setSelectorListener(new DSelectorPopup.SelectorClickListener() {
                    @Override
                    public void onSelectorClick(int position, String text) {
                        maintenanceCusVo.setDepartmentId(appendOptionUtil.getItemIdByName(text));
                        maintenanceCusVo.setDepartmentName(text);
                        inputSubmit(null,text,tvDepartment);
                        dSelectorPopup.dismissPopup();
                    }
                });
            }
        });
        //所属员工
        llCarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list= (List<AppendOptionVo>) map.get("carder");
                final AppendOptionUtil appendOptionUtil=new AppendOptionUtil(list);
                int option=appendOptionUtil.getItemCheckedByName(tvCarder.getText().toString());
                final DSelectorPopup dSelectorPopup=new DSelectorPopup(myActivity,appendOptionUtil.getItemName());
                dSelectorPopup.setTextcolor_unchecked(getResources().getColor(R.color.colorPrimary))
                        .setOffset(option)//默认选中
                        .setButtonText("确定")
                        .setGradual_color(0xffD81B60)
                        .setTitleText("所属员工")
                        .setTitleColor(getResources().getColor(R.color.colorPrimary)).build();
                dSelectorPopup.popOutShadow(v);
                dSelectorPopup.setSelectorListener(new DSelectorPopup.SelectorClickListener() {
                    @Override
                    public void onSelectorClick(int position, String text) {
                        maintenanceCusVo.setCarderId(appendOptionUtil.getItemIdByName(text));
                        maintenanceCusVo.setCarderName(text);
                        inputSubmit(null,text,tvCarder);
                        dSelectorPopup.dismissPopup();
                    }
                });
            }
        });
        //保险公司
        llInsuranceCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list= (List<AppendOptionVo>) map.get("insuranceCom");
                final AppendOptionUtil appendOptionUtil=new AppendOptionUtil(list);
                int option=appendOptionUtil.getItemCheckedByName(tvInsuranceCom.getText().toString());
                final DSelectorPopup dSelectorPopup=new DSelectorPopup(myActivity,appendOptionUtil.getItemName());
                dSelectorPopup.setTextcolor_unchecked(getResources().getColor(R.color.colorPrimary))
                        .setOffset(option)//默认选中
                        .setButtonText("确定")
                        .setGradual_color(0xffD81B60)
                        .setTitleText("保险公司")
                        .setTitleColor(getResources().getColor(R.color.colorPrimary)).build();
                dSelectorPopup.popOutShadow(v);
                dSelectorPopup.setSelectorListener(new DSelectorPopup.SelectorClickListener() {
                    @Override
                    public void onSelectorClick(int position, String text) {
                        maintenanceCusVo.setInsuranceComId(appendOptionUtil.getItemIdByName(text));
                        maintenanceCusVo.setInsuranceComName(text);
                        inputSubmit(null,text,tvInsuranceCom);
                        dSelectorPopup.dismissPopup();
                    }
                });
            }
        });
        //保险种类
        llInsuranceSpe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list= (List<AppendOptionVo>) map.get("insuranceSpe");
                final AppendOptionUtil appendOptionUtil=new AppendOptionUtil(list);
                int option=appendOptionUtil.getItemCheckedByName(tvInsuranceSpe.getText().toString());
                final DSelectorPopup dSelectorPopup=new DSelectorPopup(myActivity,appendOptionUtil.getItemName());
                dSelectorPopup.setTextcolor_unchecked(getResources().getColor(R.color.colorPrimary))
                        .setOffset(option)//默认选中
                        .setButtonText("确定")
                        .setGradual_color(0xffD81B60)
                        .setTitleText("保险种类")
                        .setTitleColor(getResources().getColor(R.color.colorPrimary)).build();
                dSelectorPopup.popOutShadow(v);
                dSelectorPopup.setSelectorListener(new DSelectorPopup.SelectorClickListener() {
                    @Override
                    public void onSelectorClick(int position, String text) {
                        maintenanceCusVo.setInsuranceSpeId(appendOptionUtil.getItemIdByName(text));
                        maintenanceCusVo.setInsuranceSpeName(text);
                        inputSubmit(null,text,tvInsuranceSpe);
                        dSelectorPopup.dismissPopup();
                    }
                });
            }
        });
        //保险起始日
        llInitialStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    selectedDate = sf.parse(tvInitialStartDate.getText().toString());
                    selectedCalendar.setTime(selectedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                TimePickerView pvTime = new TimePickerView.Builder(myActivity, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        maintenanceCusVo.setInitialStartDate(sf.format(date));
                        inputSubmit(null,sf.format(date),tvInitialStartDate);
                    }
                }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
                        .setContentSize(18)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("保险起始日期")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .setSubmitColor(Color.parseColor("#fb7299"))//确定按钮文字颜色
                        .setCancelColor(Color.parseColor("#585858"))//取消按钮文字颜色*/
                        .setDate(selectedCalendar)// 如果不设置的话，默认是系统时间*/
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .build();
                pvTime.show();
            }
        });
        //保险终止日
        llInitialEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    selectedDate = sf.parse(tvInitialEndDate.getText().toString());
                    selectedCalendar.setTime(selectedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                TimePickerView pvTime = new TimePickerView.Builder(myActivity, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        maintenanceCusVo.setInitialEndDate(sf.format(date));
                        inputSubmit(null,sf.format(date),tvInitialEndDate);
                    }
                }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
                        .setContentSize(18)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("保险终止日期")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .setSubmitColor(Color.parseColor("#fb7299"))//确定按钮文字颜色
                        .setCancelColor(Color.parseColor("#585858"))//取消按钮文字颜色*/
                        .setDate(selectedCalendar)// 如果不设置的话，默认是系统时间*/
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .build();
                pvTime.show();
            }
        });
        //行驶证年审
        llDrivingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    selectedDate = sf.parse(tvDrivingDate.getText().toString());
                    selectedCalendar.setTime(selectedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                TimePickerView pvTime = new TimePickerView.Builder(myActivity, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        maintenanceCusVo.setDrivingDate(sf.format(date));
                        inputSubmit(null,sf.format(date),tvDrivingDate);
                    }
                }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
                        .setContentSize(18)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("行驶证年审日期")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .setSubmitColor(Color.parseColor("#fb7299"))//确定按钮文字颜色
                        .setCancelColor(Color.parseColor("#585858"))//取消按钮文字颜色*/
                        .setDate(selectedCalendar)// 如果不设置的话，默认是系统时间*/
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .build();
                pvTime.show();
            }
        });
        //客户等级
        llCustomerLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list= (List<AppendOptionVo>) map.get("customerLevel");
                final AppendOptionUtil appendOptionUtil=new AppendOptionUtil(list);
                int option=appendOptionUtil.getItemCheckedByName(tvCustomerLevel.getText().toString());
                final DSelectorPopup dSelectorPopup=new DSelectorPopup(myActivity,appendOptionUtil.getItemName());
                dSelectorPopup.setTextcolor_unchecked(getResources().getColor(R.color.colorPrimary))
                        .setOffset(option)//默认选中
                        .setButtonText("确定")
                        .setGradual_color(0xffD81B60)
                        .setTitleText("客户等级")
                        .setTitleColor(getResources().getColor(R.color.colorPrimary)).build();
                dSelectorPopup.popOutShadow(v);
                dSelectorPopup.setSelectorListener(new DSelectorPopup.SelectorClickListener() {
                    @Override
                    public void onSelectorClick(int position, String text) {
                        maintenanceCusVo.setCustomerLevelId(appendOptionUtil.getItemIdByName(text));
                        maintenanceCusVo.setCustomerLevel(text);
                        inputSubmit(null,text,tvCustomerLevel);
                        dSelectorPopup.dismissPopup();
                    }
                });
            }
        });
        //客户来源
        llCustomerSou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list= (List<AppendOptionVo>) map.get("customerSou");
                final AppendOptionUtil appendOptionUtil=new AppendOptionUtil(list);
                int option=appendOptionUtil.getItemCheckedByName(tvCustomerSou.getText().toString());
                final DSelectorPopup dSelectorPopup=new DSelectorPopup(myActivity,appendOptionUtil.getItemName());
                dSelectorPopup.setTextcolor_unchecked(getResources().getColor(R.color.colorPrimary))
                        .setOffset(option)//默认选中
                        .setButtonText("确定")
                        .setGradual_color(0xffD81B60)
                        .setTitleText("客户来源")
                        .setTitleColor(getResources().getColor(R.color.colorPrimary)).build();
                dSelectorPopup.popOutShadow(v);
                dSelectorPopup.setSelectorListener(new DSelectorPopup.SelectorClickListener() {
                    @Override
                    public void onSelectorClick(int position, String text) {
                        maintenanceCusVo.setCustomerSouId(appendOptionUtil.getItemIdByName(text));
                        maintenanceCusVo.setCustomerSou(text);
                        inputSubmit(null,text,tvCustomerSou);
                        dSelectorPopup.dismissPopup();
                    }
                });
            }
        });
        //客户类别
        llCustomerType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list= (List<AppendOptionVo>) map.get("customerType");
                final AppendOptionUtil appendOptionUtil=new AppendOptionUtil(list);
                int option=appendOptionUtil.getItemCheckedByName(tvCustomerType.getText().toString());
                final DSelectorPopup dSelectorPopup=new DSelectorPopup(myActivity,appendOptionUtil.getItemName());
                dSelectorPopup.setTextcolor_unchecked(getResources().getColor(R.color.colorPrimary))
                        .setOffset(option)//默认选中
                        .setButtonText("确定")
                        .setGradual_color(0xffD81B60)
                        .setTitleText("客户类别")
                        .setTitleColor(getResources().getColor(R.color.colorPrimary)).build();
                dSelectorPopup.popOutShadow(v);
                dSelectorPopup.setSelectorListener(new DSelectorPopup.SelectorClickListener() {
                    @Override
                    public void onSelectorClick(int position, String text) {
                        maintenanceCusVo.setCustomerTypeId(appendOptionUtil.getItemIdByName(text));
                        maintenanceCusVo.setCustomerType(text);
                        inputSubmit(null,text,tvCustomerType);
                        dSelectorPopup.dismissPopup();
                    }
                });
            }
        });
        //驾驶证年审
        llDriveDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    selectedDate = sf.parse(tvDriveDate.getText().toString());
                    selectedCalendar.setTime(selectedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                TimePickerView pvTime = new TimePickerView.Builder(myActivity, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        maintenanceCusVo.setDriveDate(sf.format(date));
                        inputSubmit(null,sf.format(date),tvDriveDate);
                    }
                }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
                        .setContentSize(18)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("驾驶证年审日期")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .setSubmitColor(Color.parseColor("#fb7299"))//确定按钮文字颜色
                        .setCancelColor(Color.parseColor("#585858"))//取消按钮文字颜色*/
                        .setDate(selectedCalendar)// 如果不设置的话，默认是系统时间*/
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .build();
                pvTime.show();
            }
        });
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
    }

    /**
     * 资料数据提交
     */
    private void inputSubmit(final Dialog dialog, final String content, final TextView textView){
        String url= ServiceUrls.getCommonMethodUrl("updateMaintenanceCus");
        Map<String,Object> map= JSON.parseObject(JSON.toJSONString(maintenanceCusVo),Map.class);
        OkHttpTool.httpPost(url, map, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, String response, Exception exception) {
                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isSuccess && responseCode==200){
                            textView.setText(content);
                            if (dialog!=null){
                                dialog.dismiss();
                            }
                            Toast.makeText(myActivity,"修改成功",Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(myActivity,"修改失败，请稍后再试",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    /**
     * 日期选择
     * @param title
     * @param textView
     */
    private void timePicker(String title,final TextView textView){

    }
}
