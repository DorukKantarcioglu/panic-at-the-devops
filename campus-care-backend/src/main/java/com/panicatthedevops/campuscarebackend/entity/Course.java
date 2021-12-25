package com.panicatthedevops.campuscarebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    private String courseCode;
    private String courseName;
    private int section;
    private int capacity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    @JsonIgnoreProperties({"coursesTaken", "reservations", "password"})
    private Instructor instructor;

    @ManyToMany(mappedBy = "coursesTaken", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"coursesTaken", "reservations", "password"})
    List<Student> studentList;

    @OneToOne
    @JoinColumn(name = "seatingPlan_id", referencedColumnName = "id")
    @JsonIgnoreProperties("course")
    private SeatingPlan seatingPlan;

    private String schedule;
}
