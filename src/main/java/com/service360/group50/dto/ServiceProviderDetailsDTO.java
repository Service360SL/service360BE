package com.service360.group50.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProviderDetailsDTO{

    private Long userid;
    private String firstname;
    private String lastname;
    private String email;
    private String nic;
    private String address;
    private String phonenumber;
    private String status;
    private String reason;
    private String profilePic;
    private String district;
    private String city;
    private boolean locked;
    private boolean enabled;
    private LocalDate registrationdate;
    private List<ServiceCategoryDTO> serviceCategories;
    private List<ServiceProviderFilesDTO> files;

}
