package com.service360.group50.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "jobdetails"
)

public class JobDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long jobdetailsid;

    @OneToOne
    @JoinColumn(name = "jobid")
    private Jobs jobs;

    @Column( columnDefinition = "DATE")
    private LocalDate jobdate;

    @Column( columnDefinition = "Time")
    private Time jobstarttime;

    @Column( columnDefinition = "Time")
    private Time jobendtime;

}