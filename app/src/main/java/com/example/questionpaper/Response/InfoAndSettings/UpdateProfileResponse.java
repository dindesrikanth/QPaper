package com.example.questionpaper.Response.InfoAndSettings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProfileResponse {
    @SerializedName("userId")
    @Expose
    private String userId;
}
