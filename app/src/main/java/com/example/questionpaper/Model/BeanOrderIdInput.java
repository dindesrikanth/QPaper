package com.example.questionpaper.Model;


public class BeanOrderIdInput {

    private long amount;
    private String currency;
    private long payment_capture;

    public BeanOrderIdInput(long amount, String currency, long payment_capture) {
        this.amount = amount;
        this.currency = currency;
        this.payment_capture = payment_capture;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getPayment_capture() {
        return payment_capture;
    }

    public void setPayment_capture(long payment_capture) {
        this.payment_capture = payment_capture;
    }
}
