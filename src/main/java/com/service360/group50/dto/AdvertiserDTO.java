package com.service360.group50.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

public class AdvertiserDTO {

    private Long userid;
    private String firstname;
    private String lastname;
    private String email;
    private String nic;
    private String phonenumber;
    private String address;
    private LocalDate registrationdate;
    private String status;
    private String reason;
    private boolean locked;
    private boolean isactive;
    private String profilePic;
    private String shopname;
    private String shopaddress;
    private List<AdvertiserFilesDTO> files;

    public AdvertiserDTO() {
    }

    public AdvertiserDTO(Long userid, String firstname, String lastname, String email, String nic, String phonenumber,
                         String address, LocalDate registrationdate, String status,String reason, boolean locked, boolean isactive,
                         String profilePic, String shopname, String shopaddress, List<AdvertiserFilesDTO> files) {
        this.userid = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.nic = nic;
        this.phonenumber = phonenumber;
        this.address = address;
        this.registrationdate = registrationdate;
        this.status = status;
        this.locked = locked;
        this.isactive = isactive;
        this.profilePic = profilePic;
        this.shopname = shopname;
        this.shopaddress = shopaddress;
        this.files = files;
        this.reason = reason;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getRegistrationdate() {
        return registrationdate;
    }

    public void setRegistrationdate(LocalDate registrationdate) {
        this.registrationdate = registrationdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopaddress() {
        return shopaddress;
    }

    public void setShopaddress(String shopaddress) {
        this.shopaddress = shopaddress;
    }

    public List<AdvertiserFilesDTO> getFiles() {
        return files;
    }

    public void setFiles(List<AdvertiserFilesDTO> files) {
        this.files = files;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
