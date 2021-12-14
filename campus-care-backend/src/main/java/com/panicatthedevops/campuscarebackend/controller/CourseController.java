package com.panicatthedevops.campuscarebackend.controller;

import com.panicatthedevops.campuscarebackend.entity.Course;
import com.panicatthedevops.campuscarebackend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{courseCode}")
    public ResponseEntity<Course> getCourse(@PathVariable String courseCode) {
        return ResponseEntity.ok(courseService.findByCourseCode(courseCode));
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.save(course), HttpStatus.CREATED);
    }

    @DeleteMapping("/{courseCode}")
    public ResponseEntity<?> deleteCourse(@PathVariable String courseCode) {
        courseService.deleteByCourseCode(courseCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
