package com.example.questionpaper.Response.Payments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transactions {
    @SerializedName("transactionDate")
    @Expose
    private String transactionDate ;
    @SerializedName("transactionType")
    @Expose
    private String transactionType ;
    @SerializedName("transactionInd")
    @Expose
    private String transactionInd ;
    @SerializedName("ammount")
    @Expose
    private String ammount ;
    @SerializedName("transactionId")
    @Expose
    private String transactionId ;


    public String getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getTransactionInd() {
        return transactionInd;
    }

    public String getAmmount() {
        return ammount;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
