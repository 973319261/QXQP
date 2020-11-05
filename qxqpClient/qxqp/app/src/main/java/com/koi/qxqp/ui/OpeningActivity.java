package com.koi.qxqp.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.koi.qxqp.R;
import com.koi.qxqp.util.StatusBarUtil;

import site.gemus.openingstartanimation.DrawStrategy;
import site.gemus.openingstartanimation.NormalDrawStrategy;
import site.gemus.openingstartanimation.OpeningStartAnimation;

public class OpeningActivity extends AppCompatActivity {
    private Activity myActivity;
    private OpeningStartAnimation openingStartAnimation;//开屏动画

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myActivity=this;
        openingStartAnimation = new OpeningStartAnimation.Builder(this)
                .setAppName("汽修预约App")
               .setColorOfAppName(Color.parseColor("#fb7299")) //设置app名称颜色
               .setDrawStategy(new NormalDrawStrategy()) //设置动画效果
               .setAppStatement("欢迎使用汽修预约App") //设置一句话描述
               .setColorOfAppStatement(Color.parseColor("#fb7299")) // 设置一句话描述的颜色
               .setAnimationInterval(2000) // 设置动画时间间隔
               .setAnimationFinishTime(1000) // 设置动画的消失时长
                .setAppIcon(myActivity.getDrawable(R.drawable.bg_open))//设置图标
               .create();
        openingStartAnimation.show(myActivity);
        CountDownTimer timer = new CountDownTimer(3*1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                Intent intent = new Intent(myActivity, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        //启动定时器
        timer.start();
    }
}
