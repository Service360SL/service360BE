package com.service360.group50.request;

import com.service360.group50.entity.Users;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class CjobsRequest {
    private String jobtitle;

    private LocalDate duedate;

    private LocalDate posteddate;

    private String servicename;

    private String jobdescription;

    private String joblocation;

    private String images;

    private String paymentstatus;

    private String isquotation;

    private String quotationpdf;

    private Boolean disabled;

    private Long customer;

    public String getJobtitle() {
        return jobtitle;
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

    public String getJobdescription() {
        return jobdescription;
    }

    public String getJoblocation() {
        return joblocation;
    }

    public String getImages() {
        return images;
    }

    public String getPaymentstatus() {
        return paymentstatus;
    }

    public String getIsquotation() {
        return isquotation;
    }

    public String getQuotationpdf() {
        return quotationpdf;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public Long getCustomer() {
        return customer;
    }
}






