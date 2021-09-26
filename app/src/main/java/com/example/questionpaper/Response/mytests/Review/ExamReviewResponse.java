package com.example.questionpaper.Response.mytests.Review;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExamReviewResponse {
    @SerializedName("isobjectionRequired")
    @Expose
    private boolean isObjectionRequired;

    @SerializedName("examReviews")
    @Expose
    private List<ExamReviews> examReviews;

    public List<ExamReviews> getExamReviewsList() {
        return examReviews;
    }

    public boolean isObjectionRequired() {
        return isObjectionRequired;
    }
}
