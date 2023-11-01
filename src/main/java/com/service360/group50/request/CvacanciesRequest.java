package com.service360.group50.request;


import com.service360.group50.entity.Users;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

public class CvacanciesRequest {
    private String vacancytitle;

    private LocalDate duedate;

    private LocalDate posteddate;

    private String servicename;

    private String vacancydescription;

    private String vacancylocation;

    private String vacancytype;

    private String qualifications;

    private String responsibilities;

    private boolean disabled;

    private Long customer;

    public String getVacancytitle() {
        return vacancytitle;
    }

    public LocalDate getDuedate() {
        return duedate;
    }

    public LocalDate getPosteddate() {
        return posteddate;
    }

    public String getServicename() {
        return servicename;
    }

    public String getVacancydescription() {
        return vacancydescription;
    }

    public String getVacancylocation() {
        return vacancylocation;
    }

    public String getVacancytype() {
        return vacancytype;
    }

    public String getQualifications() {
        return qualifications;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public Long getCustomer() {
        return customer;
    }
}
