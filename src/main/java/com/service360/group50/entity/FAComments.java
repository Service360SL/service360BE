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
        name = "FAComments"
)
public class FAComments {
    @Id
    @GeneratedValue()
    @Column(updatable = false)
    private Long CommentId;

    @Column( columnDefinition = "TEXT")
    private String Comment;

    @Column
    private String type;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime AnsPostedAt;

    @ManyToOne
    @JoinColumn(name = "AnsId")
    private ForumAnswer forumAnswer;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Users user;

    @PrePersist
    public void setPostedAt() {
        this.AnsPostedAt = LocalDateTime.now();
    }
}
