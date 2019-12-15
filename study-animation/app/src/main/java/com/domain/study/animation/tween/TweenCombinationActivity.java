package com.domain.study.animation.tween;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.domain.study.animation.R;
import com.domain.study.animation.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TweenCombinationActivity extends BaseActivity {

    @BindView(R.id.iv_shumiao)
    ImageView ivShumiao;
    @BindView(R.id.iv_shuihu)
    ImageView ivShuihu;
    @BindView(R.id.iv_water)
    ImageView ivWater;

    int shuiHuX = 0;
    int shuiHuY = 0;

    int shuMiaoX = 0;
    int shuMiaoY = 0;

    int shuMiaoWidth = 0;
    int shuMiaoHeight = 0;

    int shuiHuWidth = 0;
    int shuiHuHeight = 0;

    int waterHeight = 0;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_combination;
    }

    @Override
    protected void initView() {
        ivShuihu.post(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                ivShuihu.getLocationOnScreen(location);
                shuiHuX = location[0]; // view距离 屏幕左边的距离（即x轴方向）
                shuiHuY = location[1]; // view距离 屏幕顶边的距离（即y轴方向）
                shuiHuWidth = ivShuihu.getWidth();
                shuiHuHeight = ivShuihu.getHeight();
            }
        });
        ivShumiao.post(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                ivShumiao.getLocationOnScreen(location);
                shuMiaoX = location[0]; // view距离 屏幕左边的距离（即x轴方向）
                shuMiaoY = location[1]; // view距离 屏幕顶边的距离（即y轴方向）

                shuMiaoWidth=ivShumiao.getWidth(); // 获取宽度
                shuMiaoHeight =ivShumiao.getHeight(); // 获取高度
            }
        });

        ivWater.post(new Runnable() {
            @Override
            public void run() {
                waterHeight = ivWater.getHeight();
            }
        });



    }


    @OnClick(R.id.iv_shuihu)
    public void onViewClicked() {
        start();
//        startWaterAnimation();
//        new Handler().postDelayed(new Runnable(){
//            public void run(){
//                ivWater.setVisibility(View.VISIBLE);
//            }
//        },4000);

//   new Handler().postDelayed(new Runnable(){
//            public void run(){
//                ivWater.setVisibility(View.GONE);
//            }
//        },5000);


    }

    private void start(){

        AnimationSet setAnimation = new AnimationSet(true);

// 子动画1:旋转动画
        Animation rotate = new RotateAnimation(0,10,0.5f,0.5f);
        rotate.setDuration(1000);
        rotate.setStartOffset(3000);

// 子动画2:平移动画
        Animation translate = new TranslateAnimation(
                0,
                shuMiaoX-shuiHuWidth,
                0
                ,-(shuiHuHeight+shuMiaoHeight+waterHeight+100));
        translate.setDuration(3000);


//等待水滴落下动画
        Animation wait = new TranslateAnimation(
                0,
                0,
                0
                ,0);
        wait.setStartOffset(4000);
        wait.setDuration(2000);




// 步骤4:将创建的子动画添加到组合动画里
        setAnimation.addAnimation(translate);
        setAnimation.addAnimation(rotate);
        setAnimation.addAnimation(wait);

        ivShuihu.startAnimation(setAnimation);


        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivWater.setVisibility(View.VISIBLE);
                startWaterAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void startWaterAnimation(){

        AnimationSet animationSet = new AnimationSet(true);


        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,50);
        translateAnimation.setDuration(2000);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
        alphaAnimation.setDuration(2000);


        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        ivWater.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivWater.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

}
