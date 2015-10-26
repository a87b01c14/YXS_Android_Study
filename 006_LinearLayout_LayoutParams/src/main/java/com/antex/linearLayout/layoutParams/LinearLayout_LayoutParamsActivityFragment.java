package com.antex.linearLayout.layoutParams;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * LinearLayout.LayoutParams 使用说明
 * 用JAVA代码构建布局.
 */
public class LinearLayout_LayoutParamsActivityFragment extends Fragment {

    private LinearLayout.LayoutParams params;

    public LinearLayout_LayoutParamsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        //设置布局方向
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        //父LinearLayout LayoutParams
        LinearLayout.LayoutParams parentparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        linearLayout.setLayoutParams(parentparams);

        //子控件的LayoutParams
        // 宽度为0,高度为WRAP_CONTENT,权重为1,权重也可以不指定
        params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        //设置控件的显示位置,相当于控件的layout_gravity属性
        params.gravity= Gravity.CENTER;

        //设置控件margin值
        params.leftMargin=10;
        params.rightMargin=10;
        params.bottomMargin=10;
        params.topMargin=10;

        addView(4, linearLayout);

        return linearLayout;
    }

    /**
     * 添加按钮到容器中
     * @param count        添加按钮总个数
     * @param linearLayout 按钮所在的父容器
     */
    private void addView(int count, LinearLayout linearLayout) {
        for (int i = 1; i <= count; i++) {
            Button button = new Button(getContext());
            button.setText("Button" + i);
            //设置按钮内文本内容显示位置
            //button.setGravity(Gravity.LEFT);
            linearLayout.addView(button, params);
        }
    }
}
