package com.service360.group50.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "subscription"
)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Date endDate;
    // status
    @Column( columnDefinition = "TEXT")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column()
    private Date createdAt;

    // planid
    @OneToOne
    @JoinColumn(name = "planid")
    private SubscriptionPlan subscriptionPlan;

    // userid
    @OneToOne
    @JoinColumn(name = "userid")
    private Users users;


    @PrePersist
    public void setDefaultDates() {
        startDate = new Date(); // Set the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, 1); // Add one month to the current date
        endDate = calendar.getTime();
    }

}
