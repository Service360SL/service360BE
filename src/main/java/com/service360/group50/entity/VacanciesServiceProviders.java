package com.service360.group50.entity;

import com.service360.group50.compositekeys.VacanciesServiceProvidersId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "vacanciesserviceproviders"
)

@IdClass(VacanciesServiceProvidersId.class)
public class VacanciesServiceProviders {
    @Column( columnDefinition = "TEXT")
    private String vacancystatus;

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

    // Setter method for vacancystatus
    public void setVacancystatus(String vacancystatus) {
        this.vacancystatus = vacancystatus;
    }
}
