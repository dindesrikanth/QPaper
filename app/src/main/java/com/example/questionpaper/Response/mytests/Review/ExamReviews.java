package com.example.questionpaper.Response.mytests.Review;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExamReviews {
    @SerializedName("optionA")
    @Expose
    private String optionA;
    @SerializedName("optionB")
    @Expose
    private String optionB;
    @SerializedName("optionC")
    @Expose
    private String optionC;
    @SerializedName("optionD")
    @Expose
    private String optionD;
    @SerializedName("optionE")
    @Expose
    private String optionE;
    @SerializedName("questionDetails")
    @Expose
    private String questionDetails;
    @SerializedName("questionId")
    @Expose
    private String questionId;
    @SerializedName("questionLocalId")
    @Expose
    private String questionLocalId;
    @SerializedName("rightAns")
    @Expose
    private String rightAns;
    @SerializedName("userAns")
    @Expose
    private String userAns;

    @SerializedName("questionExplanation")
    @Expose
    private String questionExplanation;

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getOptionE() {
        return optionE;
    }

    public String getQuestionDetails() {
        return questionDetails;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getQuestionLocalId() {
        return questionLocalId;
    }

    public String getRightAns() {
        return rightAns;
    }

    public String getUserAns() {
        return userAns;
    }

    public String getQuestionExplanation() {
        return questionExplanation;
    }
}
