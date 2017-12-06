package com.wenshao.coursate.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wenshao.coursate.R;
import com.wenshao.coursate.bean.QuestionAnswerBean;
import com.wenshao.coursate.bean.QuestionBean;
import com.wenshao.coursate.listener.AnswerListener;
import com.wenshao.coursate.util.DensityUtil;
import com.wenshao.coursate.view.CustomRadioGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wenshao on 2017/12/3.
 * 问答系统问题适配器
 */

public class QuestionAdapter extends PagerAdapter implements CustomRadioGroup.OnCheckedChangeListener {
    private Context mContext;
    private List<QuestionBean> mListData;
    private final static String[] SERIAL_NUMBER = {"A.", "B.", "C.", "D.", "E.", "F.", "G."};
    private final static String TAG = "QuestionAdapter";
    private final static int[] OPTION_ID = {R.id.option_0, R.id.option_1, R.id.option_2, R.id.option_3, R.id.option_4,
            R.id.option_5, R.id.option_6};

    private AnswerListener mAnswerListener;
    private Map<String, Integer> dataIndexMap = new HashMap<>();
    private List<View> mListView = new ArrayList<>();


    public QuestionAdapter(Context context, List<QuestionBean> listData) {
        mContext = context;
        mListData = listData;
        int index = 0;
        LayoutInflater inflater = LayoutInflater.from(mContext);


        for (QuestionBean questionBean : listData) {
            dataIndexMap.put(questionBean.getId(), index++);
            View view = inflater.inflate(R.layout.question_choose_view, null);
            mListView.add(view);
        }
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mListView.get(position);

        QuestionBean questionBean = mListData.get(position);
        // view 已经加载过数据
        if (questionBean.isInit()) {
            container.addView(view);
            return view;
        }

        CustomRadioGroup questionOptions = (CustomRadioGroup) view.findViewById(R.id.question_options);

        TextView questionType = (TextView) view.findViewById(R.id.question_type);
        TextView questionTitle = (TextView) view.findViewById(R.id.question_title);


        setQuestionType(questionBean.getType(), questionType);
        questionTitle.setText(questionBean.getTitle());
        addOption(questionOptions, questionBean.getOptions());
        questionOptions.setOnCheckedChangeListener(this);
        questionOptions.setBusinessId(questionBean.getId());
        questionBean.setInit(true); // 设置状态为已经初始化
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.i(TAG, "destroyItem: ");
        container.removeView((View) object);
    }

    private void setQuestionType(String type, TextView view) {
        switch (type) {
            case QuestionBean.CHOOSE_TYPE:
                view.setText("(单选题)");
                break;
            default:
                view.setText("(单选题)");
                break;
        }

    }

    private void addOption(RadioGroup radiogroup, List<String> list) {
        if (list == null) {
            return;
        }
        if (list.size() > SERIAL_NUMBER.length) {
            return;
        }
        int i = DensityUtil.dip2px(mContext, 10);
        int index = 0;
        for (String e : list) {
            RadioButton button = new RadioButton(mContext);
            radiogroup.addView(button);
            button.setId(OPTION_ID[index]);
            /*LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) button
                    .getLayoutParams();*/
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
            button.setLayoutParams(layoutParams);
            button.setPadding(i, i, i, i);
            button.setText(SERIAL_NUMBER[index] + " " + e);

            index++;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        CustomRadioGroup customRadioGroup = (CustomRadioGroup) radioGroup;
        int checkIndex = 0;
        for (int j = 0; j < OPTION_ID.length; j++) {
            int v = OPTION_ID[j];
            if (v == i) {
                checkIndex = j;
                break;
            }
        }
        // 触发单选答题完成事件
        if (mAnswerListener != null) mAnswerListener.onAnswerComplete();

        QuestionAnswerBean questionAnswerBean = new QuestionAnswerBean();
        questionAnswerBean.setContent(String.valueOf(checkIndex));
        questionAnswerBean.setAnswer_time(1);

        setQuestionAnswer(customRadioGroup, questionAnswerBean);
        Log.i(TAG, "onCheckedChanged: " + SERIAL_NUMBER[checkIndex] + "========" + customRadioGroup.getBusinessId());

    }

    /**
     * 设置问题的用户回答
     *
     * @return 是否设置成功
     */
    private boolean setQuestionAnswer(CustomRadioGroup customRadioGroup, QuestionAnswerBean questionAnswerBean) {
        if (dataIndexMap.containsKey(customRadioGroup.getBusinessId())) {
            int indexes = dataIndexMap.get(customRadioGroup.getBusinessId());
            if (mListData.size() > indexes) {
                QuestionBean questionBean = mListData.get(indexes);
                questionBean.setQuestionAnswerBean(questionAnswerBean);
                return true;
            }
        }
        return false;
    }

    /**
     * 设置监听事件
     *
     * @param answerListener AnswerListener对象
     */
    public void setAnswerListener(AnswerListener answerListener) {
        mAnswerListener = answerListener;
    }

    private class ViewHolder {
        TextView questionType;
        TextView questionTitle;

    }
}
