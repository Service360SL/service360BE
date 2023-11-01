package com.service360.group50.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "ForumAnswer"
)
public class ForumAnswer {
    @Id
    @GeneratedValue()
    @Column(updatable = false)
    private Long AnsId;

    @Column( columnDefinition = "TEXT")
    private String AnsTitle;

    @Column( columnDefinition = "TEXT")
    private String AnsImages;

    @Column( columnDefinition = "TEXT")
    private String VideoName;

    @Column( columnDefinition = "TEXT")
    private String VideoLink;

    @Column( columnDefinition = "TEXT")
    private String Description;

    @Column
    private Long Likes;

    @Column
    private Long disLike;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime AnsPostedAt;

    @ManyToOne
    @JoinColumn(name = "QuestionId")
    private ForumQuestion forumQuestion;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Users user;

    @PrePersist
    public void setPostedAt() {
        this.AnsPostedAt = LocalDateTime.now();
    }
}
