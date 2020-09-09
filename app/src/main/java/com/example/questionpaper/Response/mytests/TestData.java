package com.example.questionpaper.Response.mytests;

import java.util.ArrayList;

public class TestData {
    String courseName ;
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
