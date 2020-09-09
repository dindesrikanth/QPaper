package com.example.questionpaper.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UserCourses {
    @SerializedName("user_id")
    private Long user_id;
    @SerializedName("course_id")
    private Long course_id;
    @SerializedName("user_main_course_id")
    private String user_main_course_id;
    @SerializedName("updatedAt")
    private Date updatedAt = new Date();
    @SerializedName("updatedBy")
    private String updatedBy;
    @SerializedName("createdAt")
    private Date createdAt = new Date();
    @SerializedName("createdBy")
    private String createdBy;

    public UserCourses(Long user_id, Long course_id, String user_main_course_id, Date updatedAt, String updatedBy, Date createdAt, String createdBy) {
        this.user_id = user_id;
        this.course_id = course_id;
        this.user_main_course_id = user_main_course_id;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public UserCourses() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getUser_main_course_id() {
        return user_main_course_id;
    }

    public void setUser_main_course_id(String user_main_course_id) {
        this.user_main_course_id = user_main_course_id;
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
}
