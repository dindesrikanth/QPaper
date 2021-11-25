package com.example.questionpaper.Requests.Login;

public class ForgotPasswordRequest {
    String name,emaiId,mobileNumber,password,examPreference;

    public ForgotPasswordRequest(String name,String emaiId,String mobileNumber, String password,String examPreference) {
        this.name = name;
        this.emaiId = emaiId;
        this.mobileNumber=mobileNumber;
        this.password = password;
        this.examPreference= examPreference;
    }
}
