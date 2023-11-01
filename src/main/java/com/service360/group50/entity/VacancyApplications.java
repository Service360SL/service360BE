package com.service360.group50.entity;

import com.service360.group50.compositekeys.VacancyApplicationsId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "vacancyapplications"
)

@IdClass(VacancyApplicationsId.class)
public class VacancyApplications {
    @Column( columnDefinition = "TEXT")
    private String firstname;

    @Column( columnDefinition = "TEXT")
    private String lastname;

    @Column( columnDefinition = "TEXT")
    private String contactnumber;

    @Column( columnDefinition = "TEXT")
    private String emailaddress;

    @Column( columnDefinition = "TEXT")
    private String educationqualification;

    @Column( columnDefinition = "TEXT")
    private String yearsofexperience;

    @Column( columnDefinition = "TEXT")
    private String salaryexpectation;

    @Column( columnDefinition = "TEXT")
    private String cvfile;

    @Column( columnDefinition = "TIMESTAMP")
    private Timestamp dateapplied;

    //Applied Service Provider IDs
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    private Users serviceproviders;

    //Vacancy IDs
    @Id
    @ManyToOne
    @JoinColumn(name = "vacancyid")
    private Vacancies vacancies;
}
