package com.domain.study.animation.property;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ProgressView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float progress = 0;

    private float RADIUS = 80;


    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = getWidth()/2;
        float centerY = getHeight()/2;


        mPaint.setColor(Color.RED);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(25);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(centerX - RADIUS, centerY - RADIUS, centerX + RADIUS, centerY + RADIUS);

        canvas.drawArc(rectF,0,3.6f*progress,false,mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText((int) progress + "%", centerX, centerY - (mPaint.ascent() + mPaint.descent()) / 2, mPaint);


    }
}
