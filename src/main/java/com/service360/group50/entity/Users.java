package com.service360.group50.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_email_unique",
                        columnNames = "email"
                )
        }
)
public class Users implements UserDetails {

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Long userid;

    @Column( columnDefinition = "TEXT")
    private String firstname;

    @Column(columnDefinition = "TEXT")
    private String lastname;

    @Column( columnDefinition = "TEXT")
    private String email;

    @Column( columnDefinition = "TEXT")
    private String nic;

    @Column( columnDefinition = "TEXT")
    private String phonenumber;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false)
    private LocalDate registrationdate ;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(columnDefinition = "TEXT")
    private String status;

    @Column(columnDefinition = "TEXT")
    private String profilePic;

    @Column( name = "isActive")
    private boolean isactive;

    @Column( columnDefinition = "TEXT")
    private String reason;

    @Column( columnDefinition = "TEXT")
    private String district;

    @Column( columnDefinition = "TEXT")
    private String city;

//    @OneToMany(mappedBy = "users")
//    private List<Complaints> complaints;

//    @ManyToMany
//    @JoinTable(
//            name = "user_todolist", // Name of the join table
//            joinColumns = @JoinColumn(name = "userid"), // FK column in user_vacancy table
//            inverseJoinColumns = @JoinColumn(name = "todolistid") // FK column in user_vacancy table
//    )
//    private List<TodoList> todolist;

    private Boolean locked;
    private Boolean enabled;


    @PrePersist
    protected void onCreate() {
        registrationdate = LocalDate.now();
        isactive = true;
        status = "Pending";
        locked = false;
        enabled = false;
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        return List.of ( new SimpleGrantedAuthority ( role.name ( ) ) );
    }

    public String getPassword () {
        return password;
    }

    @Override
    public String getUsername () {
        return email;
    }

    @Override
    public boolean isAccountNonExpired () {
        return true;
    }

    @Override
    public boolean isAccountNonLocked () {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired () {
        return true;
    }

    @Override
    public boolean isEnabled () {
        return enabled;
    }


}