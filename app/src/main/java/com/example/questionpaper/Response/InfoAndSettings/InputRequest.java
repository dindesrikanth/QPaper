package com.example.questionpaper.Response.InfoAndSettings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InputRequest {
    @SerializedName("userId")
    @Expose
    private String userId;

    public InputRequest(String userId) {
        this.userId = userId;
    }
}
