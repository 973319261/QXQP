package com.koi.qxqp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koi.qxqp.MyApplication;
import com.koi.qxqp.R;
import com.koi.qxqp.bean.AppendOptionVo;
import com.koi.qxqp.bean.PreRepairItemDetailVo;
import com.koi.qxqp.util.AppendOptionUtil;
import com.koi.qxqp.util.RecycleItemTouchHelper;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RvWeiXiuAdapter extends RecyclerView.Adapter<RvWeiXiuAdapter.ViewHolder>implements RecycleItemTouchHelper.ItemTouchHelperCallback{
    private Activity myActivity;//上下文
    private MyApplication myApplication;//全局变量
    private List<PreRepairItemDetailVo> list;//数据
    Map<String,List<AppendOptionVo>> mapData;//获取后台返回的集合
    private OnItemClickListener onItemClickListener;
    private TextView tvNum;//维修项目个数
    private LinearLayout llEmpty;//空数据图片
    public RvWeiXiuAdapter(Activity activity,List<PreRepairItemDetailVo> list){
        this.myActivity=activity;
        this.list=list;
        myApplication= (MyApplication) myActivity.getApplication();
        mapData=myApplication.getAppendOptionVoMap();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 控件提示
     * @param tvNum 维修项目个数
     * @param llEmpty //空数据图片
     */
    public void setViewHint(TextView tvNum, LinearLayout llEmpty){
        this.tvNum=tvNum;
        this.llEmpty=llEmpty;
    }
    //创建ViewHolder --设置子项的布局
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_home_weixiu,parent,false);
        return new RvWeiXiuAdapter.ViewHolder(view);
    }

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final PreRepairItemDetailVo preRepairItemDetailVo=list.get(position);
        if (preRepairItemDetailVo!=null){
            if (preRepairItemDetailVo.getRepairItemId()>0){
                AppendOptionUtil appendOptionUtil=new AppendOptionUtil(mapData.get("repairItem"));
                holder.tvRepairItemName.setText(appendOptionUtil.getItemNameById(preRepairItemDetailVo.getRepairItemId()).trim());
            }
            if (preRepairItemDetailVo.getMaintenanceId()>0){
                AppendOptionUtil appendOptionUtil=new AppendOptionUtil(mapData.get("maintenance"));
                holder.tvMaintenanceName.setText(appendOptionUtil.getItemNameById(preRepairItemDetailVo.getMaintenanceId()).trim());
            }
            if (preRepairItemDetailVo.getRepairCharge()!=null){
                holder.tvRepairCharge.setText(String.format(Locale.getDefault(),"%.2f",preRepairItemDetailVo.getRepairCharge()));
            }
            if (preRepairItemDetailVo.getDiscount()!=null){
                holder.tvDiscount.setText(String.format(Locale.getDefault(),"%.0f",preRepairItemDetailVo.getDiscount()));
            }
            if (preRepairItemDetailVo.getAmountPaid()!=null){
                holder.tvAmountPaid.setText(String.format(Locale.getDefault(),"%.2f",preRepairItemDetailVo.getAmountPaid()));
            }
            if (preRepairItemDetailVo.getMaintainabilityId()>0){
                AppendOptionUtil appendOptionUtil=new AppendOptionUtil(mapData.get("maintainability"));
                holder.tvMaintainabilityName.setText(appendOptionUtil.getItemNameById(preRepairItemDetailVo.getMaintainabilityId()).trim());
            }
            if (preRepairItemDetailVo.getRemark()!=null){
                holder.tvRemark.setText(preRepairItemDetailVo.getRemark().trim());
            }
            //=======列表每一项点击 item点击事件
            if (onItemClickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //判断是否设置点击事件
                        if (onItemClickListener!=null){
                            onItemClickListener.onItemClick(preRepairItemDetailVo,position);
                        }
                    }
                });
            }
        }
    }

    //返回数据条数
    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 对外方法，用于添加数据
     *
     * @param listAdd  要添加的数据
     * @param isRefresh 整体刷新
     */
    public void addItem(List<PreRepairItemDetailVo> listAdd, boolean isRefresh) {
        if (isRefresh){
            //如果是整体加载，需要先清空数据列表
            this.list.clear();
            if (listAdd!=null){
                //添加数据
                this.list.addAll(listAdd);
            }
            //通知RecyclerView进行改变--整体
            notifyDataSetChanged();
        }else {
          //添加数据
          int nowCount=this.list.size();
          if (listAdd!=null){
              this.list.addAll(listAdd);
          }
          //通知RecyclerView进行改变 -- 局部刷新
          notifyItemRangeChanged(nowCount,list.size());
      }
    }

    /**
     * 对外方法，用于修改数据
     * @param listModify 修改的数据
     * @param position 修改的索引
     */
    public void modifyItem(PreRepairItemDetailVo listModify,int position) {
       if (listModify!=null){
           notifyItemChanged(position);
       }
    }

    /**
     * 移除列表
     * @param positon
     */
    @Override
    public void onItemDelete(int positon) {
        list.remove(positon);//从列表中移除
        notifyItemRemoved(positon);//更新移除的列表
        myApplication.getRepairItemDetailVoList().remove(positon);//从全局变量中移除
        if (list.size()>0){
            llEmpty.setVisibility(View.GONE);
        }else {
            llEmpty.setVisibility(View.VISIBLE);
        }
        tvNum.setText(String.format(Locale.getDefault(),"%d个维修项目",list.size()));
        Toast.makeText(myActivity,"移除成功",Toast.LENGTH_SHORT).show();
    }

    /**
     * 列表顺序移动
     * @param fromPosition
     * @param toPosition
     */
    @Override
    public void onMove(int fromPosition, int toPosition) {
        Collections.swap(list,fromPosition,toPosition);//交换数据
        notifyItemMoved(fromPosition,toPosition);
        myApplication.getRepairItemDetailVoList().clear();//从全局变量全部移除
        myApplication.getRepairItemDetailVoList().addAll(list);//重新添加
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvRepairItemName;//修理项目
        TextView tvMaintenanceName;//维修工艺
        TextView tvRepairCharge;//修理费
        TextView tvDiscount;//折扣
        TextView tvAmountPaid;//实收金额
        TextView tvMaintainabilityName;//维修性质
        TextView tvRemark;//备注
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRepairItemName= itemView.findViewById(R.id.tv_home_weixiu_repairItemName);
            tvMaintenanceName=itemView.findViewById(R.id.tv_home_weixiu_maintenanceName);
            tvRepairCharge=itemView.findViewById(R.id.tv_home_weixiu_repairCharge);
            tvDiscount=itemView.findViewById(R.id.tv_home_weixiu_discount);
            tvAmountPaid=itemView.findViewById(R.id.tv_home_weixiu_amountPaid);
            tvMaintainabilityName=itemView.findViewById(R.id.tv_home_weixiu_maintainabilityName);
            tvRemark=itemView.findViewById(R.id.tv_home_weixiu_remark);
        }
    }

    /**
     * 点击事件
     */
    public interface OnItemClickListener{
        void onItemClick(PreRepairItemDetailVo data,Integer position);
    }
}

