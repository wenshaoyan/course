package com.wenshao.coursate.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.wenshao.coursate.R;
import com.wenshao.coursate.adapter.QuestionAdapter;
import com.wenshao.coursate.adapter.QuestionOutlineListAdapter;
import com.wenshao.coursate.bean.QuestionAnswerBean;
import com.wenshao.coursate.bean.QuestionBean;
import com.wenshao.coursate.listener.AnswerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wenshao on 2017/12/3.
 * 答题系统
 */

public class AnswerActivity extends ToolBarActivity implements View.OnClickListener {
    private Context mContext;
    private MenuItem menuItem;
    private final static String TAG = "AnswerActivity";
    private List<QuestionBean> mListData;

    private Toolbar mToolbar;
    private ViewPager mVpQuestion;
    private int mQuestionMaxIndex;
    private LinearLayout mQuestionList;
    private TextView mQuestionListTitle;
    private LinearLayout mQuestionErrorList;
    private LinearLayout mQuestionPre;
    private LinearLayout mQuestionNext;
    private TextView toolbar_answer_time;
    private TextView toolbar_answer_post;

    private long midTime = 20;
    private Timer timerCountDown = new Timer();
    private String pageStatus = "answer";

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {   // 下一题
                int currentItem = mVpQuestion.getCurrentItem();
                mVpQuestion.setCurrentItem(currentItem + 1);
            } else if (msg.what == 2) {
                int currentItem = mVpQuestion.getCurrentItem();
                mQuestionListTitle.setText((currentItem + 1) + "/" + (mQuestionMaxIndex + 1));
            } else if (msg.what == 3) { // 更新时间
                toolbar_answer_time.setText(AnswerActivity.this.getCurrentFormat());
            } else if (msg.what == 4) { // 显示提交提示框
                AnswerActivity.this.popupPostHint();
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_answer);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) supportActionBar.setDisplayHomeAsUpEnabled(false);
        initUi();
        initData();
        startCountDown();
    }

    private void initUi() {
        mQuestionErrorList = (LinearLayout) findViewById(R.id.question_error_list);
        mQuestionList = (LinearLayout) findViewById(R.id.question_list);
        mQuestionListTitle = (TextView) findViewById(R.id.question_list_title);
        mQuestionPre = (LinearLayout) findViewById(R.id.question_pre);
        mQuestionNext = (LinearLayout) findViewById(R.id.question_next);
        mQuestionErrorList.setOnClickListener(this);
        mQuestionList.setOnClickListener(this);
        mQuestionPre.setOnClickListener(this);
        mQuestionNext.setOnClickListener(this);

        mVpQuestion = (ViewPager) findViewById(R.id.vp_question);
    }

    private void initData() {

        mListData = produceData();
        mQuestionMaxIndex = mListData.size() - 1;
        QuestionAdapter questionAdapter = new QuestionAdapter(mContext, mListData);

        mVpQuestion.setAdapter(questionAdapter);
        mVpQuestion.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // Log.i(TAG, "onPageSelected: =============");
                mHandler.sendEmptyMessage(2);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        questionAdapter.setAnswerListener(new AnswerListener() {
            @Override
            public void onAnswerComplete() {
                boolean b = nextQuestion(500);
                if (!b) {    // 答题完成 提示提交答题
                    Log.i(TAG, "onAnswerComplete: " + "===========");
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
        toolbar_answer_time = (TextView) inflate.findViewById(R.id.toolbar_answer_time);
        ImageView answer_back = (ImageView) inflate.findViewById(R.id.answer_back);
        toolbar_answer_post = (TextView) inflate.findViewById(R.id.toolbar_answer_post);
        /*Drawable drawable1 = ContextCompat.getDrawable(mContext, R.drawable.icon_practice_time);

        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(),
                drawable1.getMinimumHeight());
        mToolbarShowTime.setCompoundDrawables(drawable1, null, null, null);*/


        answer_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnswerActivity.this.onBackPressed();
            }
        });
        toolbar_answer_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageStatus.equals("answer")) AnswerActivity.this.popupPostHint();
                else if (pageStatus.equals("query")) reopenActivity();

            }
        });
        // toolbar_answer_post.setText();
    }

    protected void actionAlertDialog() {

        AlertDialog.Builder builder;
        final AlertDialog alertDialog;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_question_outline, null);
        ListView listView = (ListView) view.findViewById(R.id.question_outline_list);
        Button dialog_close = (Button) view.findViewById(R.id.dialog_close);

        QuestionOutlineListAdapter adapter = new QuestionOutlineListAdapter(mContext, R.layout.question_item_view, mListData);
        listView.setAdapter(adapter);
        builder = new AlertDialog.Builder(mContext, R.style.questionOutlineAlertDialog);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int widthPixels = dm.widthPixels;
        int heightPixels = dm.heightPixels;
        Window window = alertDialog.getWindow();
        android.view.WindowManager.LayoutParams p = window.getAttributes();  //获取对话框当前的参数值
        p.width = (int) (widthPixels * 0.85);   //高度设置为屏幕的0.85
        p.height = (int) (heightPixels * 0.85);    //宽度设置为屏幕的0.85
        window.setGravity(Gravity.CENTER);
        window.getDecorView().setPadding(0, 0, 0, 0);
        //window.setBackgroundDrawable(new ColorDrawable());
        window.setAttributes(p);     //设置生效
        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                AnswerActivity.this.goonCountDown();
            }
        });

        // 中断定时器
        this.breakCountDown();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.question_next:
                nextQuestion(10);
                break;
            case R.id.question_pre:
                preQuestion();
                break;
            case R.id.question_list:
                actionAlertDialog();
                break;
            default:
                break;
        }
    }

    /**
     * 下一题
     */
    private boolean nextQuestion(final int time) {
        final int currentItem = mVpQuestion.getCurrentItem();
        if (currentItem < mQuestionMaxIndex) { // 可以移动
            // 延时加载下一题
            mHandler.sendEmptyMessageDelayed(1, time);
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

    /**
     * 弹出提交提示框
     */
    private void popupPostHint() {
        breakCountDown();
        new AlertDialog.Builder(mContext).setTitle("是否提交")//设置对话框标题
                .setMessage("请确认所有数据都保存后再推出系统！")//设置显示的内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        setAdapterShowInfo();
                        checkQuestion();
                        // 回到第一个
                        mVpQuestion.setCurrentItem(0);
                        // 设置当前页面的状态  answer:答题状态 query:查看状态
                        setPageStatus("query");
                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AnswerActivity.this.goonCountDown();
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        AnswerActivity.this.goonCountDown();
                    }
                })
                .show();//在按键响应事件中显示此对话框
    }

    /**
     * 获取当前midTime对应的格式化时间
     *
     * @return 时间
     */
    private String getCurrentFormat() {
        long hh = midTime / 60 / 60 % 60;
        long mm = midTime / 60 % 60;
        long ss = midTime % 60;
        return hh + ":" + mm + ":" + ss;
    }

    /**
     * 开启新倒计时
     */
    private void startCountDown() {
        if (midTime <= 0) return;
        if (timerCountDown == null) timerCountDown = new Timer();
        timerCountDown.schedule(new TimerTask() {
            public void run() {
                midTime--;
                if (midTime == 0) mHandler.sendEmptyMessage(4);  // 时间到 弹出提交提示框
                else mHandler.sendEmptyMessage(3);
            }
        }, 0, 1000);
    }

    /**
     * 中断倒计时
     */
    private void breakCountDown() {
        if (timerCountDown != null) {
            timerCountDown.cancel();
            timerCountDown = null;
        }

    }

    /**
     * 结束倒计时
     */
    private void endCountDown() {
        this.breakCountDown();
        midTime = 0;
    }

    /**
     * 继续倒计时
     */
    private void goonCountDown() {
        this.startCountDown();
    }

    /**
     * 打分
     */
    private void checkQuestion() {
        float totalScore = 0;
        for (QuestionBean questionBean : mListData) {
            QuestionAnswerBean questionAnswerBean = questionBean.getQuestionAnswerBean();
            if (questionAnswerBean != null) {    // 已答
                if (questionBean.getType().equals(QuestionBean.RADIO_CHOOSE_TYPE)) { // 单选题打分规则
                    if (questionBean.getCorrect_answer().equals(questionAnswerBean.getContent())) {  // 答对
                        totalScore += questionBean.getScore();
                    } else {

                    }
                } else if (questionBean.getType().equals(QuestionBean.MULTIPLE_CHOOSE_TYPE)) {   // 多选题打分规则

                }

            } else {    // 未答

            }
        }
        Log.i(TAG, "checkQuestion: " + totalScore);

    }

    /**
     * 设置adapter中显示解析详情
     */
    private void setAdapterShowInfo(){
        QuestionAdapter questionAdapter = (QuestionAdapter)mVpQuestion.getAdapter();
        questionAdapter.setShowInfo(true);
    }

    /**
     * 设置当前页面的状态
     * @param status answer:答题状态 query:查看状态
     */
    private void setPageStatus(String status){
        if (status.equals("query")){
            pageStatus = status;
            endCountDown();
            toolbar_answer_time.setText("查看解析");
            toolbar_answer_post.setText("重新答题");
        }

    }
    /**
     * 重新打开当前的AnswerActivity
     */
    public void reopenActivity() {
        startActivity(new Intent(mContext,AnswerActivity.class));
        finish();
    }
    private List<QuestionBean> produceData() {
        ArrayList<QuestionBean> questionBeans = new ArrayList<>();

        QuestionBean questionBean1 = new QuestionBean();
        questionBean1.setId("1");
        questionBean1.setTitle("您的年龄是？");
        questionBean1.setType("rc");
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
        questionBean1.setSingle(true);


        QuestionBean questionBean2 = new QuestionBean();
        questionBean2.setId("2");
        questionBean2.setTitle("您的工作是？");
        questionBean2.setType("rc");
        questionBean2.setAnalysis("戴维的法阵");
        questionBean2.setCorrect_answer("1");
        questionBean2.setScore(10);
        List<String> options2 = new ArrayList<>();
        options2.add("学生");
        options2.add("公务单位");
        options2.add("工薪一族");
        options2.add("自己当老板");
        options2.add("其他");
        questionBean2.setSingle(true);
        questionBean2.setOptions(options2);


        QuestionBean questionBean3 = new QuestionBean();
        questionBean3.setId("3");
        questionBean3.setTitle("工笔是哪种绘画形式的技法3");
        questionBean3.setType("rc");
        questionBean3.setAnalysis("戴维的法阵");
        questionBean3.setCorrect_answer("1");
        questionBean3.setScore(10);
        List<String> options3 = new ArrayList<>();
        options3.add("水彩画");
        options3.add("油画");
        options3.add("水粉画");
        options3.add("国画");
        questionBean3.setSingle(true);
        questionBean3.setOptions(options3);

        QuestionBean questionBean4 = new QuestionBean();
        questionBean4.setId("4");
        questionBean4.setTitle("工笔是哪种绘画形式的技4");
        questionBean4.setType("rc");
        questionBean4.setAnalysis("戴维的法阵");
        questionBean4.setCorrect_answer("1");
        questionBean4.setScore(10);
        List<String> options4 = new ArrayList<>();
        options4.add("水彩画");
        options4.add("油画");
        options4.add("水粉画");
        options4.add("国画");
        questionBean4.setSingle(true);
        questionBean4.setOptions(options4);

        QuestionBean questionBean5 = new QuestionBean();
        questionBean5.setId("5");
        questionBean5.setTitle("工笔是哪种绘画形式的技5");
        questionBean5.setType("rc");
        questionBean5.setAnalysis("戴维的法阵");
        questionBean5.setCorrect_answer("1");
        questionBean5.setScore(10);
        List<String> options5 = new ArrayList<>();
        options5.add("水彩画");
        options5.add("油画");
        options5.add("水粉画");
        options5.add("国画");
        questionBean5.setSingle(true);
        questionBean5.setOptions(options5);

        QuestionBean questionBean6 = new QuestionBean();
        questionBean6.setId("6");
        questionBean6.setTitle("工笔是哪种绘画形式的技法6");
        questionBean6.setType("rc");
        questionBean6.setAnalysis("戴维的法阵");
        questionBean6.setCorrect_answer("1");
        questionBean6.setScore(10);
        List<String> options6 = new ArrayList<>();
        options6.add("水彩画");
        options6.add("油画");
        options6.add("水粉画");
        options6.add("国画");
        questionBean6.setSingle(true);
        questionBean6.setOptions(options6);

        QuestionBean questionBean7 = new QuestionBean();
        questionBean7.setId("7");
        questionBean7.setTitle("工笔是哪种绘画形式的技7");
        questionBean7.setType("rc");
        questionBean7.setAnalysis("戴维的法阵");
        questionBean7.setCorrect_answer("1");
        questionBean7.setScore(10);
        List<String> options7 = new ArrayList<>();
        options7.add("水彩画");
        options7.add("油画");
        options7.add("水粉画");
        options7.add("国画");
        questionBean7.setSingle(true);
        questionBean7.setOptions(options7);


        QuestionBean questionBean8 = new QuestionBean();
        questionBean8.setId("8");
        questionBean8.setTitle("工笔是哪种绘画形式的技法81111111111111111111111111111");
        questionBean8.setType("rc");
        questionBean8.setAnalysis("戴维的法阵");
        questionBean8.setCorrect_answer("1");
        questionBean8.setScore(10);
        List<String> options8 = new ArrayList<>();
        options8.add("水彩画");
        options8.add("油画");
        options8.add("水粉画");
        options8.add("国画");
        questionBean8.setSingle(true);
        questionBean8.setOptions(options8);

        questionBeans.add(questionBean1);
        questionBeans.add(questionBean2);
        questionBeans.add(questionBean3);
        questionBeans.add(questionBean4);
        questionBeans.add(questionBean5);
        questionBeans.add(questionBean6);
        questionBeans.add(questionBean7);
        questionBeans.add(questionBean8);
        return questionBeans;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.endCountDown();
    }
}
