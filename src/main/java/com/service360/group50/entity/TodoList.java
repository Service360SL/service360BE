package com.service360.group50.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "todolist"
)

public class TodoList {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Long todolistid;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "userid")
    private Users customer;

    @ManyToOne
    @JoinColumn(name = "service_provider_id", referencedColumnName = "userid")
    private Users serviceprovider;

    //job id
    @ManyToOne
    @JoinColumn(name = "jobid")
    private Jobs job;

    @Column( columnDefinition = "TEXT")
    private String status;

    @PrePersist
    public void prePersist() {
        this.status = "ongoing";
    }

}