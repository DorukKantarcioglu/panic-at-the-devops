package com.panicatthedevops.campuscarebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

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
    @JsonIgnoreProperties("seatings")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seatingPlan_id")
    @JsonIgnoreProperties("seatingSet")
    private SeatingPlan seatingPlan;

    private int rowNo;
    private int columnNo;

}
