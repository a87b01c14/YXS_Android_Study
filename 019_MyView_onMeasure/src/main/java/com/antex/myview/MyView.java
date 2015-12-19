package com.antex.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * onMeasure与MeasureSpec介绍
 * MeasureSpec 三种模式
 * 有三种可能的模式：
 * UNSPECIFIED：父布局没有给子布局任何限制，子布局可以任意大小。一般为ScrollView/HorizontalScrollView
 * EXACTLY：父布局决定子元素的确切大小，子元素将被限定在给定的边界里而忽略它本身大小.一般是设置了明确的值或者是MATCH_PARENT
 * AT_MOST：子布局可以根据自己的大小选择任意大小。一般为WARP_CONTENT
 *
 * @author xiaosanyu on 15/12/18.
 */
public class MyView extends View {
    private static final String TAG = MyView.class.getSimpleName();
    private Paint mPaint;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        //消除锯齿
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(30);
        //描边宽度
        mPaint.setStrokeWidth(3);

    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取真正的尺寸
        int measuredWidth = measureWidth(widthMeasureSpec);
        int measuredHeight = measureHeight(heightMeasureSpec);
        //必须调用setMeasuredDimension,否则在布局控件的时候会造成运行时异常
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    private int measureHeight(int heightMeasureSpec) {
        //设置默认高度
        int result = 200;
        //获取控件上下间距
        int padding = getPaddingTop() + getPaddingBottom();
        //获取模式
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取值大小
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        //打印模式和值大小
        Log.i(TAG,"测量高度 " + MeasureSpec.toString(heightMeasureSpec));

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.AT_MOST:
                //result = specSize;
                //因为在ondraw里面只是简单的画一个文本。所以只需要文本的高度即可
                result = Math.min(specSize,measureTextHeight()+padding);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    private int measureWidth(int widthMeasureSpec) {
        //设置默认宽度
        int result = 2000;
        //获取控件左右间距
        int padding = getPaddingLeft() + getPaddingRight();
        //获取模式
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        //获取值大小
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        //打印模式和值大小
        Log.i(TAG, "测量宽度 " + MeasureSpec.toString(widthMeasureSpec));
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(specSize,specSize+padding);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("MyView", 0, getHeight(), mPaint);
    }

    //获取文本高度
    private int measureTextHeight() {
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
//        System.out.println("fontMetrics = " + fontMetrics);
        return fontMetrics.descent - fontMetrics.ascent + fontMetrics.leading;
    }
}
