package com.panicatthedevops.campuscarebackend.entity;

import com.panicatthedevops.campuscarebackend.util.SeatingObject;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

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


}
