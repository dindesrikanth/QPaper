package com.example.questionpaper.Requests.MyTests.review;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ObjectionsRequest {
    @SerializedName("objectionRequests")
    @Expose
    private List<ObjectionsData> objectionRequests;

    public ObjectionsRequest(List<ObjectionsData> objectionRequests) {
        this.objectionRequests = objectionRequests;
    }
}
