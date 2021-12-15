package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.Student;
import com.panicatthedevops.campuscarebackend.exception.*;
import com.panicatthedevops.campuscarebackend.repository.CourseRepository;
import com.panicatthedevops.campuscarebackend.repository.StudentRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(()
                -> new StudentNotFoundException("Student with id " + id + " does not exist."));
    }

    public Student findByHesCode(String hesCode) {
        if (studentRepository.findByHesCode(hesCode).isEmpty()) {
            throw new StudentNotFoundException("Student with HES code " + hesCode + " does not exist.");
        }
        else {
            return studentRepository.findByHesCode(hesCode).iterator().next();
        }
    }

    public Student save(Student student) {
        if (studentRepository.existsById(student.getId())) {
            throw new StudentAlreadyExistsException("Student with id " + student.getId() + " already exists.");
        }
        else {
            return studentRepository.save(student);
        }
    }

    public Student updateHesCode(Long id, String hesCode) {
        if (studentRepository.existsById(id)) {
            if (studentRepository.existsByHesCode(hesCode)) {
                throw new HesCodeAlreadyExistsException("HES code " + hesCode + " belongs to another student.");
            }
            Student student = studentRepository.getById(id);
            student.setHesCode(hesCode);
            return studentRepository.save(student);
        }
        else {
            throw new StudentNotFoundException("Student with id " + id + " does not exist.");
        }
    }

    public Student addCourse(Long id, String courseCode) {
        if (studentRepository.existsById(id)) {
            Student student = studentRepository.getById(id);
            if (courseRepository.existsByCourseCode(courseCode)) {
                student.addCourse(courseRepository.findByCourseCode(courseCode).iterator().next());
                return studentRepository.save(student);
            }
            else {
                throw new CourseNotFoundException("Course with code " + courseCode + " does not exist.");
            }
        }
        else {
            throw new StudentNotFoundException("Student with id " + id + " does not exist.");
        }
    }

    public Student removeCourse(Long id, String courseCode) {
        if (studentRepository.existsById(id)) {
            Student student = studentRepository.getById(id);
            if (courseRepository.existsByCourseCode(courseCode)) {
                student.removeCourse(courseRepository.findByCourseCode(courseCode).iterator().next());
                return studentRepository.save(student);
            }
            else {
                throw new CourseNotFoundException("Course with code " + courseCode + " does not exist.");
            }
        }
        else {
            throw new StudentNotFoundException("Student with id " + id + " does not exist.");
        }
    }

    public void deleteById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        }
        else {
            throw new StudentNotFoundException("Student with id " + id + " does not exist.");
        }
    }

    public String validateHesCode(String hesCode, String trIdNumber, String eGovernmentPassword) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://giris.turkiye.gov.tr/Giris/gir");
        driver.findElement(By.id("tridField")).sendKeys(trIdNumber);
        driver.findElement(By.id("egpField")).sendKeys(eGovernmentPassword);
        driver.findElement(By.name("submitButton")).click();
        driver.get("https://www.turkiye.gov.tr/saglik-bakanligi-hes-kodu-sorgulama");
        try {
            driver.findElement(By.id("hes_kodu")).sendKeys(hesCode);
        }
        catch (NoSuchElementException e) {
            throw new EGovernmentAuthenticationFailedException("Credentials for E-Government authentication are wrong.");
        }
        driver.findElement(By.className("actionButton")).click();
        driver.get("https://www.turkiye.gov.tr/saglik-bakanligi-hes-kodu-sorgulama?sonuc=Goster");
        List<WebElement> webElements = driver.findElements(By.xpath("//dl"));
        if (webElements.isEmpty()) {
            throw new HesCodeNotValidException("HES code " + hesCode + " is not valid.");
        }
        return webElements.iterator().next().getText();
    }
}
