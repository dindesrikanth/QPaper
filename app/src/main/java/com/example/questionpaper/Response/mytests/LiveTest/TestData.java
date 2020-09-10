package com.example.questionpaper.Response.mytests.LiveTest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestData {
    @SerializedName("courseName")
    @Expose
    private String courseName ;
    @SerializedName("tests")
    @Expose
    private List<Tests> tests ;

    public List<Tests> getTests() {
        return tests;
    }
    public String getCourseName() {
        return courseName;
    }
}
