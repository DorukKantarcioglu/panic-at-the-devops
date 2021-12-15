package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.Course;
import com.panicatthedevops.campuscarebackend.exception.CourseAlreadyExistsException;
import com.panicatthedevops.campuscarebackend.exception.CourseNotFoundException;
import com.panicatthedevops.campuscarebackend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findByCourseCode(String courseCode) {
        if (courseRepository.findByCourseCode(courseCode).isEmpty()) {
            throw new CourseNotFoundException("Course with code " + courseCode + " does not exist.");
        }
        else {
            return courseRepository.findByCourseCode(courseCode).iterator().next();
        }
    }

    public Course findByCourseName(String courseName) {
        if (courseRepository.findByCourseName(courseName).isEmpty()) {
            throw new CourseNotFoundException("Course with name " + courseName + " does not exist.");
        }
        else {
            return courseRepository.findByCourseName(courseName).iterator().next();
        }
    }

    public Course save(Course course) {
        if (courseRepository.existsByCourseCode(course.getCourseCode())) {
            throw new CourseAlreadyExistsException("Course with code " + course.getCourseCode() + " already exists.");
        }
        else {
            return courseRepository.save(course);
        }
    }

    public void deleteByCourseCode(String courseCode) {
        if (courseRepository.existsByCourseCode(courseCode)) {
            courseRepository.deleteByCourseCode(courseCode);
        }
        else {
            throw new CourseNotFoundException("Course with code " + courseCode + " does not exist.");
        }
    }
}
