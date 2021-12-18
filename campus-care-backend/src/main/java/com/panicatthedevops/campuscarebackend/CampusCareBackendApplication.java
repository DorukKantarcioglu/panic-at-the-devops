package com.panicatthedevops.campuscarebackend;

import com.panicatthedevops.campuscarebackend.entity.Course;
import com.panicatthedevops.campuscarebackend.entity.Instructor;
import com.panicatthedevops.campuscarebackend.entity.Student;
import com.panicatthedevops.campuscarebackend.repository.CourseRepository;
import com.panicatthedevops.campuscarebackend.repository.InstructorRepository;
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
            StudentRepository studentRepository, InstructorRepository instructorRepository, CourseRepository courseRepository) {
        return args -> {
            studentRepository.saveAll(Arrays.asList(
                    new Student(1L, "Doruk", "doruk@campuscare.com", "DORUK-HES-CODE", true, true, false, null),
                    new Student(2L, "Yagmur", "yagmur@campuscare.com", "YAGMUR-HES-CODE", true, false, true, null),
                    new Student(3L, "Oguz", "oguz@campuscare.com", "OGUZ-HES-CODE", false, true, false, null),
                    new Student(4L, "Elif", "elif@campuscare.com", "ELIF-HES-CODE", true, true, false, null),
                    new Student(5L, "Suleyman", "suleyman@campuscare.com", "SULO-HES-CODE", true, true, true, null)
            ));
            instructorRepository.saveAll(Arrays.asList(
                    new Instructor(1L, "Eray Tuzun", "tuzun@campuscare.com", "TUZUN-HES-CODE", null),
                    new Instructor(2L, "Karani Kardas", "kardas@campuscare.com", "KARDAS-HES-CODE", null),
                    new Instructor(3L, "Dilek Koksal", "koksal@campuscare.com", "KOKSAL-HES-CODE", null)
            ));
            courseRepository.saveAll(Arrays.asList(
                    new Course("CS-319", "Object-Oriented Software Engineering", 50, instructorRepository.findById(1L).get(), null),
                    new Course("CS-315", "Programming Languages", 50, instructorRepository.findById(2L).get(), null),
                    new Course("MATH-230", "Probability and Statistics for Engineers", 25, instructorRepository.findById(3L).get(), null)
            ));
        };
    }
}
