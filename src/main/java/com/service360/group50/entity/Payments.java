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
        name = "Payments"
)
public class Payments {
    @Id
    @GeneratedValue
    @Column(updatable = false ,name = "paymentId")
    private Long paymentId;

    @Column( columnDefinition = "TEXT", nullable = false)
    private String orderId;

    @Column( columnDefinition = "TEXT", nullable = false)
    private Long senderId;

    @Column( columnDefinition = "TEXT", nullable = true)
    private Long receiverId;

    @Column( columnDefinition = "TEXT", nullable = false)
    private String PaymentName;

    @Column( columnDefinition = "TEXT", nullable = false)
    private String amount;

    @Column( columnDefinition = "TEXT", nullable = false)
    private String paymentDate;

    @Column( columnDefinition = "TEXT", nullable = false)   // Advertiser or Customer or servivise provider  like put all in capital letters  // who pay money
    private String userType;

    @Column( columnDefinition = "TEXT", nullable = false)    // Completed or Pending
    private String paymentStatus;

    @Column( columnDefinition = "TEXT", nullable = false)  // Online or Cash
    private String paymentMethod;


    //    @Column( columnDefinition = "TEXT", nullable = false)
    //    private String statusMsg;

    //    @Column( columnDefinition = "TEXT", nullable = false)
    //    private String statusCode;

    //    @Column( columnDefinition = "TEXT", nullable = false)
    //    private String cardNo;

    @PrePersist
    protected void onCreate()  {
        paymentDate = String.valueOf(LocalDate.now());
    }

}
