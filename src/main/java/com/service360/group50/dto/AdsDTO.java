package com.service360.group50.dto;

import com.service360.group50.entity.Ads;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdsDTO {

    private Long id;
    private String adsName;
    private String category;
    private String price;
    private String warrantyMonths;
    private String description;
    private String area;
    private  String status;
    private String verificationStatus;
    private String delivery;

    private String reason;
    private String role;
    private Long userId;
    private String firstName;
    private String lastName;
    private String shopName;
    private String shopAddress;
    private String shopPhone;

    private String plan;

    private List<byte[]> adsImages;

    private String profileImage;

    private LocalDate date;

      public AdsDTO() {
            this.id = id;
            this.adsName = adsName;
            this.category = category;
            this.price = price;
            this.warrantyMonths = warrantyMonths;
            this.description = description;
            this.area = area;
            this.delivery = delivery;
            this.reason= reason;
            this.role = role;
            this.userId = userId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.shopName = shopName;
            this.shopAddress = shopAddress;
            this.shopPhone = shopPhone;
            this.status = status;
            this.verificationStatus = verificationStatus;
            this.profileImage = profileImage;
            this.adsImages = new ArrayList<>();
            this.plan = plan;
            this.date = date;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getAdsName() {
        return adsName;
    }

    public void setAdsName(String adsName) {
        this.adsName = adsName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(String warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public List<byte[]> getAdsImages() {
        return adsImages;
    }

    public void setAdsImages(List<byte[]> adsImages) {
        this.adsImages = adsImages;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
