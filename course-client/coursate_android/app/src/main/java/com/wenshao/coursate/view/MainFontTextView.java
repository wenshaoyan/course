package com.wenshao.coursate.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.wenshao.coursate.R;

/**
 * Created by wenshao on 2017/9/23.
 * app主字体TextView
 * 字体为微软雅黑
 *
 */

public class MainFontTextView extends AppCompatTextView {
    private  final static String TAG = "IconFontTextView";


    public MainFontTextView(Context context) {
        this(context,null);
    }

    public MainFontTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MainFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "Microsoft-YaHei.ttf");
        setTypeface(font);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.MainFontTextView);
        mTypedArray.getResources();
    }


}
