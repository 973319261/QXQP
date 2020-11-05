package com.koi.qxqp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.koi.qxqp.R;
import com.koi.qxqp.bean.FittingsInfoVo;
import com.koi.qxqp.util.ServiceUrls;
import com.koi.qxqp.util.Tools;

import java.util.List;
import java.util.Locale;

public class RvPeiJianAdapter extends RecyclerView.Adapter<RvPeiJianAdapter.ViewHolder> {
    private Activity myActivity;
    private List<FittingsInfoVo> list;
    private boolean isToUser;
    private OnItemCheckListener onItemCheckListener;
    private OnItemClickListener onItemClickListener;

    public RvPeiJianAdapter(Activity activity,List<FittingsInfoVo> list){
        this.myActivity=activity;
        this.list=list;
    }
    public void setOnItemCheckListener(OnItemCheckListener onItemCheckListener) {
        this.onItemCheckListener = onItemCheckListener;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public void setShowChekc(Boolean isToUser){
        this.isToUser=isToUser;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_peijian_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FittingsInfoVo fittingsInfoVo=list.get(position);
        if (fittingsInfoVo!=null){
            String strPicture="";
            //==设置图片
            if (fittingsInfoVo.getPictureName()!=null){
                strPicture = fittingsInfoVo.getPictureName().trim();
            }
            if (Tools.isNotNull(strPicture)) {
                String[] pictures = strPicture.split(";");
                String pictureUrl = ServiceUrls.getClientMethodUrl("getFittingsPicture") + "?pictureName=" + pictures[0];
                //使用Glide加载图片
                Glide.with(myActivity)
                        .load(pictureUrl)
                        .error(R.drawable.ic_no_photo)
                        .into(holder.ivPicture);
            }
            if (fittingsInfoVo.getFittingsCode()!=null){
                holder.tvFittingsCode.setText(fittingsInfoVo.getFittingsCode().trim());
            }
            if (fittingsInfoVo.getFittingsName()!=null){
                holder.tvFittingsName.setText(fittingsInfoVo.getFittingsName().trim());
            }
            if (fittingsInfoVo.getSpecification()!=null){
                holder.tvSpecification.setText(fittingsInfoVo.getSpecification().trim());
            }
            if (fittingsInfoVo.getSystemUnit()!=null){
                holder.tvSystemUnit.setText(String.format(Locale.getDefault(),"(%s)",fittingsInfoVo.getSystemUnit().trim()));
            }
            if (fittingsInfoVo.getVehicleType()!=null){
                holder.tvVehicleType.setText(String.format(Locale.getDefault(),"【%s】",fittingsInfoVo.getVehicleType().trim()));
            }
            if (fittingsInfoVo.getFittingsTypeName()!=null){
                holder.tvFittingsTypeName.setText(String.format(Locale.getDefault(),"%s ",fittingsInfoVo.getFittingsTypeName().trim()));
            }
            if (fittingsInfoVo.getSuppliersName()!=null){
                holder.tvSuppliersName.setText(fittingsInfoVo.getSuppliersName().trim());
            }
            if (fittingsInfoVo.getRemark()!=null){
                holder.tvRemark.setText(fittingsInfoVo.getRemark().trim());
            }
            if (fittingsInfoVo.getSalesPrice()!=null){
                holder.tvSalesPrice.setText(Tools.setMoneySize(String.format(Locale.getDefault(),"￥%.2f",fittingsInfoVo.getSalesPrice())));
            }
            if (isToUser){
                holder.tvCheck.setVisibility(View.GONE);
                holder.ivRight.setVisibility(View.VISIBLE);
                if (onItemClickListener!=null){
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemClickListener!=null){
                                onItemClickListener.onItemClick(fittingsInfoVo);
                            }
                        }
                    });
                }
            }else {
                //=======列表每一项选择 item事件
                if (onItemCheckListener!=null){
                    holder.tvCheck.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemCheckListener!=null){
                                onItemCheckListener.onItemCheck(fittingsInfoVo);
                            }
                        }
                    });
                }
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
     * @param loadPage
     */
    public void addItem(List<FittingsInfoVo> listAdd, int loadPage) {
        if (loadPage==1){
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

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvFittingsCode;//配件编号
        TextView tvCheck;//选择
        ImageView ivRight;//向右图标
        ImageView ivPicture;//图片
        TextView tvFittingsName;//配件名称
        TextView tvSpecification;//规格
        TextView tvSystemUnit;//单位
        TextView tvVehicleType;//车型
        TextView tvFittingsTypeName;//配件类型
        TextView tvSuppliersName;//供应商
        TextView tvRemark;//备注
        TextView tvSalesPrice;//金额
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFittingsCode= itemView.findViewById(R.id.tv_peijian_list_fittingsCode);
            tvCheck=itemView.findViewById(R.id.tv_peijian_list_check);
            ivRight=itemView.findViewById(R.id.iv_peijian_list_right);
            ivPicture=itemView.findViewById(R.id.iv_peijian_list_picture);
            tvFittingsName=itemView.findViewById(R.id.tv_peijian_list_fittingsName);
            tvSpecification=itemView.findViewById(R.id.tv_peijian_list_specification);
            tvSystemUnit=itemView.findViewById(R.id.tv_peijian_list_systemUnit);
            tvVehicleType=itemView.findViewById(R.id.tv_peijian_list_vehicleType);
            tvFittingsTypeName=itemView.findViewById(R.id.tv_peijian_list_fittingsTypeName);
            tvSuppliersName=itemView.findViewById(R.id.tv_peijian_list_suppliersName);
            tvRemark=itemView.findViewById(R.id.tv_peijian_list_remark);
            tvSalesPrice=itemView.findViewById(R.id.tv_peijian_list_salesPrice);
        }
    }
    //配件选择
    public interface OnItemCheckListener{
        void onItemCheck(FittingsInfoVo fittingsInfoVo);
    }
    //配件点击
    public interface OnItemClickListener{
        void onItemClick(FittingsInfoVo fittingsInfoVo);
    }
}
