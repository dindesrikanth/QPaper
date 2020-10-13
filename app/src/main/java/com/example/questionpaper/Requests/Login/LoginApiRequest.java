package com.example.questionpaper.Requests.Login;

public class LoginApiRequest {
    String email;
    String password;

    public LoginApiRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
