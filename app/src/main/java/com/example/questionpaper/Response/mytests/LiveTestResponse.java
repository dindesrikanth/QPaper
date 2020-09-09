package com.example.questionpaper.Response.mytests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LiveTestResponse {
    @SerializedName("data")
    @Expose
    List<TestData> data = null;

    public List<TestData> getData() {
        return data;
    }

    public void setData(List<TestData> data) {
        this.data = data;
    }
}
