package com.example.questionpaper.Requests.MyTests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompletedTestsRequest {
    @SerializedName("numberOfMonths")
    @Expose
    private String numberOfMonths;
    @SerializedName("userId")
    @Expose
    private String userId;

    public CompletedTestsRequest(String numberOfMonths, String userId) {
        this.numberOfMonths = numberOfMonths;
        this.userId = userId;
    }

}
