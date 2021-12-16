package com.panicatthedevops.campuscarebackend.controller;

import com.panicatthedevops.campuscarebackend.entity.Instructor;
import com.panicatthedevops.campuscarebackend.entity.Student;
import com.panicatthedevops.campuscarebackend.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> getInstructors() {
        return ResponseEntity.ok(instructorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructor(@PathVariable Long id) {
        return ResponseEntity.ok(instructorService.findById(id));
    }

    @GetMapping(headers = "hesCode")
    public ResponseEntity<Instructor> getInstructorByHesCode(@RequestHeader String hesCode) {
        return ResponseEntity.ok(instructorService.findByHesCode(hesCode));
    }

    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor) {
        return new ResponseEntity<>(instructorService.save(instructor), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}", headers = "courseCode")
    public ResponseEntity<Instructor> addCourse(@PathVariable Long id, @RequestHeader String courseCode) {
        return ResponseEntity.ok(instructorService.addCourse(id, courseCode));
    }

    @DeleteMapping(path = "/{id}", headers = "courseCode")
    public ResponseEntity<Instructor> removeCourse(@PathVariable Long id, @RequestHeader String courseCode) {
        return ResponseEntity.ok(instructorService.removeCourse(id, courseCode));
    }

    @GetMapping(path = "/{id}", headers = "courseCode")
    public ResponseEntity<List<Student>> getNotAllowedStudents(@PathVariable Long id, @RequestHeader String courseCode) {
        return ResponseEntity.ok(instructorService.findNotAllowedStudents(id, courseCode));
    }

    @PatchMapping(path = "/{id}", headers = "hesCode")
    public ResponseEntity<Instructor> updateHesCode(@PathVariable Long id, @RequestHeader String hesCode) {
        return ResponseEntity.ok(instructorService.updateHesCode(id, hesCode));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
