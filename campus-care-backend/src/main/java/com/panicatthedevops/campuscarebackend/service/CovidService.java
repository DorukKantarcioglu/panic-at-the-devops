package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.*;
import com.panicatthedevops.campuscarebackend.exception.AreaNotFoundException;
import com.panicatthedevops.campuscarebackend.exception.CourseNotFoundException;
import com.panicatthedevops.campuscarebackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CovidService {
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final StaffRepository staffRepository;
    private final CourseRepository courseRepository;
    private final AreaRepository areaRepository;

    @Autowired
    public CovidService(StudentRepository studentRepository, InstructorRepository instructorRepository, StaffRepository staffRepository, CourseRepository courseRepository, AreaRepository areaRepository) {
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
        this.staffRepository = staffRepository;
        this.courseRepository = courseRepository;
        this.areaRepository = areaRepository;
    }

    public List<Student> getNotAllowedStudents() {
        return studentRepository.findAll().stream().filter(s -> !s.isAllowedOnCampus()).collect(Collectors.toList());
    }

    public List<Instructor> getNotAllowedInstructors() {
        return instructorRepository.findAll().stream().filter(s -> !s.isAllowedOnCampus()).collect(Collectors.toList());
    }

    public List<Staff> getNotAllowedStaffs() {
        return staffRepository.findAll().stream().filter(s -> !s.isAllowedOnCampus()).collect(Collectors.toList());
    }

    public List<Student> getNotAllowedStudentsInCourse(String courseCode) {
        if (courseRepository.existsById(courseCode)) {
            Course course = courseRepository.findById(courseCode).get();
            return course.getStudentList().stream().filter(s -> !s.isAllowedOnCampus()).collect(Collectors.toList());
        }
        else {
            throw new CourseNotFoundException("Course with code " + courseCode + " does not exist.");
        }
    }

    public List<Student> getNotAllowedStudentsInCafeteria(String cafeteriaName) {
        if (areaRepository.existsById(cafeteriaName)) {
            return studentRepository.findAll().stream().filter(s -> s.getSelectedCafeteria() != null && s.getSelectedCafeteria().equals(cafeteriaName) && !s.isAllowedOnCampus()).collect(Collectors.toList());
        }
        else {
            throw new AreaNotFoundException("Cafeteria " + cafeteriaName + " does not exist.");
        }
    }

    public List<Student> getNotAllowedStudentsInSmokingArea(String smokingAreaName) {
        if (areaRepository.existsById(smokingAreaName)) {
            return studentRepository.findAll().stream().filter(s -> s.getSelectedSmokingArea() != null && s.getSelectedSmokingArea().equals(smokingAreaName) && !s.isAllowedOnCampus()).collect(Collectors.toList());
        }
        else {
            throw new AreaNotFoundException("Smoking area " + smokingAreaName + " does not exist.");
        }
    }



}
