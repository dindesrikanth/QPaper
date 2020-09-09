package com.example.questionpaper.Model;

import java.util.Date;

public class ScoreModel {
    private long test_id;


    private long user_id;

    private long time_spent;
    //private long total_marks;
    // private long time_spent;
    private long achieved_marks;
    private long ques_right;
    private long ques_wrong;
    private long ques_unattempted;


    public ScoreModel(long test_id, long user_id, long time_spent, long achieved_marks, long ques_right, long ques_wrong, long ques_unattempted) {
        this.test_id = test_id;
        this.user_id = user_id;
        this.time_spent = time_spent;
        this.achieved_marks = achieved_marks;
        this.ques_right = ques_right;
        this.ques_wrong = ques_wrong;
        this.ques_unattempted = ques_unattempted;
    }

    public long getTest_id() {
        return test_id;
    }

    public void setTest_id(long test_id) {
        this.test_id = test_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getTime_spent() {
        return time_spent;
    }

    public void setTime_spent(long time_spent) {
        this.time_spent = time_spent;
    }

    public long getAchieved_marks() {
        return achieved_marks;
    }

    public void setAchieved_marks(long achieved_marks) {
        this.achieved_marks = achieved_marks;
    }

    public long getQues_right() {
        return ques_right;
    }

    public void setQues_right(long ques_right) {
        this.ques_right = ques_right;
    }

    public long getQues_wrong() {
        return ques_wrong;
    }

    public void setQues_wrong(long ques_wrong) {
        this.ques_wrong = ques_wrong;
    }

    public long getQues_unattempted() {
        return ques_unattempted;
    }

    public void setQues_unattempted(long ques_unattempted) {
        this.ques_unattempted = ques_unattempted;
    }
}
