package com.panicatthedevops.pandemicmanager.web;

import com.panicatthedevops.pandemicmanager.entity.Student;
import com.panicatthedevops.pandemicmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(headers = {"hesCode", "trIdNumber", "eDevletPassword"})
    public ResponseEntity<String> validateHesCode(@RequestHeader String hesCode, @RequestHeader String trIdNumber, @RequestHeader String eDevletPassword) {
        return ResponseEntity.ok(studentService.validateHesCode(hesCode, trIdNumber, eDevletPassword));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> updateHesCode(@PathVariable Long id, @RequestHeader String hesCode) {
        return ResponseEntity.ok(studentService.updateHesCode(id, hesCode));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
