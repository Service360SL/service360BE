package com.service360.group50.request;

public class PayhereCallbackRequest {
    private String merchantId;
    private String orderId;

    private String payherePaymentId;

    private String payhereAmount;
    private String payhereCurrency;
    private String statusCode;
    private String md5sig;
    private String card_no;

    private String statusMsg;


    public PayhereCallbackRequest(String merchantId, String orderId, String payhereAmount, String payhereCurrency, String statusCode, String md5sig, String card_no, String statusMsg, String payherePaymentId) {
        this.merchantId = merchantId;
        this.orderId = orderId;
        this.payhereAmount = payhereAmount;
        this.payhereCurrency = payhereCurrency;
        this.statusCode = statusCode;
        this.md5sig = md5sig;
        this.card_no = card_no;
        this.statusMsg = statusMsg;
        this.payherePaymentId = payherePaymentId;
    }

    public String getPayherePaymentId() {
        return payherePaymentId;
    }

    public void setPayherePaymentId(String payherePaymentId) {
        this.payherePaymentId = payherePaymentId;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayhereAmount() {
        return payhereAmount;
    }

    public void setPayhereAmount(String payhereAmount) {
        this.payhereAmount = payhereAmount;
    }

    public String getPayhereCurrency() {
        return payhereCurrency;
    }

    public void setPayhereCurrency(String payhereCurrency) {
        this.payhereCurrency = payhereCurrency;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMd5sig() {
        return md5sig;
    }

    public void setMd5sig(String md5sig) {
        this.md5sig = md5sig;
    }
}
