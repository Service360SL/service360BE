package com.service360.group50.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "ForumQuestion"
)
public class ForumQuestion {
    @Id
    @GeneratedValue()
    @Column(updatable = false)
    private Long QuestionId;

    @Column( columnDefinition = "TEXT")
    private String QuestionTitle;

    @Column( columnDefinition = "TEXT")
    private String QuestionImages;

    @Column( columnDefinition = "TEXT")
    private String Category;

    @Column( columnDefinition = "TEXT")
    private String Description;

    @Column
    private Long Likes;
    @Column
    private Long Views;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime postedAt;

    @OneToMany(mappedBy = "forumQuestion")
    private List<ForumAnswer> forumAnswers;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Users user;

    @PrePersist
    public void setPostedAt() {
        this.postedAt = LocalDateTime.now();
    }


}