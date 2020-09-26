package com.example.questionpaper.Response.mytests.LeaderBoard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaderBoardResponse {
    @SerializedName("userTestRanks")
    @Expose
    private List<UserTestRanks> userTestRanks;

    public List<UserTestRanks> getUserTestRanks() {
        return userTestRanks;
    }

}
