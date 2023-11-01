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
@Table(
        name = "Ads"
)
public class Ads {
    @Id
    @GeneratedValue()
    @Column(updatable = false ,name = "adsId")
    private Long adsId;

    @Column( columnDefinition = "TEXT")
    private String adsName;

    @Column( columnDefinition = "TEXT", nullable = true)
    private String status;

    @Column( columnDefinition = "TEXT", nullable = true)
    private String verificationStatus;

    @Column( columnDefinition = "TEXT", nullable = false)
    private String adsImages;

    @Column( columnDefinition = "TEXT", nullable = false)
    private String category;

    @Column( columnDefinition = "TEXT", nullable = false)
    private String price;

    @Column( columnDefinition = "TEXT", nullable = true)
    private String warrantyMonths;

    @Column( columnDefinition = "TEXT", nullable = true)
    private String description;

    @Column( columnDefinition = "TEXT", nullable = false)
    private String area;

    @Column( columnDefinition = "TEXT", nullable = false)
    private String delivery;

    @Column( columnDefinition = "TEXT", nullable = true)
    private String verifyImages;

    @Column( columnDefinition = "TEXT", nullable = true)
    private String Reason;

    @Column( nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Users user;

    @PrePersist
    protected void onCreate()  {
        status = "Active";
        verificationStatus = "Pending";
        date = LocalDate.now();
    }

}
