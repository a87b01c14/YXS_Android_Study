package com.antex.relativelayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * A placeholder fragment containing a simple view.
 */
public class RelativeLayoutActivityFragment extends Fragment {

    public RelativeLayoutActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_relative_layout, container, false);
        //获取屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        //int screenHeight = dm.heightPixels;
        RelativeLayout relativeLayout= (RelativeLayout)view.findViewById(R.id.relativeLayout);
        Button button1 = (Button)view.findViewById(R.id.button1);
        Button button2 = (Button)view.findViewById(R.id.button2);
        Button button3 = (Button)view.findViewById(R.id.button3);

        //获取父容器可用宽度
        screenWidth-= relativeLayout.getPaddingLeft()+relativeLayout.getPaddingRight();
        //设置每个按钮的宽度为父容器可用宽度的1/4
        button1.setWidth(screenWidth / 4);
        button2.setWidth(screenWidth / 4);
        button3.setWidth(screenWidth / 4);

        return view;
    }
}
