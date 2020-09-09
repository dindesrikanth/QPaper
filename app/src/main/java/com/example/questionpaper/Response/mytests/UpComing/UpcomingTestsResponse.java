package com.example.questionpaper.Response.mytests.UpComing;

import com.example.questionpaper.Response.mytests.UpComing.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpcomingTestsResponse {
    @SerializedName("data")
    @Expose
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }
}
