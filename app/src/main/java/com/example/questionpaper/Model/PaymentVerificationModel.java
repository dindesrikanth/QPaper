package com.example.questionpaper.Model;


public class PaymentVerificationModel {
    private long amountPaid;
    private String status;
    private long currentBalance;

    public PaymentVerificationModel(long amountPaid, String status, long currentBalance) {
        this.amountPaid = amountPaid;
        this.status = status;
        this.currentBalance = currentBalance;
    }

    public long getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(long amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(long currentBalance) {
        this.currentBalance = currentBalance;
    }
}
