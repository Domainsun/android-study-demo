package com.domain.study.animation.property;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.domain.study.animation.R;
import com.domain.study.animation.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ValueAnimatorActivity extends BaseActivity {

    @BindView(R.id.btn_of_int)
    Button btnOfInt;
    @BindView(R.id.btn_of_float)
    Button btnOfFloat;
    @BindView(R.id.btn_of_object)
    Button btnOfObject;
  @BindView(R.id.btn_of_rgb)
    Button btnOfRgb;
  @BindView(R.id.myView)
    MyView myView;

  @BindView(R.id.btn_of_values_property)
    Button btnOfValuesProperty;

    public static void start(Context context) {
        Intent starter = new Intent(context, ValueAnimatorActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_value_animator;
    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.btn_of_int, R.id.btn_of_float, R.id.btn_of_object,R.id.btn_of_rgb,R.id.btn_of_values_property})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_of_int:
                valueAnimatorOfInt();
                break;
            case R.id.btn_of_float:
                valueAnimatorOfFloat();
                break;
            case R.id.btn_of_object:
                myView.startAnimation();
                break;
                case R.id.btn_of_rgb:
                    valueOfRGB();
                break;

                case R.id.btn_of_values_property:
                    valueOfPropertyValues();
                break;

        }
    }


    int btnofFloatWidth = 0 ;

    int btnWidth = 0;
    int btnHeight = 0;
    @Override
    protected void onStart() {
        super.onStart();
        btnOfFloat.post(() ->{
            btnofFloatWidth = btnOfFloat.getMeasuredWidth();

            btnWidth = btnOfValuesProperty.getMeasuredWidth();
            btnHeight = btnOfValuesProperty.getMeasuredHeight();
        });
    }



    private void valueAnimatorOfInt() {
        //获取到屏幕宽度
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        //起始值为btn最开始的坐标的x位置， 结束值为 屏幕的最右边，
        ValueAnimator valueAnimator = ValueAnimator.ofInt((int) btnOfInt.getTranslationX(), widthPixels);

        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(2);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setStartDelay(500);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int currentValue = (int) valueAnimator.getAnimatedValue();
                btnOfInt.setTranslationX(currentValue);
                System.out.println("current value:" + currentValue);

            }
        });
        valueAnimator.start();
    }


    private void valueAnimatorOfFloat() {
        //获取到屏幕宽度
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;


        //起始值为btn最开始的宽度， 结束值为 屏幕的宽度。
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(btnofFloatWidth,widthPixels);

        valueAnimator.setDuration(2000);
//        valueAnimator.setRepeatCount(2);
//        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
//        valueAnimator.setStartDelay(500);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float currentValue = (float) valueAnimator.getAnimatedValue();
                btnOfFloat.getLayoutParams().width = (int) currentValue;
                //重新绘制
                btnOfFloat.requestLayout();
                System.out.println("current value:" + currentValue);
            }
        });
        valueAnimator.start();
    }


    private void valueOfRGB(){
        ValueAnimator valueAnimator = ValueAnimator.ofArgb(Color.BLUE, Color.RED);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int currentValue = (int) valueAnimator.getAnimatedValue();
                btnOfRgb.setBackgroundColor(currentValue);
                System.out.println("current value:" + currentValue);
            }
        });
        valueAnimator.start();
    }

    private void valueOfPropertyValues(){

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;

        PropertyValuesHolder holder1 = PropertyValuesHolder.ofInt("width", btnWidth, widthPixels);
        PropertyValuesHolder holder2 =PropertyValuesHolder.ofInt("height",btnHeight,heightPixels-btnOfValuesProperty.getTop());

        ValueAnimator valueAnimator = ValueAnimator.ofPropertyValuesHolder(holder1, holder2);
        valueAnimator.setDuration(5000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int width = (int) valueAnimator.getAnimatedValue("width");
                int height = (int) valueAnimator.getAnimatedValue("height");

                btnOfValuesProperty.getLayoutParams().width = width;
                btnOfValuesProperty.getLayoutParams().height = height;
                btnOfValuesProperty.requestLayout();

                System.out.println("width:"+width+"height:"+height);
            }
        });

        valueAnimator.start();
    }






}
