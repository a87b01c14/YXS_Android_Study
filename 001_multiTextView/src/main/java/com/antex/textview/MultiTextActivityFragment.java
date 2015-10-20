package com.antex.textview;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.antex.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MultiTextActivityFragment extends Fragment {

    public MultiTextActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multi_text, container, false);
        final ScrollView mScrollView = (ScrollView) view.findViewById(R.id.mScrollView);

        //LogTextBox
        Button addbutton = (Button) view.findViewById(R.id.add);
        Button clearbutton = (Button) view.findViewById(R.id.clear);
        final TextView textView0 = (TextView) view.findViewById(R.id.text);
        textView0.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    mScrollView.requestDisallowInterceptTouchEvent(false);
                else
                    mScrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView0.append("This is a test\n");
            }
        });
        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView0.setText("");
            }
        });

        //自定义字体
        TextView textView1 = (TextView) view.findViewById(R.id.custom_font_textView);
        AssetManager mgr = getActivity().getAssets();//得到AssetManager
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/jdxsj.TTF");//根据路径得到Typeface
        textView1.setTypeface(tf);//设置字体
        textView1.setText(Html.fromHtml(getActivity().getString(R.string.custom_font_text)));//格式化html格式字符串
        textView1.getPaint().setFakeBoldText(true);//设置中文加粗
        //textView1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗方法2

        //带图片的TextView
        TextView textView2 = (TextView) view.findViewById(R.id.insert_picture_textView);
        String string = getActivity().getString(R.string.insert_picture_text);
        String[] strings = string.split(" ");
        SpannableString spannableString = new SpannableString(string);
        //利用SpannableString设置文字颜色
        spannableString.setSpan(new ForegroundColorSpan(Color.GREEN), 0, strings[0].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //利用SpannableString设置文字点击
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(getActivity(), "Click me!!!", Toast.LENGTH_SHORT).show();
            }
        }, strings[0].length() + 1, string.length() - strings[2].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //利用SpannableString设置背景颜色
        spannableString.setSpan(new BackgroundColorSpan(Color.GRAY), strings[0].length() + strings[1].length() + 2, string.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        String htmlstring = "<img src='" + R.mipmap.yxs + "'/>";

        textView2.setText(spannableString);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());

        //追加照片
        textView2.append(Html.fromHtml(htmlstring, new Html.ImageGetter() {

            @Override
            public Drawable getDrawable(String source) {
                int id = Integer.parseInt(source);
                Drawable d = getResources().getDrawable(id);
                //图片缩放
                d.setBounds(0, 0, 40, 40);
                return d;
            }
        }, null));

        //设置文字阴影
        //TextView textView3=(TextView)view.findViewById(R.id.shadow_textView);
        //textView3.setShadowLayer(10,15,10,0x7f0000ff);
        return view;
    }
}
