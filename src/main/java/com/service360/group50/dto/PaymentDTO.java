package com.service360.group50.dto;

public class PaymentDTO {


    private String orderId;
    private String hash;
    private String amount;


    public PaymentDTO() {
        this.orderId = orderId;
        this.hash = hash;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
