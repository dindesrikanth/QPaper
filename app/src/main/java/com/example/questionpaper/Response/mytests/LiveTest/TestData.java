package com.example.questionpaper.Response.mytests.LiveTest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TestData {
    @SerializedName("courseName")
    @Expose
    private String courseName ;
    @SerializedName("tests")
    @Expose
    private  ArrayList<Tests> tests ;

    public ArrayList<Tests> getTests() {
        return tests;
    }
    public String getCourseName() {
        return courseName;
    }
}
