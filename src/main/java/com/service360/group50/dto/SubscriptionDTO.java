package com.service360.group50.dto;

public class SubscriptionDTO {
    private Long id;
    private String startDate;
    private String endDate;

    private String createdAt;

    private String status;
    private String planName;
    private String planDescription;
    private String planPrice;
    private Long userId;

    public SubscriptionDTO() {
        this.id=id;
        this.endDate=endDate;
        this.startDate=startDate;
        this.createdAt=createdAt;
        this.status=status;
        this.planName=planName;
        this.planDescription=planDescription;
        this.planPrice=planPrice;
        this.userId=userId;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanDescription() {
        return planDescription;
    }

    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }

    public String getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(String planPrice) {
        this.planPrice = planPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
