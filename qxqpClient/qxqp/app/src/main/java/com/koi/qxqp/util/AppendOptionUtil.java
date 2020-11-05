package com.koi.qxqp.util;

import android.content.Context;
import android.graphics.Color;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.koi.qxqp.R;
import com.koi.qxqp.bean.AppendOptionVo;
import com.koi.qxqp.widget.FlowRadioGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 追加列表（绑定列表）
 */
public class AppendOptionUtil {
    public ArrayList<String> itemName=null;//单选每列名称
    public Map<String,Integer> itemIdByName=null;//通过名称查找ID
    public Map<String,Integer> itemCheckedByName=null;//通过名称选择默认选项
    public Map<Integer,String> itemNameById=null;//通过ID查找名称
    public AppendOptionUtil(List<AppendOptionVo> list){
        itemIdByName=new HashMap<>();
        itemCheckedByName=new HashMap<>();
        itemName=new ArrayList<>();
        itemNameById=new ArrayMap<>();
        for (int i=0;i<list.size();i++){
            itemIdByName.put(list.get(i).getName(),list.get(i).getId());
            itemCheckedByName.put(list.get(i).getName(),i);
            itemNameById.put(list.get(i).getId(),list.get(i).getName());
            itemName.add(list.get(i).getName());
        }
    }

    /**
     * 获取单选每列名称
     * @return
     */
    public ArrayList<String> getItemName(){
        return itemName;
    }

    /**
     * 通过名称查找ID
     * @return
     */
    public Integer getItemIdByName(String name){
        return itemIdByName.get(name);
    }

    /**
     * 通过名称选择默认选项
     * @return
     */
    public Integer getItemCheckedByName(String name){
        return itemCheckedByName.get(name);
    }

    /**
     * 通过ID查找名称
     * @return
     */
    public String getItemNameById(Integer id){
        return itemNameById.get(id);
    }

    /**
     * 绑定选项信息 FlowRadioGroup/单选按钮
     */
    public static void appendOption(Context context, FlowRadioGroup flowRadioGroup, List<AppendOptionVo> list, int padding,boolean isClickable){
        flowRadioGroup.removeAllViews();//清空容器
        for (int i=0;i<list.size();i++){
            RadioButton radioButton = new RadioButton(context);
            LinearLayout.LayoutParams rbLayoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            radioButton.setLayoutParams(rbLayoutParams);
            radioButton.setId(list.get(i).getId());//设置id
            radioButton.setPadding(padding,0,padding,0);//设置按钮边距
            radioButton.setGravity(Gravity.CENTER);//文字居中
            radioButton.setBackgroundResource(R.drawable.selector_btn_background);//背景
            radioButton.setTextColor(Color.BLACK);//设置字体颜色
            radioButton.setClickable(isClickable);//禁止点击
            radioButton.setButtonDrawable(null);//按钮图标
            radioButton.setText(list.get(i).getName().trim());//设置文字
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        buttonView.setTextColor(Color.parseColor("#fb7299"));//设置字体颜色
                    }else {
                        buttonView.setTextColor(Color.BLACK);//设置字体颜色
                    }
                }
            });
            flowRadioGroup.addView(radioButton);
        }
    }
}
