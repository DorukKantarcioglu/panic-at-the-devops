package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.Instructor;
import com.panicatthedevops.campuscarebackend.entity.Course;
import com.panicatthedevops.campuscarebackend.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Configuration
@EnableScheduling
public class ScheduledService {
    private final NotificationService notificationServices;
    private final SeatingPlanService seatingPlanService;
    private final CovidService covidService;
    private final InstructorService instructorService;

    @Autowired
    public ScheduledService(NotificationService notificationService, SeatingPlanService seatingPlanService, CovidService covidService, InstructorService instructorService){
        this.notificationServices = notificationService;
        this.seatingPlanService = seatingPlanService;
        this.covidService = covidService;
        this.instructorService = instructorService;
    }

    @Scheduled(cron = "0 20 8 * * MON-FRI")
    public void notifyInstructors(){
        List<Instructor> instructors = instructorService.findAll();
        for(Instructor instructor : instructors){
            List<String> notificationContents = new ArrayList<>();
            for(Course course : instructor.getCoursesGiven()){
                List<Student> studentList = covidService.getNotAllowedStudentsInCourse(course.getCourseCode());
                if(studentList.isEmpty())
                    continue;

                Calendar c = Calendar.getInstance();
                int today = c.get(Calendar.DAY_OF_WEEK) - 1;

                List<String> days = Arrays.stream(course.getSchedule().split(" ")).collect(Collectors.toList());
                if(!days.contains("" + today)){
                    continue;
                }

                String notificationContent = "For the course " + course.getCourseCode() + " the disallowed students are:\n";
                for(Student student : studentList){
                    notificationContent += student.getName() + " ";
                }
                notificationContents.add(notificationContent);
            }
            for(String notificationContent : notificationContents){
                notificationServices.saveCovidNotification(notificationContent, instructor.getId());
            }
        }
    }
}
