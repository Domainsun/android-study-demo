package domain.com.study.sqlite;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import domain.com.study.sqlite.base.BaseActivity;

public class YiBaAcitvity extends BaseActivity {
    @BindView(R.id.iv_wg)
    ImageView ivWg;
    @BindView(R.id.iv_tq)
    ImageView ivTq;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_yiba;
    }

    @Override
    protected void initView() {

    }


    @OnClick(R.id.iv_wg)
    public void onViewClicked() {
        ObjectAnimator  animator = ObjectAnimator.ofFloat(ivWg, "translationX", 50f , -50);
        animator.setDuration(5000);//动画时间
//        animator.setInterpolator(new BounceInterpolator());//实现反复移动的效果
        animator.setRepeatCount(0);//设置动画重复次数
//        animator.setStartDelay(1000);//设置动画延时执行
        animator.start();//启动动画

    }
}
