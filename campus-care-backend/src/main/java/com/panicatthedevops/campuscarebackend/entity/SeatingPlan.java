package com.panicatthedevops.campuscarebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(mappedBy = "seatingPlan")
    @JsonIgnoreProperties("seatingPlan")
    private Set<SeatingObject> seatingSet;

}
