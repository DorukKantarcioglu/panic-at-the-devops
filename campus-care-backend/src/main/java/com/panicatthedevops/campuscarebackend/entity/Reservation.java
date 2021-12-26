package com.panicatthedevops.campuscarebackend.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

/**
 * Represents the reservations a user can make.
 * A reservation can be made by only one user.
 * @version 1.0
 */

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String date;

    private String timeSlot;
    private String place;
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"reservations", "password"})
    private User user;

}
