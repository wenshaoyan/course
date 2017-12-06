package com.wenshao.coursate.adapter;

import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenshao.coursate.R;
import com.wenshao.coursate.bean.QuestionBean;

import java.util.List;

/**
 * Created by wenshao on 2017/12/6.
 * 问题概要列表数据适配器
 */

public class QuestionOutlineListAdapter extends ArrayAdapter {

    private int mResourceId;
    private List<QuestionBean> mListData;

    public QuestionOutlineListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<QuestionBean> objects) {
        super(context, resource, objects);
        mResourceId = resource;
        mListData = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        QuestionBean questionBean = (QuestionBean) getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View view = inflater.inflate(mResourceId, null);
        TextView question_status = (TextView) view.findViewById(R.id.question_status);
        if (questionBean != null && questionBean.getQuestionAnswerBean()!=null){
            question_status.setBackgroundResource(R.drawable.shape_answered_icon);
            question_status.setText(R.string.answered);
        }else{
            question_status.setBackgroundResource(R.drawable.shape_unanswered_icon);
            question_status.setText(R.string.unanswered);
        }
        return view;

    }


}
