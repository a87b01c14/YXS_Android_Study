package com.antex.baseLine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class BaseLineActivity extends AppCompatActivity {
    private static  final String TOGGLEBUTTON1_ISCHECKED="TOGGLEBUTTON1_ISCHECKED";
    private static  final String TOGGLEBUTTON2_ISCHECKED="TOGGLEBUTTON2_ISCHECKED";
    private LinearLayout linearLayout1, linearLayout2;
    private ToggleButton toggleButton1, toggleButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("BaseLineActivity.onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_line);
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

        linearLayout1 = (LinearLayout) findViewById(R.id.ll_parent);
        linearLayout2 = (LinearLayout) findViewById(R.id.ll_child);
        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        //获取intent
        Intent intent= getIntent();
        //获取保存的ToggleButton状态
        if(intent !=null) {
            boolean b1=intent.getBooleanExtra(TOGGLEBUTTON1_ISCHECKED, true);
            boolean b2=intent.getBooleanExtra(TOGGLEBUTTON2_ISCHECKED, true);
            toggleButton1.setChecked(b1);
            toggleButton2.setChecked(b2);

            //setBaselineAligned(boolean) 是否允许用户调整它内容的基线。 true 允许按基线对齐,false不对齐
            //只有带文本内容的控件才有基线，如TextView/Button/EditText
            linearLayout1.setBaselineAligned(b1);

            //setBaselineAlignedChildIndex(m)  ,设置当前LinearLayout与其它View的对齐方式.
            //索引号从0开始,表示是以第m+1控件为基准线与其它View对齐
            linearLayout2.setBaselineAlignedChildIndex(b2 ? 3 : 0);
        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base_line, menu);
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

        public void click(View v) {

            //直接修改不起作用
            //linearLayout1.setBaselineAligned(toggleButton1.isChecked());
            //linearLayout2.setBaselineAlignedChildIndex(toggleButton2.isChecked() ? 3 : 0);
            //重启应用,并传入修改后的状态值
            Intent intent= new Intent(BaseLineActivity.this,BaseLineActivity.class);
            intent.putExtra(TOGGLEBUTTON1_ISCHECKED, toggleButton1.isChecked());
            intent.putExtra(TOGGLEBUTTON2_ISCHECKED, toggleButton2.isChecked());
            startActivity(intent);
            finish();
            }
}
