package com.wenshao.coursate.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import com.wenshao.coursate.R;

/**
 * Created by Administrator on 2017/12/6.
 * 添加控件的问题id
 */

public class CustomRadioGroup extends RadioGroup {
    private String businessId;
    public CustomRadioGroup(Context context) {
        // super(context);
        this(context,null);
    }

    public CustomRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomRadioGroup);
        businessId = mTypedArray.getString(R.styleable.CustomRadioGroup_business_id);
        mTypedArray.recycle();

    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
}
