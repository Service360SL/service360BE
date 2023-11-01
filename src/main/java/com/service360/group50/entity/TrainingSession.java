package com.service360.group50.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "trainingsession"
)

public class TrainingSession {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Long trainingid;

    @Column(columnDefinition = "TEXT")
    private String trainingimage;           // Comma-separated image filenames

    @Column( columnDefinition = "TEXT")
    private String trainingtitle;

    @Column( columnDefinition = "TEXT")
    private String trainingdescription;

    @Column( columnDefinition = "DATE")
    private Date trainingdate;

    @Column( columnDefinition = "TIME")
    private Time trainingstarttime;

    @Column( columnDefinition = "TIME")
    private Time trainingendtime;

    @Column( columnDefinition = "TEXT")
    private String traininglocation;

    @Column( columnDefinition = "TEXT")
    private String trainingcost;

    @Column( columnDefinition = "TEXT")
    private String servicename;

    @Column( columnDefinition = "TEXT")
    private String status;

    @Column( columnDefinition = "INT")
    private Integer going;

    @Column( columnDefinition = "INT")
    private Integer interested;

    @Column( columnDefinition = "TEXT")
    private String reason;

    //service provider name
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private Users serviceprovider;

    @Transient
    private List<String> trainingImageUrls; // List of full image URLs (not stored in the database)

    public List<String> getTrainingImageList() {
        return Arrays.asList(trainingimage.split(","));
    }

    public void setTrainingImageList(List<String> imageList) {
        this.trainingimage = String.join(",", imageList);
    }

    public List<String> getTrainingImageUrls() {
        return trainingImageUrls;
    }

    public void setTrainingImageUrls(List<String> trainingImageUrls) {
        this.trainingImageUrls = trainingImageUrls;
    }

    public Integer getInterestedcount() {
        return interested;
    }

    public Integer getGoingcount() {
        return going;
    }

    public void setInterestedcount(Integer interested) {
        this.interested = interested;
    }

    public void setGoingcount(Integer going) {
        this.going = going;
    }
}
