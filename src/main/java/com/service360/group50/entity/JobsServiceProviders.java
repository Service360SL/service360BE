package com.service360.group50.entity;

import com.service360.group50.compositekeys.JobsServiceProvidersId;
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
        name = "jobsserviceproviders"
)

@IdClass(JobsServiceProvidersId.class)
public class JobsServiceProviders {
    @Column( columnDefinition = "TEXT")
    private String jobstatus;

    //Applied Service Provider IDs
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    private Users serviceproviders;

    //Job IDs
    @Id
    @ManyToOne
    @JoinColumn(name = "jobid")
    private Jobs jobs;

    // Setter method for jobstatus
    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }
}
