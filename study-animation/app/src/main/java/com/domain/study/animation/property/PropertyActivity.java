package com.domain.study.animation.property;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.domain.study.animation.R;
import com.domain.study.animation.base.BaseActivity;
import com.domain.study.animation.property.ObjectAnimatorActivity;
import com.domain.study.animation.property.ValueAnimatorActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PropertyActivity extends BaseActivity {
    @BindView(R.id.btn_value_animator)
    Button btnValueAnimator;
    @BindView(R.id.btn_object_animator)
    Button btnObjectAnimator;


    public static void start(Context context) {
        Intent starter = new Intent(context, PropertyActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_property;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.btn_value_animator, R.id.btn_object_animator})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_value_animator:
                ValueAnimatorActivity.start(this);
                break;
            case R.id.btn_object_animator:
                ObjectAnimatorActivity.start(this);
                break;
        }
    }
}
