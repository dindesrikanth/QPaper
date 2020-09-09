package com.example.questionpaper.Response.mytests.UpComing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Extra {
    @SerializedName("hour")
    @Expose
    private String hour;
    @SerializedName("minute")
    @Expose
    private String minute;
    @SerializedName("nano")
    @Expose
    private String nano;
    @SerializedName("second")
    @Expose
    private String second;

    public String getHour() {
        return hour;
    }
    public String getMinute() {
        return minute;
    }
    public String getNano() {
        return nano;
    }
    public String getSecond() {
        return second;
    }
}
