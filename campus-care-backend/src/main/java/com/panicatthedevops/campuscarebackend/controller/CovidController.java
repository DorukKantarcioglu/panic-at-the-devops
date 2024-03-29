package com.panicatthedevops.campuscarebackend.controller;

import com.panicatthedevops.campuscarebackend.entity.Instructor;
import com.panicatthedevops.campuscarebackend.entity.Staff;
import com.panicatthedevops.campuscarebackend.entity.Student;
import com.panicatthedevops.campuscarebackend.entity.User;
import com.panicatthedevops.campuscarebackend.service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/covid")
public class CovidController {
    private final CovidService covidService;

    @Autowired
    public CovidController(CovidService covidService) {
        this.covidService = covidService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getNotAllowedStudents() {
        return ResponseEntity.ok(covidService.getNotAllowedStudents());
    }

    @GetMapping("/instructors")
    public ResponseEntity<List<Instructor>> getNotAllowedInstructors() {
        return ResponseEntity.ok(covidService.getNotAllowedInstructors());
    }

    @GetMapping("/staffs")
    public ResponseEntity<List<Staff>> getNotAllowedStaffs() {
        return ResponseEntity.ok(covidService.getNotAllowedStaffs());
    }

    @GetMapping("/courses/{courseCode}")
    public ResponseEntity<List<Student>> getNotAllowedStudentsInCourse(@PathVariable String courseCode) {
        return ResponseEntity.ok(covidService.getNotAllowedStudentsInCourse(courseCode));
    }

    @GetMapping("/cafeterias/{cafeteriaName}")
    public ResponseEntity<List<Student>> getNotAllowedStudentsInCafeteria(@PathVariable String cafeteriaName) {
        return ResponseEntity.ok(covidService.getNotAllowedStudentsInCafeteria(cafeteriaName));
    }

    @GetMapping("/smokingAreas/{smokingAreaName}")
    public ResponseEntity<List<Student>> getNotAllowedStudentsInSmokingArea(@PathVariable String smokingAreaName) {
        return ResponseEntity.ok(covidService.getNotAllowedStudentsInSmokingArea(smokingAreaName));
    }

    @PutMapping(headers = "hesCode")
    public ResponseEntity<User> validateHesCode(@RequestHeader String hesCode, @RequestHeader String trIdNumber, @RequestHeader String eGovernmentPassword) {
        return ResponseEntity.ok(covidService.validateHesCode(hesCode, trIdNumber, eGovernmentPassword));
    }

    @PutMapping
    public ResponseEntity<List<User>> validateHesCode(@RequestHeader String trIdNumber, @RequestHeader String eGovernmentPassword) {
        return ResponseEntity.ok(covidService.validateHesCodes(trIdNumber, eGovernmentPassword));
    }

    @GetMapping("/statistics/notAllowed")
    public ResponseEntity<Integer> getNotAllowedStatistics() {
        return ResponseEntity.ok(covidService.getNotAllowedStatistics());
    }

    @GetMapping("/statistics/vaccinated")
    public ResponseEntity<Integer> getVaccinatedStatistics() {
        return ResponseEntity.ok(covidService.getVaccinatedStatistics());
    }

    @GetMapping("/statistics/notVaccinated")
    public ResponseEntity<Integer> getNotVaccinatedStatistics() {
        return ResponseEntity.ok(covidService.getNotVaccinatedStatistics());
    }

    @GetMapping("/statistics/tested")
    public ResponseEntity<Integer> getTestedStatistics() {
        return ResponseEntity.ok(covidService.getTestedStatistics());
    }

}
