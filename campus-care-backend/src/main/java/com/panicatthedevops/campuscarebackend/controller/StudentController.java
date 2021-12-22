package com.panicatthedevops.campuscarebackend.controller;

import com.panicatthedevops.campuscarebackend.entity.Notification;
import com.panicatthedevops.campuscarebackend.entity.Student;
import com.panicatthedevops.campuscarebackend.service.NotificationService;
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
    private final NotificationService notificationService;

    @Autowired
    public StudentController(StudentService studentService, NotificationService notificationService) {
        this.studentService = studentService;
        this.notificationService = notificationService;
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

    @PatchMapping(path = "/{id}/area", headers = "cafeteriaName")
    public ResponseEntity<Student> updateSelectedCafeteria(@PathVariable Long id, @RequestHeader String cafeteriaName) {
        return ResponseEntity.ok(studentService.updateSelectedCafeteria(id, cafeteriaName));
    }

    @PatchMapping(path = "/{id}/area", headers = "smokingAreaName")
    public ResponseEntity<Student> updateSelectedSmokingArea(@PathVariable Long id, @RequestHeader String smokingAreaName) {
        return ResponseEntity.ok(studentService.updateSelectedSmokingArea(id, smokingAreaName));
    }

    @DeleteMapping("/{id}/area")
    public ResponseEntity<?> removeSelectedArea(@PathVariable Long id, @RequestHeader String type) {
        if(type.equals("smoking"))
            return ResponseEntity.ok(studentService.removeSelectedSmokingArea(id));
        else if(type.equals("cafeteria"))
            return ResponseEntity.ok(studentService.removeSelectedCafeteria(id));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RequestHeader for type " + type + " is invalid.");
    }

    @PatchMapping("/{id}/courses")
    public ResponseEntity<Student> addCourse(@PathVariable Long id, @RequestHeader String courseCode) {
        return ResponseEntity.ok(studentService.addCourse(id, courseCode));
    }

    @DeleteMapping("/{id}/courses/{courseCode}")
    public ResponseEntity<Student> removeCourse(@PathVariable Long id, @PathVariable String courseCode) {
        return ResponseEntity.ok(studentService.removeCourse(id, courseCode));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/notifications")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable Long id){
        return ResponseEntity.ok(notificationService.getNotifications(id));
    }

    @PostMapping("/{id}/notifications")
    public ResponseEntity<Notification> createNotification(@PathVariable Long id, @RequestHeader String content){
        return ResponseEntity.ok(notificationService.saveCovidNotification(content, id));
    }
}
