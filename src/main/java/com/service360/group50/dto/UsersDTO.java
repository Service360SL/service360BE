package com.service360.group50.dto;

import java.time.LocalDate;

public class UsersDTO {
    private Long userid;
    private String firstname;
    private String lastname;
    private String email;
    private String nic;
    private String phonenumber;
    private String address;
    private LocalDate registrationdate;
    private String password;
    private String role;
    private String status;
    private String profilePic;

    public UsersDTO() {
    }

    public UsersDTO(Long userid, String firstname, String lastname, String email, String nic, String phonenumber,
                    String address, LocalDate registrationdate, String password, String role, String status, String profilePic) {
        this.userid = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.nic = nic;
        this.phonenumber = phonenumber;
        this.address = address;
        this.registrationdate = registrationdate;
        this.password = password;
        this.role = role;
        this.status = status;
        this.profilePic = profilePic;
    }

    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return this.nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getRegistrationdate() {
        return this.registrationdate;
    }

    public void setRegistrationdate(LocalDate registrationdate) {
        this.registrationdate = registrationdate;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole ( String role ) {
        this.role = role;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus ( String status ) {
        this.status = status;
    }

    public String getProfilePic () {
        return profilePic;
    }

    public void setProfilePic ( String profilePic ) {
        this.profilePic = profilePic;
    }
}
