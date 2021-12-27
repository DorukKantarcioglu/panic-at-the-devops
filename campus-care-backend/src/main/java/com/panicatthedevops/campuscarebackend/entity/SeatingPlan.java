package com.panicatthedevops.campuscarebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Seating plan object that has a list of seating objects that belong to it.
 * It is assigned to one course and has the row and column numbers of the seating plan
 * @version 1.0
 */
@Entity
@Table(name = "seatingPlan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private int rowNumber;
    private int columnNumber;

    @OneToMany(mappedBy = "seatingPlan", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("seatingPlan")
    private Set<SeatingObject> seatingSet;

    @OneToOne(mappedBy = "seatingPlan")
    @JsonIgnoreProperties({"seatingPlan", "notificationList","student"})
    private Course course;
}
