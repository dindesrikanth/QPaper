package com.example.questionpaper.Response.mytests.UpComing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("courseName")
    @Expose
    private String courseName;
    @SerializedName("tests")
    @Expose
    private List<Tests> tests;
    private boolean showDownArrow=false;

    public boolean getDownArrow() {
        return showDownArrow;
    }
    public void setDownArrow(boolean showDownArrow) {
        this.showDownArrow = showDownArrow;
    }

    public String getCourseName() {
        return courseName;
    }
    public List<Tests> getTests() {
        return tests;
    }
}
