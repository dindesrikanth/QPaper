package com.example.questionpaper.Model;


public class OrderIdModel {
    private String orderId;
    private String keyId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public OrderIdModel(String orderId, String keyId) {
        this.orderId = orderId;
        this.keyId = keyId;
    }
}
