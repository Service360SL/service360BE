package com.service360.group50.dto;

import com.service360.group50.entity.Jobs;
import java.util.ArrayList;
import java.util.List;

public class JobsDTO {
    private List<ImagesDTO> jobimages;
    private Jobs jobs;

    public JobsDTO() {
        this.jobimages = new ArrayList<>();
    }

    public Jobs getJobs() {
        return jobs;
    }

    public void setJobs(Jobs jobs) {
        this.jobs = jobs;
    }

    public List<ImagesDTO> getJobimages() {
        return jobimages;
    }

    public void setJobimages(List<ImagesDTO> jobimages) {
        this.jobimages = jobimages;
    }
}
