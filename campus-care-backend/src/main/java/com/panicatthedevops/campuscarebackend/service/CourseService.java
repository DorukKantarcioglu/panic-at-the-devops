package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.Course;
import com.panicatthedevops.campuscarebackend.entity.SeatingPlan;
import com.panicatthedevops.campuscarebackend.exception.CourseAlreadyExistsException;
import com.panicatthedevops.campuscarebackend.exception.CourseNotFoundException;
import com.panicatthedevops.campuscarebackend.exception.SeatingPlanNotFoundException;
import com.panicatthedevops.campuscarebackend.repository.CourseRepository;
import com.panicatthedevops.campuscarebackend.repository.SeatingPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final SeatingPlanRepository seatingPlanRepository;

    public CourseService(CourseRepository courseRepository, SeatingPlanRepository seatingPlanRepository) {
        this.courseRepository = courseRepository;
        this.seatingPlanRepository = seatingPlanRepository;
    }

    @Autowired


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

    public Course addSeatingPlan(Long seatingPlanId, String courseCode){
        if(!courseRepository.existsByCourseCode(courseCode)){
            throw new CourseNotFoundException("Course with code " + courseCode + " does not exist.");
        }
        else{
            Course course = courseRepository.findByCourseCode(courseCode).iterator().next();
            if(!seatingPlanRepository.existsById(seatingPlanId)){
                throw new SeatingPlanNotFoundException("Seating plan with id " + seatingPlanId + " does not exist.");
            }
            else{
                SeatingPlan seatingPlan = seatingPlanRepository.findById(seatingPlanId).get();
                course.setSeatingPlan(seatingPlan);
                seatingPlan.setCourse(course);
                seatingPlanRepository.save(seatingPlan);
                courseRepository.save(course);
                return course;
            }
        }
    }
}
