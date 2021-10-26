package com.example.questionpaper.Requests.MyTests.review;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObjectionsData {
    @SerializedName("issueId")
    @Expose
    private int issueId;
    @SerializedName("objectionDescription")
    @Expose
    private String objectionDescription;
    @SerializedName("objectionDetailId")
    @Expose
    private int objectionDetailId;
    @SerializedName("questionLocalId")
    @Expose
    private long questionLocalId;
    @SerializedName("testId")
    @Expose
    private long testId;
    @SerializedName("userId")
    @Expose
    private String userId;


    public ObjectionsData(int issueId, String objectionDescription, int objectionDetailId,
                          long questionLocalId, long testId, String userId) {

        this.issueId= issueId;
        this.objectionDescription = objectionDescription;
        this.objectionDetailId = objectionDetailId;
        this.questionLocalId = questionLocalId;
        this.testId = testId;
        this.userId = userId;
    }

    public int getIssueId() {
        return issueId;
    }

    public String getObjectionDescription() {
        return objectionDescription;
    }

    public int getObjectionDetailId() {
        return objectionDetailId;
    }

    public long getQuestionLocalId() {
        return questionLocalId;
    }

    public long getTestId() {
        return testId;
    }

    public String getUserId() {
        return userId;
    }
}
