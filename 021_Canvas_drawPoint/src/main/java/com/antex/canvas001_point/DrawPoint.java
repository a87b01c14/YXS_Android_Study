package com.antex.canvas001_point;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * @author xiaosanyu on 15/12/18.
 */
public class DrawPoint extends View {
    private Paint mPaint;
    private int step = 15;
    private Random random;

    public DrawPoint(Context context) {
        this(context, null);
    }

    public DrawPoint(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
        random = new Random();

    }

    public DrawPoint(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int j = (int) getY(); j < getHeight(); j += step)
            for (int i = (int) getX(); i < getWidth(); i += step) {
                //随机设置画笔的颜色
                mPaint.setARGB(random.nextInt(255), random.nextInt(255), random.nextInt(255),
                        random.nextInt(255));
                canvas.drawPoint(i, j, mPaint);
            }
        //延迟1秒刷新界面
        postInvalidateDelayed(1000);

    }

}
