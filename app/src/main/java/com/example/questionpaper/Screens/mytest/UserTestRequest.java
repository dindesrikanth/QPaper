package com.example.questionpaper.Screens.mytest;

public class UserTestRequest {
    String typeOfTests;
    String userId;

    public UserTestRequest(String typeOfTests, String userId) {
        this.typeOfTests = typeOfTests;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
