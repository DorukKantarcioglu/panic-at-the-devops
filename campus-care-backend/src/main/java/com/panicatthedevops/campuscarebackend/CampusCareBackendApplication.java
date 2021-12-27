package com.panicatthedevops.campuscarebackend;

import com.panicatthedevops.campuscarebackend.entity.*;
import com.panicatthedevops.campuscarebackend.repository.*;
import com.panicatthedevops.campuscarebackend.service.*;
import com.panicatthedevops.campuscarebackend.util.NotificationType;
import com.panicatthedevops.campuscarebackend.util.ReservationInformation;
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
            CourseService courseService, SeatingPlanService seatingPlanService, SeatingObjectService seatingObjectService, ReservationService reservationService, StudentService studentService,
            ScheduledService scheduledService, NotificationService notificationService,
            ReservationRepository reservationRepository, AreaRepository areaRepository, NotificationRepository notificationRepository, SeatingPlanRepository seatingPlanRepository) {
        return args -> {
            studentRepository.saveAll(Arrays.asList(
                    new Student(21902319L, "Doruk Kantarcıoğlu", new BCryptPasswordEncoder().encode("doruk-password"), "doruk@campuscare.com", "F4K2-1836-10", "num", false, false, false, null, null, null, null, null, null),
                    new Student(21903087L, "Oğuz Ata Çal", new BCryptPasswordEncoder().encode("password"), "oğuz@campuscare.com", "Oğuz-HES-CODE", "num", true, true, false, null, null, null, null, null, null),
                    new Student(21903223L, "Yağmur Eryılmaz", new BCryptPasswordEncoder().encode("yagmur-password"), "yağmur@campuscare.com", "C6D9-3181-14", "num", true, true, false, null, null, null, null, null, null),
                    new Student(21902460L, "Elif Çenesiz", new BCryptPasswordEncoder().encode("password"), "elif@campuscare.com", "A6A2-1832-17", "num", false, true, false, null, null, null, null, null, null),
                    new Student(21901008L, "Suleyman Hanyyev", new BCryptPasswordEncoder().encode("password"), "suleyman@campuscare.com", "B4V3-8549-14", "num", true, true, false, null, null, null, null, null, null)
            ));
            instructorRepository.saveAll(Arrays.asList(
                    new Instructor(4L, "Eray Tuzun", new BCryptPasswordEncoder().encode("a"), "tuzun@campuscare.com", "B4V3-8549-14", "+90", true, true, false , null, null, null),
                    new Instructor(12L, "Karani Kardaş", new BCryptPasswordEncoder().encode("karani-password"), "karani@campuscare.com", "A6A2-1832-17", "+90", true, true, false , null, null, null),
                    new Instructor(99L, "Dilek Köksal", new BCryptPasswordEncoder().encode("karani-password"), "dilek@campuscare.com", "B6A2-1832-17", "+90", true, true, false , null, null, null)
            ));
            courseRepository.saveAll(Arrays.asList(
                    new Course("CS-319-1", "Object-Oriented Software Engineering", 1, 50, instructorRepository.findById(4L).get(), null, null, "1 2 5"),
                    new Course("CS-319-2", "Object-Oriented Software Engineering", 2, 50, instructorRepository.findById(4L).get(), null, null, "1 2 5"),
                    new Course("CS-319-3", "Object-Oriented Software Engineering", 3, 50, instructorRepository.findById(4L).get(), null, null, "1 2 5"),
                    new Course("CS-315-1", "Programming Languages",1, 50, instructorRepository.findById(12L).get(), null, null, "1 4 5"),
                    new Course("CS-315-2", "Programming Languages",1, 50, instructorRepository.findById(12L).get(), null, null, "1 4 5"),
                    new Course("MATH-230-1", "Probability and Statistics for Engineers", 1, 25, instructorRepository.findById(99L).get(), null, null, "3 4 5")
            ));

            notificationRepository.saveAll(Arrays.asList(new Notification(0, "Work hard, stay positive, and get up early. It's the best part of the day. George Allen, Sr", NotificationType.MOTIVATIONAL_QUOTE,null, null),
                    new Notification(0, "My optimism wears heavy boots and is loud –Henry Rollins", NotificationType.MOTIVATIONAL_QUOTE, null, null ),
                    new Notification(0, "You are covid positive and not allowed to enter the campus", NotificationType.COVID_NOTIFICATION, studentRepository.findById(21902319L).get(), "27/12/2021 18:20" )));

            //staffRepository.saveAll(Arrays.asList(
            //        new Staff(5L, "Staff1", "password", "staff1@ug.bilkent.edu.tr", "hedcode-h", "000", false, true, false, null, null)
            //));

   //         reservationRepository.saveAll(Arrays.asList(
   //                 new Reservation(10L, "10.12.2021", "10:30", "Study_Room_1", "library", studentRepository.findById(1L).get()),
   //                 new Reservation(10L, "10.12.2021", "16:00", "Study_Room_1", "library", studentRepository.findById(1L).get()),
   //                 new Reservation(10L, "10.12.2021", "22:00", "Study_Room_1", "library", studentRepository.findById(1L).get()),
   //                 new Reservation(20L, "11.12.2021", "10:30", "room1", "diagnovir", studentRepository.findById(1L).get())
   //         ));

            areaRepository.saveAll(Arrays.asList(
                    new Cafeteria("Sozeri", 3, 15),
                    new Cafeteria("Cafe-in", 20, 100),
                    new Cafeteria("Yemekhane", 187, 300),
                    new SmokingArea("B-1", 22),
                    new SmokingArea("B-2", 30)
            ));

            seatingPlanRepository.saveAll(Arrays.asList(
                    new SeatingPlan(0, 7, 10, null, null),
                    new SeatingPlan(0, 6, 9, null, null),
                    new SeatingPlan(0, 7, 10, null, null),
                    new SeatingPlan(0, 7, 9, null, null),
                    new SeatingPlan(0, 6, 10, null, null),
                    new SeatingPlan(0, 6, 10, null, null)
            ));

            // assigning seating plans to courses
            courseService.addSeatingPlan(4L, "CS-319-1");
            //courseService.addSeatingPlan(9L, "CS-319-2"); no seating plan for section 2
            courseService.addSeatingPlan(6L, "CS-319-3");
            courseService.addSeatingPlan(7L, "CS-315-1");
            courseService.addSeatingPlan(8L, "CS-315-2");
            courseService.addSeatingPlan(9L, "MATH-230-1");

            // enrolling students to courses
            studentService.addCourse(21902319L, "CS-319-1");
            studentService.addCourse(21903087L, "CS-319-1");
            studentService.addCourse(21903223L, "CS-319-1");
            studentService.addCourse(21902460L, "CS-319-1");
            studentService.addCourse(21901008L, "CS-319-1");

            studentService.addCourse(21902319L, "CS-315-1");
            studentService.addCourse(21903087L, "CS-315-1");
            studentService.addCourse(21903223L, "CS-315-2");
            studentService.addCourse(21902460L, "CS-315-2");
            studentService.addCourse(21901008L, "CS-315-2");

            studentService.addCourse(21902319L, "MATH-230-1");
            studentService.addCourse(21903087L, "MATH-230-1");
            studentService.addCourse(21903223L, "MATH-230-1");
            studentService.addCourse(21902460L, "MATH-230-1");
            studentService.addCourse(21901008L, "MATH-230-1");

            // assigning seating plans

            // for CS-319-1
            seatingObjectService.save(5, 5, 4L, 21902319L);
            seatingObjectService.save(5, 6, 4L, 21903087L);
            seatingObjectService.save(6, 5, 4L, 21903223L);
            seatingObjectService.save(6, 6, 4L, 21902460L);
            seatingObjectService.save(6, 7, 4L, 21901008L);

            // for CS-315-1
            seatingObjectService.save(3, 4, 7L, 21902319L);
            seatingObjectService.save(3, 5, 7L, 21903087L);
            seatingObjectService.save(3, 6, 7L, 21903223L);

            // for CS-315-2
            seatingObjectService.save(4, 4, 8L, 21902460L);
            seatingObjectService.save(4, 6, 8L, 21901008L);
            System.out.println(courseService.findAll());

            // for MATH-230-1
            seatingObjectService.save( 1, 4, 9L, 21902319L);
            seatingObjectService.save( 2, 3, 9L, 21903087L);
            seatingObjectService.save( 2, 5, 9L, 21903223L);
            seatingObjectService.save( 2, 6, 9L, 21902460L);
            seatingObjectService.save( 6, 7, 9L, 21901008L);



            // adding reservations
            reservationService.save(21902319L, "29.12.2021", "10:30", "room1", "diagnovir");
            reservationService.save(21903087L, "29.12.2021", "12:30", "Study_Room_3", "library");
            reservationService.save(21903087L, "29.12.2021", "20:30", "Table_Tennis1", "sports_center");
            reservationService.save(21903223L, "30.12.2021", "10:30", "Study_Room_1", "library");
            reservationService.save(21903223L, "29.12.2021", "12:00", "Study_Room_4", "library");
            reservationService.save(4L, "30.12.2021", "15:30", "Indoor_Basketball", "sports_center");

            scheduledService.notifyInstructors();
            scheduledService.notifyNearbyStudents();

            notificationService.saveMotivationalQuote("Live life to the fullest, focus on the positive-Matt Cameron");
            notificationService.saveMotivationalQuote("Delete the negative; accentuate the positive-Donna Karan");

        };
    }

}
