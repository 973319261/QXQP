package com.koi.qxqp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.WindowManager;

import com.koi.qxqp.bean.AppendOptionVo;
import com.koi.qxqp.bean.FittingsInfoVo;
import com.koi.qxqp.bean.MaintenanceCusVo;
import com.koi.qxqp.bean.PreOtherCostDetailVo;
import com.koi.qxqp.bean.PreProductDetailVo;
import com.koi.qxqp.bean.PreRepairItemDetailVo;
import com.koi.qxqp.util.StatusBarUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyApplication extends Application {
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new MaterialHeader(context).setColorSchemeResources(R.color.colorPrimary);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
    @Override
    public void onCreate() {
        super.onCreate();

    }
    //==========设置全局的参数
    //客户信息
    private MaintenanceCusVo loginMaintenanceCus;
    //配件信息
    private FittingsInfoVo fittingsInfoVo;
    //下拉列表信息
    private Map<String, List<AppendOptionVo>> appendOptionVoMap;
    //维修项目
    private List<PreRepairItemDetailVo> repairItemDetailVoList=new ArrayList<>();
    //产品材料
    private List<PreProductDetailVo> productDetailVoList=new ArrayList<>();
    //其他费用
    private List<PreOtherCostDetailVo> otherCostDetailVoList=new ArrayList<>();
    //是否登录
    public boolean isLogin(){
        return null!=loginMaintenanceCus;
    }
    //alt+insert


    public MaintenanceCusVo getLoginMaintenanceCus() {
        return loginMaintenanceCus;
    }

    public void setLoginMaintenanceCus(MaintenanceCusVo loginMaintenanceCus) {
        this.loginMaintenanceCus = loginMaintenanceCus;
    }
    public Map<String, List<AppendOptionVo>> getAppendOptionVoMap() {
        return appendOptionVoMap;
    }

    public void setAppendOptionVoMap(Map<String, List<AppendOptionVo>> appendOptionVoMap) {
        this.appendOptionVoMap = appendOptionVoMap;
    }

    public  List<PreRepairItemDetailVo> getRepairItemDetailVoList() {
        return repairItemDetailVoList;
    }
    public void setRepairItemDetailVoList( List<PreRepairItemDetailVo> repairItemDetailVoList){
        this.repairItemDetailVoList=repairItemDetailVoList;
    }

    public List<PreProductDetailVo> getProductDetailVoList(){
        return productDetailVoList;
    }
    public void setProductDetailVoList(List<PreProductDetailVo> productDetailVoList){
        this.productDetailVoList=productDetailVoList;
    }
    public List<PreOtherCostDetailVo> getOtherCostDetailVoList(){
        return otherCostDetailVoList;
    }
    public void setOtherCostDetailVoList(List<PreOtherCostDetailVo> otherCostDetailVoList){
        this.otherCostDetailVoList=otherCostDetailVoList;
    }
    public FittingsInfoVo getFittingsInfoVo(){
        return fittingsInfoVo;
    }
    public void setFittingsInfoVo(FittingsInfoVo fittingsInfoVo){
        this.fittingsInfoVo=fittingsInfoVo;
    }
    //=====================
    private Activity loginActivity;
    private Activity activity;

    /**
     * 保存mainActivity对象
     * @param loginActivity activity
     */
    public void setLoginActivity(Activity loginActivity) {
        this.loginActivity = loginActivity;
    }
    /**
     * 关闭 closeMainActivity
     */
    public void closeLoginActivity() {
        if (loginActivity!=null){
            this.loginActivity.finish();
            loginActivity=null;
        }
    }
    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    /**
     * 关闭 closeMainActivity
     */
    public void closeActivity() {
        if (activity!=null){
            this.activity.finish();
            activity=null;
        }
    }
}
