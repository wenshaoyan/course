package com.wenshao.coursate.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wenshao.coursate.R;
import com.wenshao.coursate.adapter.QuestionAdapter;
import com.wenshao.coursate.bean.QuestionBean;
import com.wenshao.coursate.listener.AnswerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenshao on 2017/12/3.
 * 答题系统
 */

public class AnswerActivity extends ToolBarActivity implements View.OnClickListener {
    private Context mContext;
    private MenuItem menuItem;
    private final static String TAG = "AnswerActivity";
    private Toolbar mToolbar;
    private TextView mToolbarShowTime;
    private TextView mToolbarTitle;
    private ViewPager mVpQuestion;
    private int mQuestionMaxIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_answer);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) supportActionBar.setDisplayHomeAsUpEnabled(false);
        initUi();
        initData();
    }

    private void initUi() {
        LinearLayout mQuestionErrorList = (LinearLayout) findViewById(R.id.question_error_list);
        LinearLayout mQuestionList = (LinearLayout) findViewById(R.id.question_list);
        LinearLayout mQuestionPre = (LinearLayout) findViewById(R.id.question_pre);
        LinearLayout mQuestionNext = (LinearLayout) findViewById(R.id.question_next);
        mQuestionErrorList.setOnClickListener(this);
        mQuestionList.setOnClickListener(this);
        mQuestionPre.setOnClickListener(this);
        mQuestionNext.setOnClickListener(this);

        mVpQuestion = (ViewPager) findViewById(R.id.vp_question);
    }

    private void initData() {

        List<QuestionBean> questionBeans = produceData();
        mQuestionMaxIndex = questionBeans.size() - 1;
        QuestionAdapter questionAdapter = new QuestionAdapter(mContext, questionBeans);

        mVpQuestion.setAdapter(questionAdapter);
        mVpQuestion.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG, "onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "onPageSelected");

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i(TAG, "onPageScrollStateChanged");

            }
        });

        questionAdapter.setAnswerListener(new AnswerListener() {
            @Override
            public void onAnswerComplete() {
                boolean b = nextQuestion();
                if (!b){    // 答题完成 提示提交答题
                    Log.i(TAG, "onAnswerComplete: "+"===========");
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*switch (item.getItemId()) {
            case R.id.action_shopping_cart:
                break;

        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateCustomToolBar(Toolbar toolbar) {
        super.onCreateCustomToolBar(toolbar);
        mToolbar = toolbar;
        toolbar.showOverflowMenu();
        showToolbarHome();
    }

    private void showToolbarHome() {
        mToolbar.removeAllViews();  //清除原有的toolbar
        View inflate = getLayoutInflater().inflate(R.layout.toolbar_answer, mToolbar);
        mToolbarShowTime = (TextView) inflate.findViewById(R.id.toolbar_answer_time);
        mToolbarTitle = (TextView) inflate.findViewById(R.id.toolbar_answer_title);
//        Drawable drawable1 = getBaseContext().getResources().getDrawable(
//                R.drawable.icon_practice_time);
        Drawable drawable1 = ContextCompat.getDrawable(mContext, R.drawable.icon_practice_time);

        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(),
                drawable1.getMinimumHeight());
        mToolbarShowTime.setCompoundDrawables(drawable1, null, null, null);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.question_next:
                nextQuestion();
                break;
            case R.id.question_pre:
                preQuestion();
                break;
            default:
                break;
        }
    }

    /**
     * 下一题
     */
    private boolean nextQuestion() {
        int currentItem = mVpQuestion.getCurrentItem();
        if (currentItem < mQuestionMaxIndex) { // 可以移动
            mVpQuestion.setCurrentItem(currentItem+1);
            return true;
        }
        return false;
    }

    /**
     * 上一题
     */
    private void preQuestion() {
        int currentItem = mVpQuestion.getCurrentItem();
        if (currentItem != 0) { // 可以移动
            mVpQuestion.setCurrentItem(currentItem - 1);
        }

    }

    private List<QuestionBean> produceData() {
        ArrayList<QuestionBean> questionBeans = new ArrayList<>();

        QuestionBean questionBean1 = new QuestionBean();
        questionBean1.setId("1");
        questionBean1.setTitle("您的年龄是？");
        questionBean1.setType("choose");
        questionBean1.setAnalysis("戴维的法阵");
        questionBean1.setCorrect_answer("1");
        questionBean1.setScore(10);
        List<String> options1 = new ArrayList<>();
        options1.add("18岁以下");
        options1.add("18岁至25岁");
        options1.add("25岁至35岁");
        options1.add("35岁至45岁");
        options1.add("45岁以上");
        questionBean1.setOptions(options1);
        questionBean1.setIsSingle(1);


        QuestionBean questionBean2 = new QuestionBean();
        questionBean2.setId("2");
        questionBean2.setTitle("您的工作是？");
        questionBean2.setType("choose");
        questionBean2.setAnalysis("戴维的法阵");
        questionBean2.setCorrect_answer("1");
        questionBean2.setScore(10);
        List<String> options2 = new ArrayList<>();
        options2.add("学生");
        options2.add("公务单位");
        options2.add("工薪一族");
        options2.add("自己当老板");
        options2.add("其他");
        questionBean2.setIsSingle(1);
        questionBean2.setOptions(options2);

        questionBeans.add(questionBean1);
        questionBeans.add(questionBean2);
        return questionBeans;

    }
}
