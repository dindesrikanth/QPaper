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

    public String getCourseName() {
        return courseName;
    }
    public List<Tests> getTests() {
        return tests;
    }
}
