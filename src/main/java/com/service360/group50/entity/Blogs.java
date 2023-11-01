package com.service360.group50.entity;

import com.service360.group50.compositekeys.BlogsId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "blogs"
)

@IdClass(BlogsId.class)
public class Blogs {
    @Column( columnDefinition = "TEXT")
    private String blogtitle;

    @Column( columnDefinition = "TEXT")
    private String blogdescription;

    @Column( columnDefinition = "TEXT")
    private String blogimages;

    @Column( columnDefinition = "TEXT")
    private String servicename;

    //serviceproviderid
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    private Users serviceproviders;

    //blogid
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Long blogid;

}
