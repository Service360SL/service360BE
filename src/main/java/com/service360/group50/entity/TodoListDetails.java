package com.service360.group50.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "todolistdetails"
)
public class TodoListDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todolistdetailsid")
    private Long todolistdetailsid;

    @OneToOne
    @JoinColumn(name = "todolistid")
    private TodoList todolist;

    @Column( columnDefinition = "TEXT")
    private String task;

    @Column(name = "workedhours")
    private double workedHours;

    @Column(columnDefinition = "BOOLEAN")
    private boolean completed;

    @Column(columnDefinition = "REAL")
    private float amount;

    @Column(columnDefinition = "BOOLEAN")
    private boolean customercompleted;




}