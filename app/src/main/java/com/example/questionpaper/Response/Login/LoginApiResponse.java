package com.example.questionpaper.Response.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginApiResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("errorMsg")
    @Expose
    private String errorMsg;

    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status;
    }
    public String getUserId() {
        return userId;
    }
    public String getErrorMsg() {
        return errorMsg;
    }

    public String getMessage() {
        return message;
    }
}
