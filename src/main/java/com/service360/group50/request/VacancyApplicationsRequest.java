package com.service360.group50.request;

import java.sql.Timestamp;
import java.util.Date;

public class VacancyApplicationsRequest {
    private String firstname;
    private String lastname;
    private String contactnumber;
    private String emailaddress;
    private String educationqualification;
    private String yearsofexperience;
    private String salaryexpectation;
    private String cvfile;
    private Timestamp dateapplied;
    private Long serviceproviderId;
    private Long vacancyId;

    // Getters for the fields
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getContactnumber() { return contactnumber; }
    public String getEmailaddress() { return emailaddress; }
    public String getEducationqualification() { return educationqualification; }
    public String getYearsofexperience() { return yearsofexperience; }
    public String getSalaryexpectation() { return salaryexpectation; }
    public String getCvfile() { return cvfile; }
    public Timestamp getDateapplied() { return dateapplied; }
    public Long getServiceproviderId() { return serviceproviderId; }
    public Long getVacancyId() { return vacancyId; }
}
