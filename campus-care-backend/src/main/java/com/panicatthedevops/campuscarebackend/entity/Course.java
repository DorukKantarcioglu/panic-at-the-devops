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
    private int capacity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id")
    @JsonIgnoreProperties("coursesGiven")
    private Instructor instructor;

    @ManyToMany(mappedBy = "coursesTaken")
    @JsonIgnoreProperties("coursesTaken")
    List<Student> studentList;
}
