package com.wenshao.coursate.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wenshao.coursate.R;
import com.wenshao.coursate.bean.QuestionBean;
import com.wenshao.coursate.util.DensityUtil;

import java.util.List;

/**
 * Created by wenshao on 2017/12/3.
 * 问答系统问题适配器
 */

public class QuestionAdapter extends PagerAdapter {
    private Context mContext;
    private List<QuestionBean> mListData;

    public QuestionAdapter(Context context, List<QuestionBean> listData) {
        mContext = context;
        mListData = listData;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.question_choose_view, null);
        TextView questionType = (TextView) view.findViewById(R.id.question_type);
        TextView questionTitle = (TextView) view.findViewById(R.id.question_title);
        RadioGroup questionOptions = (RadioGroup) view.findViewById(R.id.question_options);

        QuestionBean questionBean = mListData.get(position);

        setQuestionType(questionBean.getType(),questionType);
        questionTitle.setText(questionBean.getTitle());
        addOption(questionOptions,questionBean.getOptions());

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private void setQuestionType(String type,TextView view){
        switch (type){
            case QuestionBean.CHOOSE_TYPE:
                view.setText("(单选题)");
                break;
            default:
                view.setText("(单选题)");
                break;
        }

    }
    private void addOption(RadioGroup radiogroup,List<String> list){
        if (list == null){
            return;
        }
        int i = DensityUtil.dip2px(mContext, 10);
        for(String e: list){
            RadioButton button=new RadioButton(mContext);

            radiogroup.addView(button);

            LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) button
                    .getLayoutParams();
            button.setLayoutParams(layoutParams);
            button.setPadding(i,i,i,i);
            button.setText(e);
        }


    }
    private class ViewHolder {
        TextView questionType;
        TextView questionTitle;

    }
}
