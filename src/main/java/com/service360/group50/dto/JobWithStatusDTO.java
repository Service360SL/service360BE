package com.service360.group50.dto;
import com.service360.group50.entity.Jobs;

public class JobWithStatusDTO{
    private Jobs job;
    private String jobStatus;

    public JobWithStatusDTO(Jobs job, String jobStatus) {
        this.job = job;
        this.jobStatus = jobStatus;
    }

    public Jobs getJob() {
        return job;
    }

    public void setJob(Jobs job) {
        this.job = job;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
}
