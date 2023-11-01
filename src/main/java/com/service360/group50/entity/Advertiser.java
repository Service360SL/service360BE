package com.service360.group50.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "advertiser"
)
public class Advertiser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long advertiserid;

    @Column( columnDefinition = "TEXT")
    private String shopname;

    @Column(columnDefinition = "TEXT")
    private String shopaddress;

    @OneToOne
    @JoinColumn(name = "userid")
    private Users users;

}