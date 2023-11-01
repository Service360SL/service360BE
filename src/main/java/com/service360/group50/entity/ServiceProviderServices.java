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
        name = "serviceproviderservices"
)

public class ServiceProviderServices {
    @SequenceGenerator(
            name = "serviceproviderservices_sequence",
            sequenceName = "serviceproviderservices_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "serviceproviderservices_sequence")
    @Column(updatable = false)
    private Long serviceProviderServicesId;

    @PrePersist
    protected void onCreate() {
        status = "active";
    }

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "serviceid")
    private Services services;

    @ManyToOne
    @JoinColumn(name = "servicecategoryid")
    private ServiceCategory serviceCategory;

}
