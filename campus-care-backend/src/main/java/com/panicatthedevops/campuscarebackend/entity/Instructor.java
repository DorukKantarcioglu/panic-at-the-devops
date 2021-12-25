package com.panicatthedevops.campuscarebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

/**
 * Represents an instructor.
 * Instructor has many courses to teach.
 * @see com.panicatthedevops.campuscarebackend.entity.User
 * @version 1.0
 */

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

    @OneToMany(mappedBy = "instructor", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"instructor", "studentList"})
    private Set<Course> coursesGiven;

    /**
     * Adds a new course to the courses of instructor.
     * Sets the instructor of course to be this instructor.
     * @param course Course to be added
     */
    public void addCourse(Course course) {
        coursesGiven.add(course);
        course.setInstructor(this);
    }

    /**
     * Removes the specified course from instructor's taught courses.
     * Sets the instructor of course to be null.
     * @param course Course to be removed
     */
    public void removeCourse(Course course) {
        coursesGiven.remove(course);
        course.setInstructor(null);
    }
}
