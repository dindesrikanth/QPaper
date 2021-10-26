package com.example.questionpaper.Response.mytests.Review;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObjectionScreenResponse {
    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }
}
