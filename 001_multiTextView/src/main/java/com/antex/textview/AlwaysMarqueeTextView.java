package com.antex.textview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by xiaosanyu on 15/10/19.
 * 一直滚动TextView
 */
public class AlwaysMarqueeTextView extends TextView {

    public AlwaysMarqueeTextView(Context context) {
        super(context);
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 关键 设置为自动获取焦点
     */
    @Override
    public boolean isFocused() {
        return true;
    }
}