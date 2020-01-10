package com.domain.study.animation.tween;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.domain.study.animation.R;
import com.domain.study.animation.base.BaseActivity;
import com.domain.study.animation.tween.TweenAlphaActivity;
import com.domain.study.animation.tween.TweenCombinationActivity;
import com.domain.study.animation.tween.TweenRotateActivity;
import com.domain.study.animation.tween.TweenScaleActivity;
import com.domain.study.animation.tween.TweenTanslateActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TweenActivity extends BaseActivity {
    @BindView(R.id.btn_translate)
    Button btnTranslate;
    @BindView(R.id.btn_scale)
    Button btnScale;
    @BindView(R.id.btn_rotate)
    Button btnRotate;
    @BindView(R.id.btn_alpha)
    Button btnAlpha;


    public static void start(Context context) {
        Intent starter = new Intent(context, TweenActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_tween;
    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.btn_translate, R.id.btn_scale, R.id.btn_rotate, R.id.btn_alpha,R.id.btn_combination})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_translate:
                start(TweenTanslateActivity.class);
                break;
            case R.id.btn_scale:
                start(TweenScaleActivity.class);
                break;
            case R.id.btn_rotate:
                start(TweenRotateActivity.class);
                break;
            case R.id.btn_alpha:
                start(TweenAlphaActivity.class);
                break;
                case R.id.btn_combination:
                start(TweenCombinationActivity.class);
                break;
        }
    }
}
