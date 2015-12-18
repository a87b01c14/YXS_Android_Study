package com.antex.mycompoundview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * @author xiaosanyu on 15/12/18.
 */
public class MyCompoundView extends LinearLayout {
    private EditText mEditText;
    private Button mClearButton;

    //使用代码构建控件
    public MyCompoundView(Context context) {
        super(context);
        //将布局方向设置为纵向
        setOrientation(VERTICAL);
        //创建子控件
        mEditText = new EditText(context);
        mClearButton = new Button(context);
        mClearButton.setText("Clear");
        //在复合控件中布局他们
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mEditText.setLayoutParams(params);
        mClearButton.setLayoutParams(params);

        //将子控件添加到复合控件中
        addView(mEditText);
        addView(mClearButton);
        //为按钮添加监听事件
        addClickLinstener();

    }


    //在XML中添加复合控件
    public MyCompoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //使用布局资源填充视图
        LayoutInflater inflater = LayoutInflater.from(getContext());
        //还有两种写法
        //inflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflater=((Activity)getContext()).getLayoutInflater();
        View view = inflater.inflate(R.layout.clearable_edit_text, this, true);

        //获得对子控件的引用
        mEditText = (EditText) view.findViewById(R.id.eidtText);
        mClearButton = (Button) view.findViewById(R.id.clearButton);
        //为按钮添加监听事件
        addClickLinstener();
    }

    private void addClickLinstener() {
        mClearButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });
    }

}
