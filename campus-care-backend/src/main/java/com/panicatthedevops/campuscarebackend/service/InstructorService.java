package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.Course;
import com.panicatthedevops.campuscarebackend.entity.Instructor;
import com.panicatthedevops.campuscarebackend.exception.CourseNotFoundException;
import com.panicatthedevops.campuscarebackend.exception.HesCodeAlreadyExistsException;
import com.panicatthedevops.campuscarebackend.exception.InstructorAlreadyExistsException;
import com.panicatthedevops.campuscarebackend.exception.InstructorNotFoundException;
import com.panicatthedevops.campuscarebackend.repository.CourseRepository;
import com.panicatthedevops.campuscarebackend.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository, CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
    }

    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    public Instructor findById(Long id) {
        return instructorRepository.findById(id).orElseThrow(()
                -> new InstructorNotFoundException("Instructor with id " + id + " does not exist."));
    }

    public List<Course> getCourses(Long id){
        if (!instructorRepository.existsById(id)) {
            throw new InstructorNotFoundException("Instructor with id " + id + " does not exist.");
        }
        else {
            return courseRepository.findAllByInstructorId(id);
        }
    }

    public Instructor findByHesCode(String hesCode) {
        if (instructorRepository.findByHesCode(hesCode).isEmpty()) {
            throw new InstructorNotFoundException("Instructor with HES code " + hesCode + " does not exist.");
        }
        else {
            return instructorRepository.findByHesCode(hesCode).iterator().next();
        }
    }

    public Instructor save(Instructor instructor) {
        if (instructorRepository.existsById(instructor.getId())) {
            throw new InstructorAlreadyExistsException("Instructor with id " + instructor.getId() + " already exists.");
        }
        else {
            return instructorRepository.save(instructor);
        }
    }

    public Instructor addCourse(Long id, String courseCode) {
        if (instructorRepository.existsById(id)) {
            Instructor instructor = instructorRepository.findById(id).get();
            if (courseRepository.existsByCourseCode(courseCode)) {
                instructor.addCourse(courseRepository.findByCourseCode(courseCode).iterator().next());
                return instructorRepository.save(instructor);
            }
            else {
                throw new CourseNotFoundException("Course with code " + courseCode + " does not exist.");
            }
        }
        else {
            throw new InstructorNotFoundException("Instructor with id " + id + " does not exist.");
        }
    }

    public Instructor removeCourse(Long id, String courseCode) {
        if (instructorRepository.existsById(id)) {
            Instructor instructor = instructorRepository.findById(id).get();
            if (courseRepository.existsByCourseCode(courseCode)) {
                instructor.removeCourse(courseRepository.findByCourseCode(courseCode).iterator().next());
                return instructorRepository.save(instructor);
            }
            else {
                throw new CourseNotFoundException("Course with code " + courseCode + " does not exist.");
            }
        }
        else {
            throw new InstructorNotFoundException("Instructor with id " + id + " does not exist.");
        }
    }

    public Instructor updateHesCode(Long id, String hesCode) {
        if (instructorRepository.existsById(id)) {
            if (instructorRepository.existsByHesCode(hesCode)) {
                throw new HesCodeAlreadyExistsException("HES code " + hesCode + " belongs to another instructor.");
            }
            Instructor instructor = instructorRepository.findById(id).get();
            instructor.setHesCode(hesCode);
            return instructorRepository.save(instructor);
        }
        else {
            throw new InstructorNotFoundException("Instructor with id " + id + " does not exist.");
        }
    }

    public Instructor updatePhoneNumber(Long id, String phoneNumber) {
        if (instructorRepository.existsById(id)) {
            Instructor instructor = instructorRepository.findById(id).get();
            instructor.setPhoneNumber(phoneNumber);
            return instructorRepository.save(instructor);
        }
        else {
            throw new InstructorNotFoundException("Instructor with id " + id + " does not exist.");
        }
    }

    public void deleteById(Long id) {
        if (instructorRepository.existsById(id)) {
            instructorRepository.deleteById(id);
        }
        else {
            throw new InstructorNotFoundException("Instructor with id " + id + " does not exist.");
        }
    }
}
