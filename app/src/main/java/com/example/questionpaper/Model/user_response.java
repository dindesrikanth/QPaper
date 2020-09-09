package com.example.questionpaper.Model;

import java.util.Date;


public class user_response {
    private long user_id;

    private long course_id;

    private long sub_id;
    private long test_id;

    private String user_resp;

    private long time_spent;

    private Date updatedAt = new Date();

    private String updatedBy;

    private Date createdAt = new Date();

    private String createdBy;


    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }

    public long getSub_id() {
        return sub_id;
    }

    public void setSub_id(long sub_id) {
        this.sub_id = sub_id;
    }

    public long getTest_id() {
        return test_id;
    }

    public void setTest_id(long test_id) {
        this.test_id = test_id;
    }

    public String getUser_resp() {
        return user_resp;
    }

    public void setUser_resp(String user_resp) {
        this.user_resp = user_resp;
    }

    public long getTime_spent() {
        return time_spent;
    }

    public void setTime_spent(long time_spent) {
        this.time_spent = time_spent;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    public user_response(long user_id, long course_id, long sub_id, long test_id, String user_resp, long time_spent, Date updatedAt, String updatedBy, Date createdAt, String createdBy) {
        this.user_id = user_id;
        this.course_id = course_id;
        this.sub_id = sub_id;
        this.test_id = test_id;
        this.user_resp = user_resp;
        this.time_spent = time_spent;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
}
