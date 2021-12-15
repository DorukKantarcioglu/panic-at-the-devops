package com.panicatthedevops.campuscarebackend.controller;

import com.panicatthedevops.campuscarebackend.entity.Student;
import com.panicatthedevops.campuscarebackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @GetMapping(headers = "hesCode")
    public ResponseEntity<Student> getStudentByHesCode(@RequestHeader String hesCode) {
        return ResponseEntity.ok(studentService.findByHesCode(hesCode));
    }

    @PostMapping(headers = {"hesCode", "trIdNumber", "eGovernmentPassword"})
    public ResponseEntity<String> validateHesCode(@RequestHeader String hesCode, @RequestHeader String trIdNumber, @RequestHeader String eGovernmentPassword) {
        return ResponseEntity.ok(studentService.validateHesCode(hesCode, trIdNumber, eGovernmentPassword));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}", headers = "hesCode")
    public ResponseEntity<Student> updateHesCode(@PathVariable Long id, @RequestHeader String hesCode) {
        return ResponseEntity.ok(studentService.updateHesCode(id, hesCode));
    }

    @PatchMapping(path = "/{id}", headers = "courseCode")
    public ResponseEntity<Student> addCourse(@PathVariable Long id, @RequestHeader String courseCode) {
        return ResponseEntity.ok(studentService.addCourse(id, courseCode));
    }

    @DeleteMapping(path = "/{id}", headers = "courseCode")
    public ResponseEntity<Student> removeCourse(@PathVariable Long id, @RequestHeader String courseCode) {
        return ResponseEntity.ok(studentService.removeCourse(id, courseCode));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
