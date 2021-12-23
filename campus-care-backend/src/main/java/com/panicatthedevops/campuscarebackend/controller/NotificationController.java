package com.panicatthedevops.campuscarebackend.controller;

import com.panicatthedevops.campuscarebackend.entity.Notification;
import com.panicatthedevops.campuscarebackend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications(){
        return ResponseEntity.ok(notificationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotification(@PathVariable long id){
        return ResponseEntity.ok(notificationService.getNotification(id));
    }

    @GetMapping("/motivationalQuotes")
    public ResponseEntity<Notification> getRandomMotivationalQuote(){
        return ResponseEntity.ok(notificationService.getRandomMotivationalQuote());
    }

    @PostMapping
    public ResponseEntity<Notification> createMotivationalQuote(@RequestHeader String content){
        return ResponseEntity.ok(notificationService.saveMotivationalQuote(content));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable long id){
        notificationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
