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
        name = "FQComments"
)

public class FQComments {
    @Id
    @GeneratedValue()
    @Column(updatable = false)
    private Long CommentId;

    @Column( columnDefinition = "TEXT")
    private String Comment;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime AnsPostedAt;

    @ManyToOne
    @JoinColumn(name = "QuestionId")
    private ForumQuestion forumQuestion;

    @PrePersist
    public void setPostedAt() {
        this.AnsPostedAt = LocalDateTime.now();
    }
}
