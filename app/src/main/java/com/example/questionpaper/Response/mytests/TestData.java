package com.example.questionpaper.Response.mytests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TestData {
    @SerializedName("courseName")
    @Expose
    String courseName ;

    @SerializedName("tests")
    @Expose
    ArrayList<Tests> tests ;

    public ArrayList<Tests> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Tests> tests) {
        this.tests = tests;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
