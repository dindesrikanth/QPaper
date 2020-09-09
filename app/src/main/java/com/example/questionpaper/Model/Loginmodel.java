package com.example.questionpaper.Model;

public class Loginmodel {
    private String user_email_id;

    private String password;
    private String message;
    private String is_enorlled_courses;
    private String is_govt_job;
    private String is_prof_job;
    private String is_both;
private  Long user_id;
private String govt_job;
private  String prof_job;

    public Loginmodel() {
    }

    public String getGovt_job() {
        return govt_job;
    }

    public void setGovt_job(String govt_job) {
        this.govt_job = govt_job;
    }

    public String getProf_job() {
        return prof_job;
    }

    public void setProf_job(String prof_job) {
        this.prof_job = prof_job;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getIs_enorlled_courses() {
        return is_enorlled_courses;
    }

    public void setIs_enorlled_courses(String is_enorlled_courses) {
        this.is_enorlled_courses = is_enorlled_courses;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Loginmodel(String user_email_id, String password) {
        this.user_email_id = user_email_id;
        this.password = password;
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
}
