package com.panicatthedevops.campuscarebackend;

import com.panicatthedevops.campuscarebackend.entity.*;
import com.panicatthedevops.campuscarebackend.repository.*;
import com.panicatthedevops.campuscarebackend.util.TimeSlot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class CampusCareBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(CampusCareBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner lineRunner(
            StudentRepository studentRepository, InstructorRepository instructorRepository, CourseRepository courseRepository, StaffRepository staffRepository, ReservationRepository reservationRepository, AreaRepository areaRepository, NotificationRepository notificationRepository) {
        return args -> {
            studentRepository.saveAll(Arrays.asList(
                    new Student(1L, "Doruk", "password", "doruk@campuscare.com", "DORUK-HES-CODE", "num", true, true, false, null, null, null, null, null)
            ));
            instructorRepository.saveAll(Arrays.asList(
                    new Instructor(2L, "Eray Tuzun", "passwrd", "tuzun@campuscare.com", "TUZUN-HES-CODE", "+90", true, true, false , null, null, null),
                    new Instructor(12L, "David", "passwrd", "david@campuscare.com", "David-HES-CODE", "+90", true, true, false , null, null, null)
            ));
            courseRepository.saveAll(Arrays.asList(
                    new Course("CS-319-1", "Object-Oriented Software Engineering", 1, 50, instructorRepository.findById(2L).get(), null),
                    new Course("CS-315-1", "Programming Languages",1, 50, instructorRepository.findById(12L).get(), null),
                    new Course("MATH-230-1", "Probability and Statistics for Engineers", 1, 25, null, null)
            ));

            notificationRepository.saveAll(Arrays.asList(new Notification(0, "Motivational quote1", "motivational-quote", instructorRepository.findById(2L).get()),
                    new Notification(0, "Its definitely not 1 am rn", "motivational-quote", studentRepository.findById(1L).get() ),
                    new Notification(0, "You got covid are not allowed to enter the campus", "covid-notification", studentRepository.findById(1L).get() )));

            staffRepository.saveAll(Arrays.asList(
                    new Staff(4L, "Staff1", "password", "staff1@ug.bilkent.edu.tr", "hedcode-h", "000", true, true, false, null, null)
            ));

            reservationRepository.saveAll(Arrays.asList(
                    new Reservation(10L, "10.12.2021", new TimeSlot("10:30", "Tuseday"), "Strudy room - 2", "library-study-room", studentRepository.findById(1L).get()),
                    new Reservation(20L, "11.12.2021", new TimeSlot("10:30", "Friday"), "Diagnovir room 1", "diagnovir", studentRepository.findById(1L).get())
            ));

            areaRepository.saveAll(Arrays.asList(
                    new Cafeteria("Sözeri", 3, 15),
                    new Cafeteria("Cafe-in", 20, 100),
                    new Cafeteria("Yemekhane", 187, 300),
                    new SmokingArea("B-1", 22),
                    new SmokingArea("B-2", 30)
            ));
        };
    }

}
