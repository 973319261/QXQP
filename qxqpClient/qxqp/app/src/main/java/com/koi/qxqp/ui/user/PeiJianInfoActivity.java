package com.koi.qxqp.ui.user;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.koi.qxqp.MyApplication;
import com.koi.qxqp.R;
import com.koi.qxqp.adapter.BannerPeiJianAdapter;
import com.koi.qxqp.bean.FittingsInfoVo;
import com.koi.qxqp.util.ServiceUrls;
import com.koi.qxqp.util.StatusBarUtil;
import com.koi.qxqp.util.Tools;
import com.koi.qxqp.widget.MyActionBar;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PeiJianInfoActivity extends AppCompatActivity {
    private Activity myActivity;
    private MyApplication myApplication;
    private Banner banner;//轮播
    private TextView tvFittingsName;//配件名称
    private TextView tvFittingsCode;//配件编号
    private TextView tvFittingsTypeName;//商品类型
    private TextView tvVehicleType;//适合车型
    private TextView tvSpecification;//规格
    private TextView tvSystemUnit;//单位
    private TextView tvSuppliersName;//供应商
    private TextView tvRemark;//备注
    private TextView tvMoney;//售价

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myActivity=this;
        myApplication= (MyApplication) getApplication();
        setContentView(R.layout.activity_peijian_info);
        banner = findViewById(R.id.banner_peijian_info);
        tvFittingsName = findViewById(R.id.tv_peijian_info_fittingsName);
        tvFittingsCode = findViewById(R.id.tv_peijian_info_fittingsCode);
        tvFittingsTypeName = findViewById(R.id.tv_peijian_info_fittingsTypeName);
        tvVehicleType = findViewById(R.id.tv_peijian_info_vehicleType);
        tvSpecification = findViewById(R.id.tv_peijian_info_specification);
        tvSystemUnit = findViewById(R.id.tv_peijian_info_systemUnit);
        tvSuppliersName = findViewById(R.id.tv_peijian_info_suppliersName);
        tvRemark = findViewById(R.id.tv_peijian_info_remark);
        tvMoney = findViewById(R.id.tv_peijian_info_money);
        initView();
        //======自定义myActionBar
        MyActionBar myActionBar=findViewById(R.id.myActionBar);
        myActionBar.setData("商品详情", R.drawable.ic_custom_actionbar_left_black, "", 1, 0, new MyActionBar.ActionBarClickListener() {
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
        FittingsInfoVo fittingsInfoVo= (FittingsInfoVo) getIntent().getSerializableExtra("fittingsInfoVo");
        if (fittingsInfoVo!=null){
            //显示轮播
            //==处理轮播图片
            String picture ="";
            if (fittingsInfoVo.getPictureName()!=null){
                 picture = fittingsInfoVo.getPictureName().trim();
            }

            String[] pictures = picture.split(";");
            List<String> listPicture=new ArrayList<>();//轮播图片链接集合
            for (String str:pictures){
                String strImage = ServiceUrls.getClientMethodUrl("getFittingsPicture") + "?pictureName=" + str;
                listPicture.add(strImage);
            }
            banner.setAdapter(new BannerPeiJianAdapter(listPicture, myActivity))
                    .setIndicator(new CircleIndicator(myActivity))//设置指示器
                    .setIndicatorSelectedColorRes(R.color.colorPrimary)//设置当前轮播指示器演示
                    .isAutoLoop(true)//允许自动轮播
                    .setDelayTime(2 * 1000);
            banner.start();
            tvFittingsName.setText(fittingsInfoVo.getFittingsName().trim());
            tvFittingsCode.setText(fittingsInfoVo.getFittingsCode().trim());
            tvFittingsTypeName.setText(fittingsInfoVo.getFittingsTypeName().trim());
            tvSpecification.setText(fittingsInfoVo.getSpecification().trim());
            tvSystemUnit.setText(fittingsInfoVo.getSystemUnit().trim());
            tvSuppliersName.setText(fittingsInfoVo.getSuppliersName().trim());
            tvRemark.setText(fittingsInfoVo.getRemark().trim());
            tvMoney.setText(Tools.setMoneySize(String.format(Locale.getDefault(),"%.2f",fittingsInfoVo.getSalesPrice())));
        }

    }
}
