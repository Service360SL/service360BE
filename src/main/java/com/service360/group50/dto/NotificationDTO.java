package com.service360.group50.dto;

public class NotificationDTO {

    private Long id;
    private String title;
    private String message;
    private String status;

    private String senderName;

    private String FEButton1;
    private String FEButton1Link;

    private String BEButton1;
    private String  BEButton1Link;

    private String BEButton2;
    private String BEButton2Link;

    private String createdAt;
    private Long userid;

    public NotificationDTO() {
        this.id=id;
        this.title=title;
        this.message=message;
        this.status=status;
        this.FEButton1=FEButton1;
        this.FEButton1Link=FEButton1Link;
        this.BEButton1=BEButton1;
        this.BEButton1Link=BEButton1Link;
        this.BEButton2=BEButton2;
        this.BEButton2Link=BEButton2Link;
        this.userid=userid;
        this.createdAt=createdAt;
        this.senderName=senderName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getFEButton1() {
        return FEButton1;
    }

    public void setFEButton1(String FEButton1) {
        this.FEButton1 = FEButton1;
    }

    public String getFEButton1Link() {
        return FEButton1Link;
    }

    public void setFEButton1Link(String FEButton1Link) {
        this.FEButton1Link = FEButton1Link;
    }

    public String getBEButton1() {
        return BEButton1;
    }

    public void setBEButton1(String BEButton1) {
        this.BEButton1 = BEButton1;
    }

    public String getBEButton1Link() {
        return BEButton1Link;
    }

    public void setBEButton1Link(String BEButton1Link) {
        this.BEButton1Link = BEButton1Link;
    }

    public String getBEButton2() {
        return BEButton2;
    }

    public void setBEButton2(String BEButton2) {
        this.BEButton2 = BEButton2;
    }

    public String getBEButton2Link() {
        return BEButton2Link;
    }

    public void setBEButton2Link(String BEButton2Link) {
        this.BEButton2Link = BEButton2Link;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
