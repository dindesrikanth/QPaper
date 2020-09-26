package com.example.questionpaper.Response.mytests.DetailedAnalysis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailedAnalysisResponse {
    @SerializedName("amountWon")
    @Expose
    private String amountWon;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("detailedAnalysis")
    @Expose
    private List<DetailedAnalysis> detailedAnalysis;

    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("testName")
    @Expose
    private String testName;


    public String getAmountWon() {
        return amountWon;
    }
    public String getRank() {
        return rank;
    }
    public List<DetailedAnalysis> getDetailedAnalysis() {
        return detailedAnalysis;
    }
    public String getScore() {
        return score;
    }
    public String getTestName() {
        return testName;
    }
}
