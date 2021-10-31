package com.example.questionpaper.Response.Payments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionHistoryResponse {
    @SerializedName("records")
    @Expose
    private List<Records> records;

    public List<Records> getRecords() {
        return records;
    }
}
