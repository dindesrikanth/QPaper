package com.example.questionpaper.Response.Payments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Records {
    @SerializedName("date")
    @Expose
    private String date ;

    @SerializedName("transactions")
    @Expose
    private List<Transactions> transactions ;

    public String getDate() {
        return date;
    }
    public List<Transactions> getTransactions() {
        return transactions;
    }
}
