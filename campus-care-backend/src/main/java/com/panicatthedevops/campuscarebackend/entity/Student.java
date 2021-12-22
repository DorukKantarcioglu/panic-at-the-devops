package com.panicatthedevops.campuscarebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Builder
public class Student extends User {
    public Student(Long id, String name, String password, String email, String hesCode, String phoneNumber, boolean allowedOnCampus, boolean vaccinated, boolean tested, List<Course> coursesTaken, List<Notification> notificationList, Set<Reservation> reservationSet, Cafeteria selectedCafeteria, SmokingArea selectedSmokingArea) {
        super(id, name, password, email, hesCode, phoneNumber, allowedOnCampus, vaccinated, tested, notificationList, reservationSet);
        this.coursesTaken = coursesTaken;
        this.selectedCafeteria = selectedCafeteria;
        this.selectedSmokingArea = selectedSmokingArea;
    }

    public Student(List<Course> coursesTaken, Cafeteria selectedCafeteria, SmokingArea selectedSmokingArea) {
        this.coursesTaken = coursesTaken;
        this.selectedCafeteria = selectedCafeteria;
    }

    @ManyToMany
    @JoinTable(name = "course_enrollment",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "courseCode"))
    @JsonIgnoreProperties("studentList")
    List<Course> coursesTaken;

    @Transient
    private Cafeteria selectedCafeteria;

    @Transient
    private SmokingArea selectedSmokingArea;

    public void addCourse(Course course) {
        coursesTaken.add(course);
        course.getStudentList().add(this);
    }

    public void removeCourse(Course course) {
        coursesTaken.remove(course);
        course.getStudentList().remove(this);
    }
}
