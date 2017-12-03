package com.wenshao.coursate.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.wenshao.coursate.R;
import com.youth.banner.Banner;

/**
 * Created by wenshao on 2017/12/3.
 * 题库
 */

public class QuestionBankFragment extends Fragment{
    private Context mContext;
    private View mView;

    private static final String TAG = "QuestionBankFragment";
    public static QuestionBankFragment newInstance() {
        return new QuestionBankFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_question_bank, null);
        mContext = getContext();
        initUi();
        // initData();
        return mView;
    }

    private void initUi() {
        FlexibleRichTextView richTextView  = (FlexibleRichTextView)mView.findViewById(R.id.test_text);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("$$\\sum_{i=1}^n a_i=0$$,");

        stringBuilder.append("$$f(x)=x^{x^x}$$");
        stringBuilder.append("$$f(x_1,x_x,\\ldots,x_n) = x_1^2 + x_2^2 + \\cdots + x_n^2 $$");
        stringBuilder.append("$$\\left. \\frac{du}{dx} \\right|_{x=0}.$$");
        stringBuilder.append("f(n) = \\begin{cases} \\frac{n}{2}, & \\text{if } n\\text{ is even} \\\\ 3n+1, & \\text{if } n\\text{ is odd} \\end{cases}");

        stringBuilder.append("$$\\mbox{对任意的$x>0$}, \\mbox{有 }f(x)>0. $$");
        stringBuilder.append("$$\\sqrt[n]{x_r_r_r} $$");
        stringBuilder.append("$$ \\frac{x+2}{x} \\sqrt{x} $$");
        stringBuilder.append("$$ \\[f(x,y,z) = 3y^2 z \\left( 3 + \\frac{7x+5}{1 + y^2} \\right).\\] $$");

        stringBuilder.append("$$ P(x|c)=\\frac{P(c|x)\\cdot P(x)}{P(x)} $$");
        stringBuilder.append("$$ \\Large x=\\frac{-b\\pm\\sqrt{b^2-4ac}}{2a} $$");
        stringBuilder.append("$$ \\sum_{i=1}^n i = \\frac{n(n+1)}2 $$");
        stringBuilder.append("$$ f(x)=\\int_{-\\infty}^x e^{-t^2}dt $$ 这道公式我也不知道怎么做");

        stringBuilder.append("$$ \\cos 2\\theta  = \\cos^2 \\theta - \\sin^2 \\theta = 2 \\cos^2 \\theta - 1. $$");

        stringBuilder.append("$$ \\displaystyle= \\frac{k(k+1)}{2}+k+1 $$");
        stringBuilder.append("$$ \\frac{x}{2}-3=0 $$");
        stringBuilder.append("$$ x=\\frac{3}{2} $$");
        stringBuilder.append("$$ \\[ \\sum_{k=1}^n k^2 = \\frac{1}{2} n (n+1).\\] $$");

        richTextView.setText(stringBuilder.toString());
    }
}
