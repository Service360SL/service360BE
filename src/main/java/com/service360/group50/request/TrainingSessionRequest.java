package com.service360.group50.request;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class TrainingSessionRequest {
    // Include fields matching the JSON payload, including serviceproviderId
    private String trainingimage;
    private String trainingtitle;
    private String trainingdescription;
    private Date trainingdate;
    private Time trainingstarttime;
    private String traininglocation;
    private Time trainingendtime;
    private String trainingcost;
    private String servicename;
    private String status;
    private Integer going;
    private Integer interested;
    private Long serviceproviderId;

    // Getters for the fields
    public String getTrainingimage() {
        return trainingimage;
    }

    public String getTrainingtitle() {
        return trainingtitle;
    }

    public String getTrainingdescription() {
        return trainingdescription;
    }

    public Date getTrainingdate() {
        return trainingdate;
    }

    public Time getTrainingstarttime() {
        return trainingstarttime;
    }

    public String getTraininglocation() {
        return traininglocation;
    }

    public Time getTrainingendtime() {
        return trainingendtime;
    }

    public String getTrainingcost() {
        return trainingcost;
    }

    public String getServicename() {
        return servicename;
    }

    public String getStatus() {
        return status;
    }

    public Integer getGoing() {
        return going;
    }

    public Integer getInterested() {
        return interested;
    }

    public Long getServiceproviderId() {
        return serviceproviderId;
    }
}
