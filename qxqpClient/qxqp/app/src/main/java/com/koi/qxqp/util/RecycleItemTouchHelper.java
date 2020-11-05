package com.koi.qxqp.util;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView 拖动和移除的支持
 */
public class RecycleItemTouchHelper extends ItemTouchHelper.Callback{

    private final ItemTouchHelperCallback helperCallback;

    public RecycleItemTouchHelper(ItemTouchHelperCallback helperCallback) {
        this.helperCallback = helperCallback;
    }
    /**
     * 定义外部接口
     */
    public interface ItemTouchHelperCallback{
        void onItemDelete(int positon);
        void onMove(int fromPosition,int toPosition);
    }

    /**
     * 设置滑动类型标记
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     *          返回一个整数类型的标识，用于判断Item那种移动行为是允许的
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //START  右向左 END左向右 LEFT  向左 RIGHT向右  UP向上
        //如果某个值传0，表示不触发该操作，次数设置支持上下拖拽，支持向右滑动
        return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.END );
    }
    /**
     * 拖拽切换Item的回调
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     *          如果Item切换了位置，返回true；反之，返回false
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        helperCallback.onMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }
    /**
     * 滑动Item
     *
     * @param viewHolder
     * @param direction
     *           Item滑动的方向
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        helperCallback.onItemDelete(viewHolder.getAdapterPosition());
    }
}
