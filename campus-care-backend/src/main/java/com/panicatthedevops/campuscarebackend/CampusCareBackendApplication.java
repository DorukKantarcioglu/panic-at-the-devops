package com.panicatthedevops.campuscarebackend;

import com.panicatthedevops.campuscarebackend.entity.Course;
import com.panicatthedevops.campuscarebackend.entity.Instructor;
import com.panicatthedevops.campuscarebackend.entity.Staff;
import com.panicatthedevops.campuscarebackend.entity.Student;
import com.panicatthedevops.campuscarebackend.repository.CourseRepository;
import com.panicatthedevops.campuscarebackend.repository.InstructorRepository;
import com.panicatthedevops.campuscarebackend.repository.StaffRepository;
import com.panicatthedevops.campuscarebackend.repository.StudentRepository;
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
            StudentRepository studentRepository, InstructorRepository instructorRepository, CourseRepository courseRepository, StaffRepository staffRepository) {
        return args -> {
            studentRepository.saveAll(Arrays.asList(
                    new Student(1L, "Doruk", "password", "doruk@campuscare.com", "DORUK-HES-CODE", "num", true, true, false, null, null, null)
            ));
            instructorRepository.saveAll(Arrays.asList(
                    new Instructor(2L, "Eray Tuzun", "passwrd", "tuzun@campuscare.com", "TUZUN-HES-CODE", "+90", true, true, false , null, null, null)
            ));
            courseRepository.saveAll(Arrays.asList(
                    new Course("CS-319-1", "Object-Oriented Software Engineering", 1, 50, instructorRepository.findById(2L).get(), null),
                    new Course("CS-315-1", "Programming Languages",1, 50, null, null),
                    new Course("MATH-230-1", "Probability and Statistics for Engineers", 1, 25, null, null)
            ));

            staffRepository.saveAll(Arrays.asList(
                    new Staff(4L, "Staff1", "password", "staff1@ug.bilkent.edu.tr", "hedcode-h", "000", true, true, false, null, null)
            ));
        };
    }

}
