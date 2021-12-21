package com.panicatthedevops.campuscarebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.util.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Instructor extends User {
    public Instructor(Long id, String name, String password, String email, String hesCode, String phoneNumber, boolean allowedOnCampus, boolean vaccinated, boolean tested, Set<Course> coursesGiven, List<Notification> notificationList, Set<Reservation> reservations) {
        super(id, name, password, email, hesCode, phoneNumber, allowedOnCampus, vaccinated, tested, notificationList, reservations);
        this.coursesGiven = coursesGiven;
    }

    public Instructor(Set<Course> coursesGiven) {
        this.coursesGiven = coursesGiven;
    }

    @OneToMany(mappedBy = "instructor")
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
