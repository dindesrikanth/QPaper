package com.example.questionpaper.Response.Payments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;
import java.util.List;

public class ViewToppersListResponse{
    @SerializedName("topTestRanks")
    @Expose
    private List<TopTestRanks> topTestRanks;

    public List<TopTestRanks> getTopTestRanks() {
        return topTestRanks;
    }

}
