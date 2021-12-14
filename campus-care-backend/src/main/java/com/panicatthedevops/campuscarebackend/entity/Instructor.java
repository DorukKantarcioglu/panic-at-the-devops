package com.panicatthedevops.campuscarebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "instructor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instructor {
    @Id
    private Long id;
    private String name;
    private String email;
    private String hesCode;

    @OneToMany(targetEntity = Course.class)
    private Set<Course> coursesGiven;
}
