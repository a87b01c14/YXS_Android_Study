package com.antex.edittext;

import android.content.Context;
import android.text.Layout;
import android.text.Selection;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 选择文字时不弹出默认系统菜单
 * Created by xiaosanyu on 15/10/30.
 */
public class EditTextWithNoMenu extends EditText {
    private int off; //字符串的偏移值
    private Context mContext;

    public EditTextWithNoMenu(Context context) {
        super(context);
        mContext=context;
        initialize();
    }

    public EditTextWithNoMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext=context;
        initialize();
    }

    public EditTextWithNoMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        initialize();
    }

    private void initialize() {
//        setGravity(Gravity.TOP);
//        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onCreateContextMenu(ContextMenu menu) {
        //不做任何处理，为了阻止长按的时候弹出上下文菜单
    }

    @Override
    public boolean getDefaultEditable() {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        Layout layout = getLayout();
        int line = 0;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                line = layout.getLineForVertical(getScrollY() + (int) event.getY());
                off = layout.getOffsetForHorizontal(line, (int) event.getX());
                Selection.setSelection(getEditableText(), off);
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                line = layout.getLineForVertical(getScrollY() + (int) event.getY());
                int curOff = layout.getOffsetForHorizontal(line, (int) event.getX());
                Selection.setSelection(getEditableText(), off, curOff);
                if(action==MotionEvent.ACTION_UP && curOff>off)
                Toast.makeText(mContext, getEditableText().subSequence(off, curOff).toString(), Toast
                        .LENGTH_SHORT).show();
                return true;
//                break;
        }
        return super.onTouchEvent(event);
//        return true;
    }
}
