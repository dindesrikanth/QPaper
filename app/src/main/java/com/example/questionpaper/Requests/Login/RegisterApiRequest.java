package com.example.questionpaper.Requests.Login;

public class RegisterApiRequest {
    String name,email,mobileNumber,password,examPreference;

    public RegisterApiRequest(String name,String email,String mobileNumber, String password,String examPreference) {
        this.name = name;
        this.email = email;
        this.mobileNumber=mobileNumber;
        this.password = password;
        this.examPreference= examPreference;
    }
}
