package com.antex.myview_events;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private MyView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        view = (MyView) findViewById(R.id.myView);
        //调用自己定义的点击事件
        view.setListener(new MyView.MyClickListener() {
            @Override
            public void myClick(String s) {
                Toast.makeText(MainActivity.this, "MyView.onClick CallBack " + s, Toast
                        .LENGTH_SHORT).show();
            }
        });

        //亦可调用系统原有的点击监听方法
    /*    view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"View setOnClickListener",Toast.LENGTH_SHORT)
                .show();
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this,"View setOnLongClickListener",Toast.LENGTH_LONG)
                .show();
                return true;
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
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
}
