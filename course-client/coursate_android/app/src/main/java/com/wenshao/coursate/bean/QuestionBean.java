package com.wenshao.coursate.bean;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/12/4.
 * 问题对象
 */

public class QuestionBean {
    public final static String CHOOSE_TYPE = "choose";

    private String id;               // 问题id
    private String title;           // 问题题目
    private String type;            // 问题类型
    private String analysis;        // 试题分析
    private String correct_answer;  // 正确答案
    private int score;              // 分值
    private List<String> options;       // 问题选项
    private boolean isSingle;           // 是否单选
    private QuestionAnswerBean questionAnswerBean;
    private boolean isInit;         // 是否被初始化


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    public boolean isInit() {
        return isInit;
    }

    public void setInit(boolean init) {
        isInit = init;
    }

    public QuestionAnswerBean getQuestionAnswerBean() {
        return questionAnswerBean;
    }

    public void setQuestionAnswerBean(QuestionAnswerBean questionAnswerBean) {
        this.questionAnswerBean = questionAnswerBean;
    }

    @Override
    public String toString() {
        return "QuestionBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", analysis='" + analysis + '\'' +
                ", correct_answer='" + correct_answer + '\'' +
                ", score=" + score +
                ", options=" + options +
                ", isSingle=" + isSingle +
                '}';
    }
}
