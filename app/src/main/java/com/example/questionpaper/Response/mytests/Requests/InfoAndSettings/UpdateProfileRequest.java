package com.example.questionpaper.Response.mytests.Requests.InfoAndSettings;

public class UpdateProfileRequest {
    String  address,city,country,dob,email,gender,  mobileNumber,  name,
            password,  pincode,  preferedExams,  receiveNotifications, state,  userId;

    public UpdateProfileRequest(String address, String city,
                                String country, String dob,
                                String email, String gender,
                                String mobileNumber, String name,
                                String password, String pincode,
                                String preferedExams, String receiveNotifications,
                                String state, String userId) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.name = name;
        this.password = password;
        this.pincode = pincode;
        this.preferedExams = preferedExams;
        this.receiveNotifications = receiveNotifications;
        this.state = state;
        this.userId = userId;
    }
}
