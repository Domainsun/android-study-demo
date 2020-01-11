package com.domain.study.animation.property;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.domain.study.animation.R;
import com.domain.study.animation.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ObjectAnimatorActivity extends BaseActivity {
    @BindView(R.id.btn_translate)
    Button btnTranslate;
    @BindView(R.id.btn_rotate)
    Button btnRotate;
    @BindView(R.id.btn_scale)
    Button btnScale;
    @BindView(R.id.btn_alpha)
    Button btnAlpha;

    @BindView(R.id.btn_custom)
    Button btnCustom;
    @BindView(R.id.progress_view)
    ProgressView progressView;
    @BindView(R.id.btn_set)
    Button btnSet;
  @BindView(R.id.btn_view_property)
    Button btnViewProperty;
 @BindView(R.id.btn_xml)
    Button btnXml;


    public static void start(Context context) {
        Intent starter = new Intent(context, ObjectAnimatorActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_object_animator;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.btn_translate, R.id.btn_rotate, R.id.btn_scale, R.id.btn_alpha, R.id.btn_custom, R.id.btn_set, R.id.btn_key_frame,R.id.btn_view_property,R.id.btn_xml})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_translate:
                translate();
                break;
            case R.id.btn_rotate:
                rotate();
                break;
            case R.id.btn_scale:
                scale();
                break;
            case R.id.btn_alpha:
                alpha();
                break;

            case R.id.btn_custom:
                custom();
                break;
            case R.id.btn_set:
                animatorSet();
                break;

            case R.id.btn_key_frame:
                keyFrame();
                break;

            case R.id.btn_view_property:
                viewProperty();
                break;


            case R.id.btn_xml:
                xmlAnimator();
                break;
        }
    }

    private void translate() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btnTranslate, "translationX", 0, 500, 200);
        objectAnimator.setDuration(3000);
        objectAnimator.start();

    }

    private void rotate() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btnRotate, "rotation", 0, 200, 300);
        objectAnimator.setDuration(3000);
        objectAnimator.start();

    }

    private void scale() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btnScale, "ScaleX", 0f, 0.5f, 1.1f);
        objectAnimator.setDuration(3000);
        objectAnimator.start();

    }

    private void alpha() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btnAlpha, "Alpha", 0, 0.5f, 1f);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }

    private void custom() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(progressView, "progress", 0, 90);
        objectAnimator.setDuration(5000);
        objectAnimator.setInterpolator(new FastOutSlowInInterpolator());
        objectAnimator.start();


        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        objectAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//            }
        });
    }

    private void animatorSet() {

//        AnimatorSet.playTogether(Animator... anim) ：  将动画组合一起执行
//        AnimatorSet.playSequentially(Animator... anim) ：  将动画组合有序执行


//        AnimatorSet.play(Animator anim)   ：播放当前动画
//        AnimatorSet.after(long delay)   ：将现有动画延迟x毫秒后执行
//        AnimatorSet.with(Animator anim)   ：将现有动画和传入的动画同时执行
//        AnimatorSet.after(Animator anim)   ：将现有动画插入到传入的动画之后执行
//        AnimatorSet.before(Animator anim) ：  将现有动画插入到传入的动画之前执行


        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;

        ObjectAnimator translationX = ObjectAnimator.ofFloat(btnSet, "translationX", 0, widthPixels);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(btnSet, "rotation", 0, 360);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(btnSet, "alpha", 1, 0, 1);

        AnimatorSet animatorSet = new AnimatorSet();

//        animatorSet.playTogether(translationX,rotation,alpha);
//        animatorSet.playSequentially();

        animatorSet.play(rotation).with(alpha).before(translationX);

        animatorSet.setDuration(5000);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.start();

    }

    private void keyFrame() {
        //设置关键帧， 第一个参数是 完成度， 第二个参数是 属性值， 比如当完成度一半的时候（0.5），属性值（progress）给100；
        Keyframe keyframe = Keyframe.ofFloat(0, 0);
        Keyframe keyframe1 = Keyframe.ofFloat(0.5f, 100);
        Keyframe keyframe2 = Keyframe.ofFloat(1, 80);
        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("progress", keyframe, keyframe1, keyframe2);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(progressView, holder);

        objectAnimator.setDuration(5000);
        objectAnimator.start();
    }

    private void viewProperty(){
       btnViewProperty.animate().alpha(0).setDuration(2000).rotation(360).translationX(300);
    }


    private void xmlAnimator(){

        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.set_animator);
        animator.setDuration(5000);
        animator.setTarget(btnXml);
        animator.start();

    }


}
