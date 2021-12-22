package com.panicatthedevops.campuscarebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

import javax.persistence.*;

@Inheritance
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private Long id;
    private String name;
    private String password;
    private String email;
    private String hesCode;
    private String phoneNumber;
    private boolean allowedOnCampus;
    private boolean vaccinated;
    private boolean tested;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Notification> notificationList;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private Set<Reservation> reservations;
}

