/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.antex.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;


public class CustomTextView extends TextView {
    private static final String TAG = CustomTextView.class.getSimpleName();
    private String default_namespace = "http://schemas.android.com/apk/res/android";
    private String namespace = "http://schemas.android.com/apk/res-auto";

    public CustomTextView(Context context) {
        super(context);
        Log.d(TAG, "CustomTextView.CustomTextView1");
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "CustomTextView.CustomTextView2");
        init(context, attrs, R.attr.CustomizeStyle, R.style.DefaultCustomizeStyle);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Log.d(TAG, "CustomTextView.CustomTextView3");
        init(context, attrs, defStyle, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        super(context, attrs, defStyle, defStyleRes);
        Log.d(TAG, "CustomTextView.CustomTextView4");
        init(context, attrs, defStyle, defStyleRes);

    }

    public void init(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        if (attrs != null) {
            try {
                int id = attrs.getAttributeResourceValue(default_namespace, "id", 0);
                switch (id) {
                    case R.id.textview2:
                        //defStyle=0 and defStyleRes=0
                        printAttributes(context, attrs);
                        return;
                    case R.id.textview3:
                        defStyle = 0;
                        break;
                }
                int textsize, textcolor, padding;
                String text = "no value";
                //获取自定义属性值的三种方法
                //方法一 在declare-styleable中定义的属性
                TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable
                        .CustomTextViewStyle, defStyle, defStyleRes);

                textsize = typedArray.getDimensionPixelSize(R.styleable
                        .CustomTextViewStyle_text_size, 10);
                textcolor = typedArray.getColor(R.styleable.CustomTextViewStyle_text_color, Color
                        .BLACK);
                text = typedArray.getString(R.styleable.CustomTextViewStyle_text_content);
                padding = typedArray.getDimensionPixelSize(R.styleable
                        .CustomTextViewStyle_padding, 0);

                System.out.println("textsize = " + textsize);
                System.out.println("textcolor = " + textcolor);
                System.out.println("text = " + text);
                System.out.println("padding = " + padding);
                //方法二、自定义属性数组,非常注意，数组中的值一定是从小到大排序，要不然后面会取不到正确的值
                int[] CustomTextViewStyle = {R.attr.text_size, R.attr.text_color, R.attr
                        .text_content, R.attr.padding};
                TypedArray typedArray2 = context.obtainStyledAttributes(attrs,
                        CustomTextViewStyle, defStyle, defStyleRes);

                int textsize1 = typedArray2.getDimensionPixelSize(0, 10);
                int textcolor1 = typedArray2.getColor(1, Color.BLACK);
                String text1 = typedArray2.getString(2);
                int padding1 = typedArray2.getDimensionPixelSize(3, 0);

                System.out.println("textsize1 = " + textsize1);
                System.out.println("textcolor1 = " + textcolor1);
                System.out.println("text1 = " + text1);
                System.out.println("padding1 = " + padding1);

                //方法三 利用命名空间，根据属性名获取属性值

                int textcolor2 = attrs.getAttributeIntValue(namespace, "textcolor", Color.BLACK);
                //or
                int resouceId = attrs.getAttributeResourceValue(namespace, "text_color", 0);
                if (resouceId > 0) {
                    textcolor2 = context.getResources().getColor(resouceId);
                } else textcolor2 = Color.BLACK;

                int textsize2 = attrs.getAttributeIntValue(namespace, "text_size", 10);
                String text2 = attrs.getAttributeValue(namespace, "text_content");

                int padding2 = attrs.getAttributeIntValue(namespace, "padding", 0);
                System.out.println("textsize2  = " + textsize2);
                System.out.println("textcolor2 = " + textcolor2);
                System.out.println("text2 = " + text2);
                System.out.println("padding2 = " + padding2);
                //namespace is null
                resouceId = attrs.getAttributeResourceValue(null, "text", 0);
                if (resouceId > 0) {
                    String null_namespace = context.getResources().getText(resouceId).toString();
                    System.out.println("null_namespace = " + null_namespace);
                }


                setTextColor(textcolor);
                setTextSize(textsize);
                setText(text + "");
                setPadding(padding, padding, padding, padding);


                //遍历attrs中所有属性和值
                for (int i = 0, m = attrs.getAttributeCount(); i < m; i++) {
                    System.out.println("AttributeName = " + attrs.getAttributeName(i) + ", " +
                            "AttributeValue = " + attrs.getAttributeValue(i));
                }


                //介绍getAttributeNameResource(int index)方法
                int[] ids = new int[attrs.getAttributeCount()];
                for (int i = 0; i < attrs.getAttributeCount(); i++) {
                    ids[i] = attrs.getAttributeNameResource(i);
                    //System.out.println("ids[" + i + "] = 0x" + Integer.toHexString(ids[i]));
                }

                TypedArray a = context.obtainStyledAttributes(attrs, ids, defStyle, 0);

                for (int i = 0; i < attrs.getAttributeCount(); i++) {
                    String attrName = attrs.getAttributeName(i);
                    if (attrName == null) continue;
                    System.out.println("attrName = " + attrName + ",attrValue = " + a.getString(i));
                }
                a.recycle();
                typedArray.recycle();
                typedArray2.recycle();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 介绍TypedArray使用方法
     * 获取 attr中formate 十种类型属性值
     * float,integer,boolean,fraction,string,dimension,color,reference,enum,flag
     * <p/>
     *
     * @param context 上下文环境
     * @param attrs   属性集合
     */
    private void printAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable
                .CustomTextViewStyle, 0, 0);
        System.out.println("typedArray.getChangingConfigurations() = " + Integer.toHexString
                (typedArray.getChangingConfigurations()));
        float float_value = typedArray.getFloat(R.styleable.CustomTextViewStyle_float_value, 0f);
        int integer_value = typedArray.getInteger(R.styleable.CustomTextViewStyle_integer_value, 0);
        boolean boolean_value = typedArray.getBoolean(R.styleable
                .CustomTextViewStyle_boolean_value, false);

        //public float getFraction (int index, int base, int pbase, float defValue)
        // 如果值为10% 则 fraction_value=10%*base
        //如果值格式为10%p,则fraction_value=10%*pbase
        float fraction_value = typedArray.getFraction(R.styleable
                .CustomTextViewStyle_fraction_value, 1, 1, 0);
        String string_value = typedArray.getString(R.styleable.CustomTextViewStyle_string_value);

        //获取像素值，浮点数  eg:27.5625
        float dimension_value_float = typedArray.getDimension(R.styleable
                .CustomTextViewStyle_dimension_value, 0f);
        //将取得浮点像素值四舍五入 eg:28
        int dimension_value = typedArray.getDimensionPixelSize(R.styleable
                .CustomTextViewStyle_dimension_value, 0);
        //将取得浮点像素值直接截取整数部分 eg:27
        int dimension_value_offset_ = typedArray.getDimensionPixelOffset(R.styleable
                .CustomTextViewStyle_dimension_value, 0);


        int color_value = typedArray.getColor(R.styleable.CustomTextViewStyle_color_value, 0);
        int reference_drawable_value = typedArray.getResourceId(R.styleable
                .CustomTextViewStyle_reference_drawable_value, 0);
        int reference_array_value = typedArray.getResourceId(R.styleable
                .CustomTextViewStyle_reference_array_value, 0);
        int enum_value = typedArray.getInt(R.styleable.CustomTextViewStyle_enum_value, -1);
        int flag_value = typedArray.getInt(R.styleable.CustomTextViewStyle_flag_value, -1);


        System.out.println("float_value = [" + float_value + "], integer_value = [" +
                integer_value + "], " +
                "boolean_value = [" + boolean_value + "], fraction_value = [" +
                fraction_value + "], string_value = [" + string_value + "], dimension_value = [" +
                dimension_value + "], color_value = [" + color_value + "], " +
                "reference_drawable_value =" +
                " [0x" +
                Integer.toHexString(reference_drawable_value) + "], enum_value = [" + enum_value
                + "], " +
                "flag_value1 = [" +
                flag_value + "]");


        //后期数据处理,设置左边图片
        Drawable drawable;
        drawable = typedArray.getDrawable(R.styleable.CustomTextViewStyle_reference_drawable_value);
        //or
        drawable = context.getDrawable(reference_drawable_value);
        drawable.setBounds(new Rect(0, 0, 50, 50));
        setCompoundDrawables(drawable, null, null, null);
        //设置文字是否大写，斜体
        if (flag_value >= 0) {
            Typeface typeface = getTypeface();
            setTypeface(Typeface.defaultFromStyle(flag_value));
        }

        //其他方法getTextArray
        CharSequence[] arrays;
        arrays = typedArray.getTextArray(R.styleable.CustomTextViewStyle_reference_array_value);
        //or
        arrays = context.getResources().getTextArray(reference_array_value);
        for (int i = 0; i < arrays.length; i++) {
            System.out.println("arrays[" + i + "] = " + arrays[i]);
        }

        //遍历TypedArray
        for (int i = 0, m = typedArray.getIndexCount(); i < m; i++) {
            System.out.println("typedArray" + i + " type= " + typedArray.getType(i) + " value=" +
                    typedArray.getString(i));
        }


        int textsize = typedArray.getDimensionPixelSize(R.styleable
                .CustomTextViewStyle_text_size, 10);
        int textcolor = typedArray.getColor(R.styleable.CustomTextViewStyle_text_color, Color
                .BLACK);
        String text = typedArray.getString(R.styleable.CustomTextViewStyle_text_content);
        int padding = typedArray.getDimensionPixelSize(R.styleable.CustomTextViewStyle_padding, 0);


        setTextColor(textcolor);
        setTextSize(textsize);
        setText(text);
        setPadding(padding, padding, padding, padding);

        typedArray.recycle();
    }

}
