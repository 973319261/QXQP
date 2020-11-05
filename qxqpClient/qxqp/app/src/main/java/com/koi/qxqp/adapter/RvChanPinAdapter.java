package com.koi.qxqp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
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
import com.koi.qxqp.bean.PreProductDetailVo;
import com.koi.qxqp.util.AppendOptionUtil;
import com.koi.qxqp.util.RecycleItemTouchHelper;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RvChanPinAdapter extends RecyclerView.Adapter<RvChanPinAdapter.ViewHolder> implements RecycleItemTouchHelper.ItemTouchHelperCallback {
    private Activity myActivity;//上下文
    private MyApplication myApplication;//全局变量
    private List<PreProductDetailVo> list;//数据
    Map<String,List<AppendOptionVo>> mapData;//获取后台返回的集合
    private OnItemClickListener onItemClickListener;
    private TextView tvNum;//维修项目个数
    private LinearLayout llEmpty;//空数据图片
    public RvChanPinAdapter(Activity activity,List<PreProductDetailVo> list){
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
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_home_chanpin,parent,false);
        return new ViewHolder(view);
    }
    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final PreProductDetailVo preProductDetailVo=list.get(position);
        if (preProductDetailVo!=null){
            if (preProductDetailVo.getFittingsCode()!=null){
                holder.tvFittingsCode.setText(preProductDetailVo.getFittingsCode());
            }
            if (preProductDetailVo.getFittingsName()!=null){
                holder.tvFittingsName.setText(preProductDetailVo.getFittingsName());
            }
            if (preProductDetailVo.getVehicleType()!=null){
                holder.tvVehicleType.setText(preProductDetailVo.getVehicleType());
            }
            if (preProductDetailVo.getQuantity()!=null){
                holder.tvQuantity.setText(String.format(Locale.getDefault(),"%.2f",preProductDetailVo.getQuantity()));
            }
            if (preProductDetailVo.getSystemUnit()!=null){
                holder.tvSystemUnit.setText(preProductDetailVo.getSystemUnit());
            }
            if (preProductDetailVo.getUnitPrice()!=null){
                holder.tvUnitPrice.setText(String.format(Locale.getDefault(),"%.2f",preProductDetailVo.getUnitPrice()));
            }
            if (preProductDetailVo.getDiscount()!=null){
                holder.tvDiscount.setText(String.format(Locale.getDefault(),"%.0f",preProductDetailVo.getDiscount()));
            }
            if (preProductDetailVo.getAmount()!=null){
                holder.tvAmount.setText(String.format(Locale.getDefault(),"%.2f",preProductDetailVo.getAmount()));
            }
            if (preProductDetailVo.getMaintainabilityId()>0){
                AppendOptionUtil appendOptionUtil=new AppendOptionUtil(mapData.get("maintainability"));
                holder.tvMaintainabilityName.setText(appendOptionUtil.getItemNameById(preProductDetailVo.getMaintainabilityId()).trim());
            }
            if (preProductDetailVo.getRemark()!=null){
                holder.tvRemark.setText(preProductDetailVo.getRemark().trim());
            }
            //=======列表每一项点击 item点击事件
            if (onItemClickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //判断是否设置点击事件
                        if (onItemClickListener!=null){
                            onItemClickListener.onItemClick(preProductDetailVo,position);
                        }
                    }
                });
            }
        }
    }

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
    public void addItem(List<PreProductDetailVo> listAdd, boolean isRefresh) {
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
    public void modifyItem(PreProductDetailVo listModify,int position) {
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
        myApplication.getProductDetailVoList().remove(positon);//从全局变量中移除
        if (list.size()>0){
            llEmpty.setVisibility(View.GONE);
        }else {
            llEmpty.setVisibility(View.VISIBLE);
        }
        tvNum.setText(String.format(Locale.getDefault(),"%d个产品材料",list.size()));
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
        myApplication.getProductDetailVoList().clear();//从全局变量全部移除
        myApplication.getProductDetailVoList().addAll(list);//重新添加
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvFittingsCode;//配件编号
        TextView tvFittingsName;//配件名称
        TextView tvVehicleType;//车型
        TextView tvQuantity;//数量
        TextView tvSystemUnit;//单位
        TextView tvUnitPrice;//单价
        TextView tvDiscount;//折扣
        TextView tvAmount;//金额
        TextView tvMaintainabilityName;//维修性质
        TextView tvRemark;//备注
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFittingsCode= itemView.findViewById(R.id.tv_home_chanpin_fittingsCode);
            tvFittingsName=itemView.findViewById(R.id.tv_home_chanpin_fittingsName);
            tvVehicleType=itemView.findViewById(R.id.tv_home_chanpin_vehicleType);
            tvQuantity=itemView.findViewById(R.id.tv_home_chanpin_quantity);
            tvSystemUnit=itemView.findViewById(R.id.tv_home_chanpin_systemUnit);
            tvUnitPrice=itemView.findViewById(R.id.tv_home_chanpin_unitPrice);
            tvDiscount=itemView.findViewById(R.id.tv_home_chanpin_discount);
            tvAmount=itemView.findViewById(R.id.tv_home_chanpin_amount);
            tvMaintainabilityName=itemView.findViewById(R.id.tv_home_chanpin_maintainabilityName);
            tvRemark=itemView.findViewById(R.id.tv_home_chanpin_remark);
        }
    }
    /**
     * 点击事件
     */
    public interface OnItemClickListener{
        void onItemClick(PreProductDetailVo data,Integer position);
    }
}
