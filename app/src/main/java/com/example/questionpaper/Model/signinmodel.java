package com.example.questionpaper.Model;

import java.util.Date;
import java.util.List;

public class signinmodel {
    private String user_email_id;
    private String password;
    private String tName;
    private String is_govt_job;
    private String is_prof_job;
    private String is_both;
    private long user_id;

    public signinmodel(String user_email_id, String password, String tName, String is_govt_job, String is_prof_job, String is_both) {
        this.user_email_id = user_email_id;
        this.password = password;
        this.tName = tName;
        this.is_govt_job = is_govt_job;
        this.is_prof_job = is_prof_job;
        this.is_both = is_both;
    }

    public String getUser_email_id() {
        return user_email_id;
    }

    public void setUser_email_id(String user_email_id) {
        this.user_email_id = user_email_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getIs_govt_job() {
        return is_govt_job;
    }

    public void setIs_govt_job(String is_govt_job) {
        this.is_govt_job = is_govt_job;
    }

    public String getIs_prof_job() {
        return is_prof_job;
    }

    public void setIs_prof_job(String is_prof_job) {
        this.is_prof_job = is_prof_job;
    }

    public String getIs_both() {
        return is_both;
    }

    public void setIs_both(String is_both) {
        this.is_both = is_both;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
