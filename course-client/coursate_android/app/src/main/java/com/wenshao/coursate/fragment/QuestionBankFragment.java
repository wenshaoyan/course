package com.wenshao.coursate.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.wenshao.coursate.R;
import com.wenshao.coursate.activity.AnswerActivity;
import com.wenshao.coursate.adapter.PapersAdapter;
import com.wenshao.coursate.adapter.QuestionAdapter;
import com.wenshao.coursate.bean.PaperBean;
import com.wenshao.coursate.listener.PaperListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wenshao on 2017/12/3.
 * 题库
 */

public class QuestionBankFragment extends Fragment{
    private Context mContext;
    private View mView;
    private ListView papers_list;

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
        initData();
        return mView;
    }



    private void initUi() {
        papers_list = (ListView) mView.findViewById(R.id.papers_list);

    }
    private void initData(){
        PapersAdapter papersAdapter = new PapersAdapter(getPaperList(), mContext);
        papersAdapter.setPaperListener(new PaperListener() {
            @Override
            public void onPaperOpenListener() {
                startActivity(new Intent(mContext,AnswerActivity.class));
            }
        });
        papers_list.setAdapter(papersAdapter);


    }
    private List<PaperBean> getPaperList(){
        ArrayList<PaperBean> paperBeen = new ArrayList<>();
        paperBeen.add(new PaperBean(1,"数据库系统原理"));

        return paperBeen;
    }


    /*FlexibleRichTextView richTextView  = (FlexibleRichTextView)mView.findViewById(R.id.test_text);
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

        richTextView.setText(stringBuilder.toString());*/
}
