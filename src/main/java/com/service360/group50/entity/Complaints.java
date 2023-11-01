package com.service360.group50.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "complaints"
)
public class Complaints {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Long complaintid;

    @Column( columnDefinition = "TEXT")
    private String complainttitle;

    @Column( columnDefinition = "TEXT")
    private String complaintdescription;

    @Column( columnDefinition = "TEXT")
    private String complaintstatus;

    @Column( columnDefinition = "TEXT")
    private String reply;

    @Column( columnDefinition = "DATE")
    private LocalDate posteddate;

    @Column(columnDefinition = "BOOLEAN")
    private boolean disabled;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Users users;

    @PrePersist
    protected void onCreate(){
        posteddate=LocalDate.now();
        complaintstatus="Pending";
    }
}
