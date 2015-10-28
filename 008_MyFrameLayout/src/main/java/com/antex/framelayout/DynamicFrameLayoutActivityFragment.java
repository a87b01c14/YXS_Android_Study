package com.antex.framelayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class DynamicFrameLayoutActivityFragment extends Fragment {
    //框架布局
    private FrameLayout frameLayout;
    //待显示图像数组
    private static final int[] drawable = {R.mipmap.a, R.mipmap.b, R.mipmap.a1, R.mipmap.a2, R
            .mipmap.a3, R.mipmap.a4, R.mipmap.b1, R.mipmap.b2, R.mipmap.b3, R.mipmap.b4, R.mipmap
            .c1, R.mipmap.c2, R.mipmap.c3, R.mipmap.c4, R.mipmap.d1, R.mipmap.d2, R.mipmap.d3, R
            .mipmap.d4};
    //定时器
    private Timer timer;
    //定时器循环执行时间间隔
    private long period=1000L;

    //用来更新图像的Handler
    private MyHandler handler;

    /**
     * 自定义Handler
     * 采用静态 弱引用
     * */
    static private class MyHandler extends Handler {
        //递增量,用来控制显示第几个图像
        private int i=0;
        WeakReference<DynamicFrameLayoutActivityFragment> mFragment;

        public MyHandler(DynamicFrameLayoutActivityFragment fragment) {
            mFragment = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            DynamicFrameLayoutActivityFragment fragement = mFragment.get();
            if (null == fragement)
                return;
            switch (msg.what) {
                case 0:
                    //设置frameLayout的前景图像
                    fragement.frameLayout.setForeground(fragement.getResources().getDrawable
                            (drawable[i++ % 16]));
                    break;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamic__frame_layout, container, false);
        frameLayout = (FrameLayout) view.findViewById(R.id.frameLayout);
        frameLayout.setForegroundGravity(Gravity.CENTER);
        handler= new MyHandler(this);

        //创建定时器
        timer = new Timer();
        //创建定时器任务
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        //将定时器任务添加到定时器中
        timer.schedule(timerTask, 0, period);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }
}
