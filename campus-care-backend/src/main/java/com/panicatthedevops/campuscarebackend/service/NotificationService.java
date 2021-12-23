package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.*;
import com.panicatthedevops.campuscarebackend.exception.NotificationNotFoundException;
import com.panicatthedevops.campuscarebackend.exception.StaffNotFoundException;
import com.panicatthedevops.campuscarebackend.exception.UserNotFoundException;
import com.panicatthedevops.campuscarebackend.repository.InstructorRepository;
import com.panicatthedevops.campuscarebackend.repository.NotificationRepository;
import com.panicatthedevops.campuscarebackend.repository.StaffRepository;
import com.panicatthedevops.campuscarebackend.repository.StudentRepository;
import com.panicatthedevops.campuscarebackend.util.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final StaffRepository staffRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, StudentRepository studentRepository, InstructorRepository instructorRepository, StaffRepository staffRepository) {
        this.notificationRepository = notificationRepository;
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
        this.staffRepository = staffRepository;
    }

    public List<Notification> getNotifications(long userId){
        return notificationRepository.findByUserId(userId);
    }

    public Notification saveMotivationalQuote(String quote){
        return notificationRepository.save(new Notification(0, quote, NotificationType.MOTIVATIONAL_QUOTE, null));
    }

    public Notification getRandomMotivationalQuote(){
        List<Notification> quotes = notificationRepository.findAllByType(NotificationType.MOTIVATIONAL_QUOTE);
        Random rand = new Random();
        return quotes.get(rand.nextInt(quotes.size()));
    }


    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public List<Notification> getMotivationalQuotes(){
        return notificationRepository.findAllByType(NotificationType.MOTIVATIONAL_QUOTE);
    }

    public Notification getNotification(long notificationId){
        if (!notificationRepository.existsById(notificationId)) {
            throw new NotificationNotFoundException("Notification with id " + notificationId + " does not exist.");
        }
        else {
            return notificationRepository.findById(notificationId).get();
        }
    }

    public Notification saveCovidNotification(String content, long userId){
        boolean studentExists = studentRepository.existsById(userId);
        boolean staffExists = staffRepository.existsById(userId);
        boolean instructorExists = instructorRepository.existsById(userId);

        if(!studentExists && !staffExists && !instructorExists){
            throw new UserNotFoundException("User with id " + userId + " does not exist");
        }
        else {
            User user;
            if(studentExists)
                user = studentRepository.findById(userId).get();
            else if(instructorExists)
                user = instructorRepository.findById(userId).get();
            else
                user = staffRepository.findById(userId).get();
            return notificationRepository.save(new Notification(0, content, NotificationType.COVID_NOTIFICATION, user));
        }
    }

    public List<Notification> saveCovidNotification(String content, List<Long> userIds){
        List<Notification> notifications = new ArrayList<>();
        for (Long userId : userIds) {
            notifications.add(saveCovidNotification(content, userId));
        }
        return notifications;

    }

    public void deleteById(long notificationId) {
        if (notificationRepository.existsById(notificationId)) {
            notificationRepository.deleteById(notificationId);
        }
        else {
            throw new NotificationNotFoundException("Notification with id " + notificationId + " does not exist.");
        }
    }




}
