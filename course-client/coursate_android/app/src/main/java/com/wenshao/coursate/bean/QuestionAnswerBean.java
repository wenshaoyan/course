package com.wenshao.coursate.bean;

/**
 * Created by Administrator on 2017/12/6.
 * 问答系统 用户回答对象
 */

public class QuestionAnswerBean {
    private String content;         // 回答内容
    private int answer_time;     // 答题时间 ms

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAnswer_time() {
        return answer_time;
    }

    public void setAnswer_time(int answer_time) {
        this.answer_time = answer_time;
    }

    @Override
    public String toString() {
        return "QuestionAnswerBean{" +
                "content='" + content + '\'' +
                ", answer_time='" + answer_time + '\'' +
                '}';
    }
}
