package com.antex.button;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ButtonActivity extends AppCompatActivity {
    private static int DOUBLE_CLICK_TIME = 1000;
    private List<Long> times = new ArrayList<>();
    private Handler mHandler = new Handler();

    private Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Button button01 = (Button) findViewById(R.id.button01);
        //短按点击事件监听

        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ButtonActivity.this, "OnClickListener", Toast.LENGTH_SHORT).show();
            }
        });

        //长按(长按2秒以上)点击事件监听
        button01.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(ButtonActivity.this, "OnLongClickListener", Toast.LENGTH_SHORT)
                        .show();
                return true;
            }
        });


        Button button02 = (Button) findViewById(R.id.button02);
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                times.add(SystemClock.uptimeMillis());

                isDoubleClick();

            }
        });

        Button button03 = (Button) findViewById(R.id.button03);
        button03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                times.add(SystemClock.uptimeMillis());

                isMultiClick();

            }
        });


    }

    /**
     * 多击事件判断
     */

    private boolean isMultiClick() {
        int size = times.size();

        //处理多击事件
        if (size > 1) {

            if (times.get(size - 1) - times.get(size - 2) < DOUBLE_CLICK_TIME) {
                Toast.makeText(ButtonActivity.this, size + "连击", Toast.LENGTH_SHORT).show();
                if (mHandler != null) {
                    if (r != null) {
                        //移除回调
                        mHandler.removeCallbacks(r);
                    }
                }
                return true;
            } else {
                //这种情况下，以前存储的点击的时间已经没有用处了，最后一次就是“第一次”
                long oldtime = times.get(size - 1);
                times.clear();
                times.add(oldtime);
            }

        }
        //此处可以添加提示
        //showTips();
        r = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ButtonActivity.this, "单击", Toast.LENGTH_SHORT).show();
            }
        };
        //DOUBLE_CLICK_TIME时间后提示单击事件
        mHandler.postDelayed(r, DOUBLE_CLICK_TIME);
        return false;
    }

    /**
     * 双击事件判断
     */
    private boolean isDoubleClick() {
        if (times.size() == 2) {
            //已经完成了一次双击，list可以清空了
            if (times.get(times.size() - 1) - times.get(0) < DOUBLE_CLICK_TIME) {
                times.clear();
                Toast.makeText(ButtonActivity.this, "双击", Toast.LENGTH_SHORT).show();
                if (mHandler != null) {
                    if (r != null) {
                        //移除回调
                        mHandler.removeCallbacks(r);
                        r = null;
                    }
                }
                return true;
            } else {
                //这种情况下，第一次点击的时间已经没有用处了，第二次就是“第一次”
                times.remove(0);
            }

        }
        //此处可以添加提示
        //showTips();
        r = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ButtonActivity.this, "单击", Toast.LENGTH_SHORT).show();
            }
        };
        //DOUBLE_CLICK_TIME时间后提示单击事件
        mHandler.postDelayed(r, DOUBLE_CLICK_TIME);
        return false;
    }

    /**
     * 谷歌程序员的写法。
     * 非常简洁，思想差不多，不过谷歌工程师是利用数组移位操作来消除第二个问题的影响的。
     * 而要实现多击事件，只需修改数组长度即可
     */
    //存储时间的数组
    long[] mHits = new long[2];

    public void doubleClick() {
        // 双击事件响应
        /**
         *  public static native void arraycopy(Object src, int srcPos,Object dst, int dstPos,
         *  int length);拷贝数组
         * src 要拷贝的源数组
         * srcPos 源数组开始拷贝的下标位置
         * dst 目标数组
         * dstPos 开始存放的下标位置
         * length 要拷贝的长度（元素的个数）
         *
         */
        //实现数组的移位操作，点击一次，左移一位，末尾补上当前开机时间（cpu的时间）
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        //双击事件的时间间隔500ms
        if (mHits[0] >= (SystemClock.uptimeMillis() - 500)) {
            //双击后具体的操作
            //do
        }
    }

    private void showTips() {
        Toast.makeText(ButtonActivity.this, "再按一次双击", Toast.LENGTH_SHORT).show();
    }

    /**
     * 在XML中配置置android:onClick="MyClick"
     * 必须设置为public void
     */

    public void MyClick(View view) {
//        switch (view.getId())
//        {
//           //TODO
//        }
        Toast.makeText(ButtonActivity.this, "android:onClick=\"MyClick\"", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    //自定义类实现OnClickListener接口
//    private MyOnClickListener mOnClickListener = new MyOnClickListener();
//
//    private class MyOnClickListener implements View.OnClickListener {
//
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                //TODO
//            }
//        }
//    }

//    或者使Activity实现   OnClickListener接口


}
