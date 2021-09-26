package com.example.questionpaper.Response.Payments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowBalanceResponse {
    @SerializedName("walletBalance")
    @Expose
    private String walletBalance;
    @SerializedName("winsBalance")
    @Expose
    private String winsBalance;

    @SerializedName("cashBonus")
    @Expose
    private String cashBonus;

    public String getWalletBalance() {
        return walletBalance;
    }

    public String getWinsBalance() {
        return winsBalance;
    }

    public String getCashBonus() {
        return cashBonus;
    }
}
