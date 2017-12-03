package com.wenshao.coursate.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext =this;
        setContentView(R.layout.activity_answer);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar!=null)supportActionBar.setDisplayHomeAsUpEnabled(false);
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
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("1");
        strings.add("1");
        strings.add("1");
        strings.add("1");
        strings.add("1");
        strings.add("1");

        QuestionAdapter questionAdapter = new QuestionAdapter(mContext, strings);


        mVpQuestion.setAdapter(questionAdapter);
        mVpQuestion.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG,"onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG,"onPageSelected");

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i(TAG,"onPageScrollStateChanged");

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
        mToolbarShowTime =(TextView) inflate.findViewById(R.id.toolbar_answer_time);
        mToolbarTitle =(TextView) inflate.findViewById(R.id.toolbar_answer_title);
        Drawable drawable1 = getBaseContext().getResources().getDrawable(
                R.drawable.icon_practice_time);
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(),
                drawable1.getMinimumHeight());
        mToolbarShowTime.setCompoundDrawables(drawable1, null, null, null);

    }

    @Override
    public void onClick(View v) {

    }
}
