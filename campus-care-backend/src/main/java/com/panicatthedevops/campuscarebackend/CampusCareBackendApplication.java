package com.panicatthedevops.campuscarebackend;

import com.panicatthedevops.campuscarebackend.entity.*;
import com.panicatthedevops.campuscarebackend.repository.*;
import com.panicatthedevops.campuscarebackend.util.NotificationType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class CampusCareBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(CampusCareBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner lineRunner(
            StudentRepository studentRepository, InstructorRepository instructorRepository, CourseRepository courseRepository, StaffRepository staffRepository,
            ReservationRepository reservationRepository, AreaRepository areaRepository, NotificationRepository notificationRepository, SeatingPlanRepository seatingPlanRepository) {
        return args -> {
            studentRepository.saveAll(Arrays.asList(
                    new Student(1L, "Doruk Kantarcıoğlu", new BCryptPasswordEncoder().encode("doruk-password"), "doruk@campuscare.com", "F4K2-1836-10", "num", false, false, false, null, null, null, "Cafe-in", null, null),
                    //new Student(2L, "Oğuz Ata Çal", "password", "oğuz@campuscare.com", "Oğuz-HES-CODE", "num", true, true, false, null, null, null, null, null, null),
                    new Student(3L, "Yağmur Eryılmaz", new BCryptPasswordEncoder().encode("yagmur-password"), "yağmur@campuscare.com", "C6D9-3181-14", "num", true, true, false, null, null, null, null, null, null)
                    //new Student(28L, "Elif Çenesiz", "password", "elif@campuscare.com", "A6A2-1832-17", "num", false, true, false, null, null, null, null, null, null),
                    //new Student(29L, "Suleyman Hanyyev", "password", "suleyman@campuscare.com", "B4V3-8549-14", "num", false, true, false, null, null, null, "Cafe-in", null, null)
            ));
            instructorRepository.saveAll(Arrays.asList(
                    new Instructor(4L, "Eray Tuzun", new BCryptPasswordEncoder().encode("eray-password"), "tuzun@campuscare.com", "B4V3-8549-14", "+90", false, true, false , null, null, null),
                    new Instructor(12L, "David Davenport", new BCryptPasswordEncoder().encode("david-password"), "david@campuscare.com", "A6A2-1832-17", "+90", true, true, false , null, null, null)
            ));
            courseRepository.saveAll(Arrays.asList(
                    new Course("CS-319-1", "Object-Oriented Software Engineering", 1, 50, instructorRepository.findById(4L).get(), null, null, "1 2 5"),
                    new Course("CS-315-1", "Programming Languages",1, 50, instructorRepository.findById(12L).get(), null, null, "1 4 5"),
                    new Course("MATH-230-1", "Probability and Statistics for Engineers", 1, 25, null, null, null, "3 4 5")
            ));

            notificationRepository.saveAll(Arrays.asList(new Notification(0, "Motivational quote1", NotificationType.MOTIVATIONAL_QUOTE, instructorRepository.findById(4L).get(), null),
                    new Notification(0, "Its definitely not 1 am rn", NotificationType.MOTIVATIONAL_QUOTE, studentRepository.findById(1L).get(), null ),
                    new Notification(0, "You got covid are not allowed to enter the campus", NotificationType.COVID_NOTIFICATION, studentRepository.findById(1L).get(), "23/12/2021 18:20" )));

            //staffRepository.saveAll(Arrays.asList(
            //        new Staff(5L, "Staff1", "password", "staff1@ug.bilkent.edu.tr", "hedcode-h", "000", false, true, false, null, null)
            //));

            reservationRepository.saveAll(Arrays.asList(
                    new Reservation(10L, "10.12.2021", "10:30", "Study_Room_1", "library", studentRepository.findById(1L).get()),
                    new Reservation(10L, "10.12.2021", "16:00", "Study_Room_1", "library", studentRepository.findById(1L).get()),
                    new Reservation(10L, "10.12.2021", "22:00", "Study_Room_1", "library", studentRepository.findById(1L).get()),
                    new Reservation(20L, "11.12.2021", "10:30", "room1", "diagnovir", studentRepository.findById(1L).get())
            ));

            areaRepository.saveAll(Arrays.asList(
                    new Cafeteria("Sozeri", 3, 15),
                    new Cafeteria("Cafe-in", 20, 100),
                    new Cafeteria("Yemekhane", 187, 300),
                    new SmokingArea("B-1", 22),
                    new SmokingArea("B-2", 30)
            ));

            seatingPlanRepository.saveAll(Arrays.asList(
                    new SeatingPlan(0, 10, 20, null, null),
                    new SeatingPlan(0, 15, 20, null, null),
                    new SeatingPlan(0, 5, 5, null, null)
            ));


        };
    }

}
