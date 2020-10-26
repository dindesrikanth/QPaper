package com.example.questionpaper.Requests.InfoAndSettings;

public class UpdateProfileRequest {
    String  address,city,country,dob,email,gender,  mobileNumber,  name,
            password,  pincode,  preferedExams,  receiveNotifications, state, accountStatus, userId;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setPreferedExams(String preferedExams) {
        this.preferedExams = preferedExams;
    }

    public void setReceiveNotifications(String receiveNotifications) {
        this.receiveNotifications = receiveNotifications;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
