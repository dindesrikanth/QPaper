package com.example.questionpaper.Requests.MyTests.review;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExamReviewRequest {
    @SerializedName("testId")
    @Expose
    private String testId;
    @SerializedName("userId")
    @Expose
    private String userId;

    public ExamReviewRequest(String testId, String userId) {
        this.testId = testId;
        this.userId = userId;
    }

}
