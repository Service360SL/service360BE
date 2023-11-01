package com.service360.group50.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cserviceproviderdetails")

public class CServiceProviderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use GenerationType.IDENTITY for auto-increment
    @Column(name = "serviceproviderid")
    private int serviceproviderid;

    @Column(name = "name")
    private String serviceprovidername;

    @Column(name = "img")
    private String img;

    @Column(name = "service")
    private String service;

    @Column(name = "membership_date")
    private LocalDate membershipdate;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "contact")
    private long contact;

//    @Column(name = "rating")
//    private double rating; // New field for rating
//
//    @Column(name = "review")
//    private String review; // New field for review


    @Override
    public String toString() {
        return "CServiceProviderDetails{" +
                "serviceproviderid=" + serviceproviderid +
                ", serviceprovidername='" + serviceprovidername + '\'' +
                ", img='" + img + '\'' +
                ", service='" + service + '\'' +
                ", membershipdate=" + membershipdate +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", contact=" + contact +
//                ", rating=" + rating +
//                ", review='" + review + '\'' +
                '}';
    }
}

