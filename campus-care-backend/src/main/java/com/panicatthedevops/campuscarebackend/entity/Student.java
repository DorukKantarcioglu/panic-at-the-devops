package com.panicatthedevops.campuscarebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    private Long id;
    private String name;
    private String email;
    private String hesCode;
    private boolean allowedOnCampus;
    private boolean vaccinated;
    private boolean tested;

    @ManyToMany
    @JoinTable(name = "course_enrollment",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "courseCode"))
    @JsonIgnoreProperties("studentList")
    List<Course> coursesTaken;

    public void addCourse(Course course) {
        coursesTaken.add(course);
        course.getStudentList().add(this);
    }

    public void removeCourse(Course course) {
        coursesTaken.remove(course);
        course.getStudentList().remove(this);
    }
}
