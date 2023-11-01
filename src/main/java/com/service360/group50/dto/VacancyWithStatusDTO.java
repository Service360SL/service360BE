package com.service360.group50.dto;
import com.service360.group50.entity.Vacancies;

public class VacancyWithStatusDTO {
    private Vacancies vacancy;
    private String vacancyStatus;

    public VacancyWithStatusDTO(Vacancies vacancy, String vacancyStatus) {
        this.vacancy = vacancy;
        this.vacancyStatus = vacancyStatus;
    }

    public Vacancies getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancies vacancy) {
        this.vacancy = vacancy;
    }

    public String getVacancyStatus() {
        return vacancyStatus;
    }

    public void setVacancyStatus(String vacancyStatus) {
        this.vacancyStatus = vacancyStatus;
    }
}
