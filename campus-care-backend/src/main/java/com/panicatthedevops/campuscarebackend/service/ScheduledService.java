package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.Course;
import com.panicatthedevops.campuscarebackend.entity.Instructor;
import com.panicatthedevops.campuscarebackend.entity.Notification;
import com.panicatthedevops.campuscarebackend.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Configuration
@EnableScheduling
public class ScheduledService {
    private final NotificationService notificationServices;
    private final SeatingPlanService seatingPlanService;
    private final CovidService covidService;
    private final InstructorService instructorService;
    private final StudentService studentService;

    @Autowired
    public ScheduledService(NotificationService notificationService, SeatingPlanService seatingPlanService, CovidService covidService, InstructorService instructorService, StudentService studentService){
        this.notificationServices = notificationService;
        this.seatingPlanService = seatingPlanService;
        this.covidService = covidService;
        this.instructorService = instructorService;
        this.studentService = studentService;
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

                StringBuilder notificationContent = new StringBuilder("For the course " + course.getCourseCode() + " the disallowed students are:\n");
                for(Student student : studentList){
                    notificationContent.append(student.getName()).append(" ");
                }
                notificationContents.add(notificationContent.toString());
            }
            for(String notificationContent : notificationContents){
                notificationServices.saveCovidNotification(notificationContent, instructor.getId());
            }
        }
    }

    @Scheduled( cron = "0 45 6 * * *")
    public void notifyNearbyStudents(){
        List<Student> notAllowedStudents = covidService.getNotAllowedStudents();
        for(Student student : notAllowedStudents){
            List<Student> nearbyStudents = studentService.findNearbyStudents(student.getId());
            if(nearbyStudents.isEmpty())
                continue;

            String censoredFirstName = "" + student.getName().split(" ")[0].charAt(0);
            String censoredSecondName = "" + student.getName().split(" ")[1].charAt(0);
            String notificationContent = "A student sitting near you (" + censoredFirstName + ". " + censoredSecondName + ".) has recently tested positive for covid, you are expected to take a test as soon as possible";
            for(Student nearbyStudent : nearbyStudents){
                if(!notificationServices.existsByContent(notificationContent)){
                    notificationServices.saveCovidNotification(notificationContent, nearbyStudent.getId());
                }
            }

        }
    }

    /**
     * This method will be used to validate all HES codes in the databases. To properly run this,
     * trIdNumber and eGovernmentPassword fields must be consisting of a valid credential pair.
     * @implNote Without valid credentials, this method will not work.
     */
    @Scheduled( cron = "0 30 6 * * *")
    public void periodicHEScodeValidation(){
        covidService.validateHesCodes("----TC KIMLIK-----", "---edevlet şifresi---");
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void deleteExpiredNotifications(){
        List<Notification> notificationList = notificationServices.getCovidNotifications();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = dtf.format(now).split(" ")[0];
        try {
            Date firstDate = sdf.parse(date);
            for (Notification notification : notificationList) {
                Date notificationDate = sdf.parse(notification.getDate());
                if (TimeUnit.DAYS.convert(firstDate.getTime() - notificationDate.getTime(), TimeUnit.MILLISECONDS) > 14){
                    notificationServices.deleteById(notification.getId());
                }
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
