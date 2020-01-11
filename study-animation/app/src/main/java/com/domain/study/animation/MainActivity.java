package com.domain.study.animation;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.domain.study.animation.base.BaseActivity;
import com.domain.study.animation.property.PropertyActivity;
import com.domain.study.animation.tween.TweenActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tv_frame)
    TextView tvFrame;
    @BindView(R.id.tv_tween)
    TextView tvTween;
    @BindView(R.id.tv_property)
    TextView tvProperty;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_frame, R.id.tv_tween, R.id.tv_property})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_frame:

                FrameActivity.start(this);
                break;
            case R.id.tv_tween:
                TweenActivity.start(this);
                break;
            case R.id.tv_property:
                PropertyActivity.start(this);
                break;
        }
    }
}
