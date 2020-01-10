package com.domain.study.animation;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.domain.study.animation.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 *
 *
 * 参考博客: https://www.jianshu.com/p/225fe1feba60
 *
 *
 *
 */

public class FrameActivity extends BaseActivity {


    @BindView(R.id.iv)
    ImageView iv;

    AnimationDrawable animationDrawable = null;
    boolean isStart = false;

    public static void start(Context context) {
        Intent starter = new Intent(context, FrameActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_frame;
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
