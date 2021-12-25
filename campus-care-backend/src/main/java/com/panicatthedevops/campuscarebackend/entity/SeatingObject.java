package com.panicatthedevops.campuscarebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

/**
 * Seating object that has the row and column info of a specific student in a specific seating plan of a specific course
 * @version 1.0
 */

@Entity
@Table(name = "SeatingObject")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatingObject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    @JsonIgnoreProperties({"seatings", "password"})
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seatingPlan_id")
    @JsonIgnoreProperties("seatingSet")
    private SeatingPlan seatingPlan;

    private int rowNo;
    private int columnNo;

}
