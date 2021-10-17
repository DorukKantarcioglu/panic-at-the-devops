package com.panicatthedevops.pandemicmanager.service;

import com.panicatthedevops.pandemicmanager.exception.StudentAlreadyExistsException;
import com.panicatthedevops.pandemicmanager.exception.StudentNotFoundException;
import com.panicatthedevops.pandemicmanager.model.Student;
import com.panicatthedevops.pandemicmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " does not exist."));
    }

    public Student save(Student student) {
        if (studentRepository.existsById(student.getId())) {
            throw new StudentAlreadyExistsException("Student with id " + student.getId() + " already exists.");
        }
        else {
            return studentRepository.save(student);
        }
    }

    public Student updateHesCode(Long id, String hesCode) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student with id " + id + " does not exist.");
        }
        else {
            Student student = studentRepository.getById(id);
            student.setHesCode(hesCode);
            return studentRepository.save(student);
        }
    }

    public void deleteById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student with id " + id + " does not exist.");
        }
        else {
            studentRepository.deleteById(id);
        }
    }
}
