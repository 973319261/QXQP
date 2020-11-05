package com.koi.qxqp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koi.qxqp.R;
import com.koi.qxqp.bean.PredateVo;
import com.koi.qxqp.util.Tools;

import java.util.List;
import java.util.Locale;

public class RvOrderAdapter extends RecyclerView.Adapter<RvOrderAdapter.ViewHolder>{
    private Activity myActivity;//上下文
    private List<PredateVo> list;//数据
    private OnItemClickListener onItemClickListener;

    public RvOrderAdapter(Activity context,List<PredateVo> list){
        this.myActivity= context;
        this.list=list;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    //创建ViewHolder --设置子项的布局
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_order_list,parent,false);
        return new RvOrderAdapter.ViewHolder(view);
    }
    //绑定每一项的数据
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final PredateVo predateVo=list.get(position);
        if (predateVo!=null){
            holder.tvPredateNum.setText(predateVo.getPredateNum());
            if (predateVo.getToAudit()==true){
                holder.tvToAudit.setText("已审核");
            }else {
                holder.tvToAudit.setText("待审核");
            }
            holder.tvAmount.setText(Tools.setMoneySize(String.format(Locale.getDefault(), "￥%.2f", predateVo.getDeserveMoney())));//总金额
            //=======列表每一项点击 item点击事件
            if (onItemClickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //判断是否设置点击事件
                        if (onItemClickListener!=null){
                            onItemClickListener.onItemClick(v,predateVo,position);
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
     * 对外方法，用于分页添加数据
     *
     * @param listAdd  要添加的数据
     * @param loadPage 加载的页数
     */
    public void addItem(List<PredateVo> listAdd, int loadPage) {
        if (loadPage==1){
            //如果是加载第一页，需要先清空数据列表
            this.list.clear();
            if (listAdd!=null){
                //添加数据
                this.list.addAll(listAdd);
            }
            //通知RecyclerView进行改变--整体
            notifyDataSetChanged();
        }else {
            //不是第一页的情况
            //添加数据
            int nowCount=this.list.size();
            if (listAdd!=null){
                this.list.addAll(listAdd);
            }
            //通知RecyclerView进行改变 -- 局部刷新
            notifyItemRangeChanged(nowCount,list.size());
        }

    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPredateNum;
        TextView tvToAudit;
        TextView tvAmount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPredateNum=itemView.findViewById(R.id.tv_order_list_predateNum);
            tvToAudit=itemView.findViewById(R.id.tv_order_list_toAudit);
            tvAmount=itemView.findViewById(R.id.tv_order_list_amount);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view, PredateVo data, int position);
    }
}
