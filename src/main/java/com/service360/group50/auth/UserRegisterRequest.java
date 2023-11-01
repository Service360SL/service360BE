package com.service360.group50.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phonenumber;
    private String address;
    private String nic;
    private String district;
    private String city;
    private String shopname;
    private String shopaddress;
    private List<MultipartFile> files;
    private String[] services;
    private String[] categories;
}