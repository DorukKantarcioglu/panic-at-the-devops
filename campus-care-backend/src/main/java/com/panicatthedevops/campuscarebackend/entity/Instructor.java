package com.panicatthedevops.campuscarebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "instructor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instructor {
    @Id
    private Long id;
    private String name;
    private String email;
    private String hesCode;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("instructor")
    private Set<Course> coursesGiven;

    public void addCourse(Course course) {
        coursesGiven.add(course);
        course.setInstructor(this);
    }

    public void removeCourse(Course course) {
        coursesGiven.remove(course);
        course.setInstructor(null);
    }
}
