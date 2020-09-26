package com.example.questionpaper.Response.mytests.Completed;

import com.example.questionpaper.Response.mytests.LiveTest.TestData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompletedTestsResponse {
    @SerializedName("data")
    @Expose
    private List<TestData> data = null;

    public List<TestData> getData() {
        return data;
    }
}
