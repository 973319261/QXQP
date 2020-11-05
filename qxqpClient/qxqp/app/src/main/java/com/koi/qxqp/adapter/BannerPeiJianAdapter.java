package com.koi.qxqp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.koi.qxqp.R;
import com.koi.qxqp.bean.FittingsInfoVo;
import com.koi.qxqp.util.ServiceUrls;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class BannerPeiJianAdapter extends BannerAdapter<String,BannerPeiJianAdapter.BannerViewHolder> {
    private Context context;
    /*构造器*/
    public BannerPeiJianAdapter(List<String> data, Context context) {
        super(data);//路径集合
        this.context = context;
    }
    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView=new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }
    /*绑定控件数据*/
    @Override
    public void onBindView(BannerViewHolder holder, String data, int position, int size) {
        //Glide 加载图片-简单用法
        Glide.with(context)
                .load(data)
                .error(R.drawable.ic_no_photo)
                .into(holder.imageView);
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public BannerViewHolder(@NonNull ImageView itemView) {
            super(itemView);
            this.imageView =  itemView;
        }
    }
}
