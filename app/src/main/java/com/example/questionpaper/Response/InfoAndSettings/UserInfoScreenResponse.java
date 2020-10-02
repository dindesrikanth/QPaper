package com.example.questionpaper.Response.InfoAndSettings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfoScreenResponse {
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("userEmailId")
    @Expose
    private String userEmailId;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("preferedExams")
    @Expose
    private String preferedExams;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("updatedBy")
    @Expose
    private String updatedBy;

    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("updatedDate")
    @Expose
    private String updatedDate;
    @SerializedName("regDate")
    @Expose
    private String regDate;
    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("accountStatus")
    @Expose
    private String accountStatus;
    @SerializedName("receiveNotifications")
    @Expose
    private String receiveNotifications;
    @SerializedName("userWallet")
    @Expose
    private String userWallet;

    @SerializedName("transactionHistory")
    @Expose
    private String transactionHistory;
    @SerializedName("userWinsHistory")
    @Expose
    private String userWinsHistory;

    public String getUserId() {
        return userId;
    }
    public String getUserEmailId() {
        return userEmailId;
    }
    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
    public String getDob() {
        return dob;
    }
    public String getPreferedExams() {
        return preferedExams;
    }
    public String getPincode() {
        return pincode;
    }
    public String getLocation() {
        return location;
    }
    public String getAddress() {
        return address;
    }
    public String getUpdatedBy() {
        return updatedBy;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public String getCreatedDate() {
        return createdDate;
    }
    public String getUpdatedDate() {
        return updatedDate;
    }
    public String getRegDate() {
        return regDate;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public String getCity() {
        return city;
    }
    public String getAccountStatus() {
        return accountStatus;
    }
    public String getReceiveNotifications() {
        return receiveNotifications;
    }
    public String getUserWallet() {
        return userWallet;
    }
    public String getTransactionHistory() {
        return transactionHistory;
    }
    public String getUserWinsHistory() {
        return userWinsHistory;
    }
}
