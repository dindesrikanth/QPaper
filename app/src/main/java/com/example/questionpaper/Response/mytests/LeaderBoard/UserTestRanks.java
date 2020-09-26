package com.example.questionpaper.Response.mytests.LeaderBoard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserTestRanks {
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("marks")
    @Expose
    private String marks;
    @SerializedName("amountWon")
    @Expose
    private String amountWon;

    public String getUserName() {
        return userName;
    }
    public String getRank() {
        return rank;
    }
    public String getMarks() {
        return marks;
    }
    public String getAmountWon() {
        return amountWon;
    }

}
