package com.panicatthedevops.campuscarebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Represents the students enrolled in the campus.
 * Students have current selected cafeterias and smoking areas to indicate where they are located in.
 * A student also has many enrolled courses.
 * @version 1.0
 * @see com.panicatthedevops.campuscarebackend.entity.User
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Builder
public class Student extends User {
    public Student(Long id, String name, String password, String email, String hesCode, String phoneNumber, boolean allowedOnCampus, boolean vaccinated, boolean tested, List<Course> coursesTaken, List<Notification> notificationList, Set<Reservation> reservationSet, String selectedCafeteria, String selectedSmokingArea, Set<SeatingObject> seatings) {
        super(id, name, password, email, hesCode, phoneNumber, allowedOnCampus, vaccinated, tested, notificationList, reservationSet);
        this.coursesTaken = coursesTaken;
        this.selectedCafeteria = selectedCafeteria;
        this.selectedSmokingArea = selectedSmokingArea;
        this.seatings = seatings;
    }

    public Student(List<Course> coursesTaken, String selectedCafeteria, String selectedSmokingArea, Set<SeatingObject> seatings) {
        this.coursesTaken = coursesTaken;
        this.selectedCafeteria = selectedCafeteria;
        this.selectedSmokingArea = selectedSmokingArea;
        this.seatings = seatings;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "course_enrollment",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "courseCode"))
    @JsonIgnoreProperties("studentList")
    List<Course> coursesTaken;

    private String selectedCafeteria;
    private String selectedSmokingArea;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("student")
    private Set<SeatingObject> seatings;

    public void addCourse(Course course) {
        coursesTaken.add(course);
        course.getStudentList().add(this);
    }

    public void removeCourse(Course course) {
        coursesTaken.remove(course);
        course.getStudentList().remove(this);
    }
}
