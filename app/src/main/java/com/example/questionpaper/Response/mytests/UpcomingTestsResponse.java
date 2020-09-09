package com.example.questionpaper.Response.mytests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpcomingTestsResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message_type")
    @Expose
    private String messageType;
    @SerializedName("message_to")
    @Expose
    private String messageTo;

    public String getStatus() {
        return status;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getMessageTo() {
        return messageTo;
    }
}
