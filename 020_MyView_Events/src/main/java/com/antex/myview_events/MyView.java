package com.antex.myview_events;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * @author xiaosanyu on 15/12/20.
 */
public class MyView extends View {
    private static final String TAG = MyView.class.getSimpleName();
    private Paint mPaint;
    //自定义回调接口
    private MyClickListener mListener;
    //坐标点,记录屏幕按下的位置,后面判断此位置到圆心的距离
    private PointF mPoint;

    public MyView(Context context) {
        this(context,null);
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

        mPoint = new PointF();
        //设置可获取焦点
        setFocusable(true);
        //添加点击事件监听器
        setOnClickListener(mOnClickListener);

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
        //Log.i(TAG,"测量高度 " + MeasureSpec.toString(heightMeasureSpec));

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.AT_MOST:
                //result = specSize;
                //因为在ondraw里面只是简单的画一个文本。所以只需要文本的高度即可
                result = Math.min(specSize, measureTextHeight(mPaint) + padding);
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
        //Log.i(TAG, "测量宽度 " + MeasureSpec.toString(widthMeasureSpec));
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(specSize, specSize + padding);
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
        mPaint.setColor(Color.GRAY);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.min(getWidth(), getHeight()) / 2,
                mPaint);
        mPaint.setColor(Color.RED);
        String string = "MyView";
        canvas.drawText("MyView", (getWidth() - measureTextWidth(string, mPaint)) / 2, (getHeight
                () + measureTextHeight(mPaint)) / 2, mPaint);
    }


    //获取文本宽度
    private int measureTextWidth(String string, Paint paint) {
        Rect result = new Rect();
        // Measure the text rectangle to get the width
        paint.getTextBounds(string, 0, string.length(), result);
        return result.width();
    }

    //获取文本高度
    private int measureTextHeight(Paint paint) {
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
//        System.out.println("fontMetrics.toString() = " + fontMetrics.toString());
        return fontMetrics.descent - fontMetrics.ascent + fontMetrics.leading;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "MyView.onKey");
        switch (event.getAction()) {
            case KeyEvent.ACTION_DOWN:
                Log.d(TAG, "KeyEvent ACTION_DOWN");
                break;
            case KeyEvent.ACTION_UP:
                Log.d(TAG, "KeyEvent ACTION_UP");
                break;
            case KeyEvent.ACTION_MULTIPLE:
                Log.d(TAG, "KeyEvent ACTION_MULTIPLE");
                break;
            default:
                break;

        }
        return super.onKeyDown(keyCode, event);
    }


    //点击事件注册
    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "MyView.onClick");
            Toast.makeText(getContext(), "MyView.onClick", Toast.LENGTH_SHORT).show();
            //自定义接口回调,判断点击的是圆内还是圆外
            if (mListener != null) {
                //计算当前点击的坐标点与圆心坐标点的距离
                int distance = (int) Math.sqrt(Math.pow(mPoint.x - getWidth() / 2, 2) + Math.pow
                        (mPoint.y - getHeight() / 2, 2));
                if (distance <= Math.min(getWidth(), getHeight()) / 2)
                    mListener.myClick("inside circle");
                else mListener.myClick("outside circle");
            }
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyView.onTouch");
        mPoint.set(event.getX(), event.getY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "MotionEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "MotionEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "MotionEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_OUTSIDE:
                Log.d(TAG, "MotionEvent ACTION_OUTSIDE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "MotionEvent ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(event);
    }


    public void setListener(MyClickListener listener) {
        mListener = listener;
    }

    public interface MyClickListener {
        void myClick(String s);
    }
}
