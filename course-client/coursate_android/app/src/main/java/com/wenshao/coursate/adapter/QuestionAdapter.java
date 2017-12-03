package com.wenshao.coursate.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenshao.coursate.R;

import java.util.List;

/**
 * Created by wenshao on 2017/12/3.
 * 问答系统问题适配器
 */

public class QuestionAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mListView;

    public QuestionAdapter(Context context, List<String> listView) {
        mContext = context;
        mListView = listView;
    }

    @Override
    public int getCount() {
        return mListView.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.question_choose_view, null);
        container.addView(view);
        return mListView.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //container.removeView();
    }
}
