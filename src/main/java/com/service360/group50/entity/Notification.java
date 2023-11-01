package com.service360.group50.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "notification"
)
public class Notification {
    @Id
    @GeneratedValue()
    @Column(updatable = false ,name = "id")
    private Long id;

    @Column( columnDefinition = "TEXT" , nullable = false)
    private String title;

    @Column( columnDefinition = "TEXT" , nullable = false)
    private String message;

    @Column( columnDefinition = "TEXT" , nullable = false)
    private String senderName;

    // Always set to UNREAD
    @Column( columnDefinition = "TEXT")
    private String status;

    // front-end button
    @Column( columnDefinition = "TEXT", nullable = true)
    private String FEButton1;

    @Column( columnDefinition = "TEXT" , nullable = true)
    private String FEButton1Link;

    //back-end button
    @Column( columnDefinition = "TEXT" , nullable = true)
    private String BEButton1;

    @Column( columnDefinition = "TEXT", nullable = true )
    private String BEButton1Link;

    @Column( columnDefinition = "TEXT", nullable = true)
    private String BEButton2;

    @Column( columnDefinition = "TEXT", nullable = true)
    private String BEButton2Link;

    // createAt
    @Temporal(TemporalType.TIMESTAMP)
    @Column()
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Users users;


}
