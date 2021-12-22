package com.panicatthedevops.campuscarebackend.controller;

import com.panicatthedevops.campuscarebackend.entity.Notification;
import com.panicatthedevops.campuscarebackend.entity.Staff;
import com.panicatthedevops.campuscarebackend.service.NotificationService;
import com.panicatthedevops.campuscarebackend.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/staffs")
public class StaffController {
    private final StaffService staffService;
    private final NotificationService notificationService;

    @Autowired
    public StaffController(StaffService staffService, NotificationService notificationService) {
        this.staffService = staffService;
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<Staff>> getStaffs() {
        return ResponseEntity.ok(staffService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaff(@PathVariable Long id) {
        return ResponseEntity.ok(staffService.findById(id));
    }

    @GetMapping(headers = "hesCode")
    public ResponseEntity<Staff> getStaffByHesCode(@RequestHeader String hesCode) {
        return ResponseEntity.ok(staffService.findByHesCode(hesCode));
    }

    @PostMapping
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        return new ResponseEntity<>(staffService.save(staff), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}", headers = "hesCode")
    public ResponseEntity<Staff> updateHesCode(@PathVariable Long id, @RequestHeader String hesCode) {
        return ResponseEntity.ok(staffService.updateHesCode(id, hesCode));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable Long id) {
        staffService.deleteById(id);
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
