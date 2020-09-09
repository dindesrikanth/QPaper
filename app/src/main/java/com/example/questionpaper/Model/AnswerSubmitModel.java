package com.example.questionpaper.Model;

import java.util.Date;


public class AnswerSubmitModel {
    private String user_id;

    private String course_id;

    private String sub_id;
    private String test_id;

    private String user_resp;

    private String time_spent;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getTest_id() {
        return test_id;
    }

    public void setTest_id(String test_id) {
        this.test_id = test_id;
    }

    public String getUser_resp() {
        return user_resp;
    }

    public void setUser_resp(String user_resp) {
        this.user_resp = user_resp;
    }

    public String getTime_spent() {
        return time_spent;
    }

    public void setTime_spent(String time_spent) {
        this.time_spent = time_spent;
    }

    public AnswerSubmitModel(String user_id, String course_id, String sub_id, String test_id, String user_resp, String time_spent) {
        this.user_id = user_id;
        this.course_id = course_id;
        this.sub_id = sub_id;
        this.test_id = test_id;
        this.user_resp = user_resp;
        this.time_spent = time_spent;
    }
}
