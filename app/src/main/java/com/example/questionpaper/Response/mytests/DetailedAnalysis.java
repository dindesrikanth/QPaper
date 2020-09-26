package com.example.questionpaper.Response.mytests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailedAnalysis {
    @SerializedName("title")
    @Expose
    private String title ;

    @SerializedName("value")
    @Expose
    private String value ;

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }
}
