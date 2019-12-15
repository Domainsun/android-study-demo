package com.domain.study.animation.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity {

    protected Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏应用的标题栏
        ActivityManager.getInstance().addActivity(this);
        setContentView(setLayoutId());
        unbinder = ButterKnife.bind(this);
        initView();
    }

    protected abstract int setLayoutId();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().finishActivity(this);
        unbinder.unbind();
    }
    
    
    
    public void start(Class<?> cls) {
        Intent starter = new Intent(this, cls);
        startActivity(starter);
    }

}
