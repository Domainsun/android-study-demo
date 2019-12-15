package com.domain.study.animation.tween;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.domain.study.animation.R;
import com.domain.study.animation.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class TweenRotateActivity extends BaseActivity {
    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_tween_translate;
    }

    @Override
    protected void initView() {

    }



    @OnClick(R.id.iv)
    public void onViewClicked() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        iv.startAnimation(animation);

    }
}
