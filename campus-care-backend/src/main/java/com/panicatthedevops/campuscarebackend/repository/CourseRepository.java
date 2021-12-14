package com.panicatthedevops.campuscarebackend.repository;

import com.panicatthedevops.campuscarebackend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    List<Course> findByCourseCode(String courseCode);
    List<Course> findByCourseName(String courseName);
    boolean existsByCourseCode(String courseCode);
    void deleteByCourseCode(String courseCode);
}
