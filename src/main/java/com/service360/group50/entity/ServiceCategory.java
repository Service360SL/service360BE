package com.service360.group50.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "servicecategory")

public class ServiceCategory {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Long servicecategoryid;

    @Column( columnDefinition = "TEXT")
    private String serviceCategoryName;

    @Column(columnDefinition = "BYTEA")
    private byte[] categoryImage;

    @OneToMany(mappedBy = "serviceCategory", fetch = FetchType.EAGER)
    private List<Services> services;

    @Override
    public String toString() {
        return "ServiceCategory{" +
                "servicecategoryid=" + servicecategoryid +
                ", serviceCategoryName='" + serviceCategoryName + '\'' +
                ", categoryImage=" + categoryImage +
                '}';
    }

}
