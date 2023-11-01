package com.service360.group50.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "contact"
)
public class Contact {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Long contactid;

    @Column( columnDefinition = "TEXT")
    private String fullname;

    @Column( columnDefinition = "TEXT")
    private String email;

    @Column( columnDefinition = "TEXT")
    private String message;

    @Column( columnDefinition = "TEXT")
    private String phonenumber;

    @Column(nullable = false)
    private LocalDate date ;

    @Column(columnDefinition = "TEXT")
    private String status;

    @PrePersist
    protected void onCreate() {
        date = LocalDate.now ();
        status = "Pending";
    }

}
