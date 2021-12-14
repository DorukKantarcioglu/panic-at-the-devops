package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.Course;
import com.panicatthedevops.campuscarebackend.entity.Instructor;
import com.panicatthedevops.campuscarebackend.exception.HesCodeAlreadyExistsException;
import com.panicatthedevops.campuscarebackend.exception.InstructorAlreadyExistsException;
import com.panicatthedevops.campuscarebackend.exception.InstructorNotFoundException;
import com.panicatthedevops.campuscarebackend.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    public Instructor findById(Long id) {
        return instructorRepository.findById(id).orElseThrow(()
        -> new InstructorNotFoundException("Instructor with id " + id + " does not exist."));
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

    public Instructor addCourse(Long id, Course course) {
        if (instructorRepository.existsById(id)) {
            Instructor instructor = instructorRepository.getById(id);
            instructor.getCoursesGiven().add(course);
            return instructorRepository.save(instructor);
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
            Instructor instructor = instructorRepository.getById(id);
            instructor.setHesCode(hesCode);
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
