package com.service360.group50.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "trainingsessionregistration"
)

public class TrainingSessionRegistration {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Long registrationid;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private Users serviceprovider;

    @ManyToOne
    @JoinColumn(name = "trainingid", referencedColumnName = "trainingid")
    private TrainingSession trainingsession;

    @Column( columnDefinition = "TEXT")
    private String mobilenumber;

    @Column( columnDefinition = "TEXT")
    private String email;

    @Column( columnDefinition = "TEXT")
    private String paymentstatus;

    @Column( columnDefinition = "DATE")
    private LocalDate registrationdate;
}
