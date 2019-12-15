package com.domain.study.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.domain.study.animation.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 *
 * 参考博客: https://www.jianshu.com/p/225fe1feba60
 *
 *
 *
 *
 *
 */

public class MainActivity extends BaseActivity {


    @BindView(R.id.iv)
    ImageView iv;

    AnimationDrawable animationDrawable = null;
    boolean isStart = false;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        // 1. 设置动画
        animationDrawable = (AnimationDrawable) iv.getBackground();
    }


    @OnClick(R.id.iv)
    public void onViewClicked() {
        if (isStart) {
            stop();
        } else {
            start();
        }
    }

    private void  start (){
        // 2. 获取动画对象
        animationDrawable.start();
        isStart = true;
    }


    private void  stop (){
        // 2. 获取动画对象
        animationDrawable.stop();
        isStart = false;
    }
}
