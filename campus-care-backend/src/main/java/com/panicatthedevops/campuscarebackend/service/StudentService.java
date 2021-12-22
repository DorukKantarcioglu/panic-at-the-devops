package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.Cafeteria;
import com.panicatthedevops.campuscarebackend.entity.SmokingArea;
import com.panicatthedevops.campuscarebackend.entity.Student;
import com.panicatthedevops.campuscarebackend.exception.*;
import com.panicatthedevops.campuscarebackend.repository.AreaRepository;
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
    private final AreaRepository areaRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, AreaRepository areaRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.areaRepository = areaRepository;
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

    public Student updateSelectedCafeteria(Long id, String cafeteriaName) {
        if (studentRepository.existsById(id)) {
            if (areaRepository.existsById(cafeteriaName)) {
                Student student = studentRepository.findById(id).get();
                if (student.getSelectedCafeteria() == null) {
                    Cafeteria cafeteria = (Cafeteria)areaRepository.findById(cafeteriaName).get();
                    cafeteria.incrementLiveCount();
                    areaRepository.save(cafeteria);
                    student.setSelectedCafeteria(cafeteriaName);
                    return studentRepository.save(student);
                }
                else if (!student.getSelectedCafeteria().equals(cafeteriaName)) {
                    Cafeteria oldCafeteria = (Cafeteria)areaRepository.findById(student.getSelectedCafeteria()).get();
                    oldCafeteria.decrementLiveCount();
                    areaRepository.save(oldCafeteria);
                    Cafeteria newCafeteria = (Cafeteria)areaRepository.findById(cafeteriaName).get();
                    newCafeteria.incrementLiveCount();
                    areaRepository.save(newCafeteria);
                    student.setSelectedCafeteria(cafeteriaName);
                    return studentRepository.save(student);
                }
                else {
                    throw new StudentAlreadyInAreaException("Student's cafeteria is already " + cafeteriaName + ".");
                }
            }
            else {
                throw new AreaNotFoundException("Cafeteria with name " + cafeteriaName + " does not exist.");
            }
        }
        else {
            throw new StudentNotFoundException("Student with id " + id + " does not exist.");
        }
    }

    public Student updateSelectedSmokingArea(Long id, String smokingAreaName) {
        if (studentRepository.existsById(id)) {
            if (areaRepository.existsById(smokingAreaName)) {
                Student student = studentRepository.findById(id).get();
                if (student.getSelectedSmokingArea() == null) {
                    SmokingArea smokingArea = (SmokingArea)areaRepository.findById(smokingAreaName).get();
                    smokingArea.incrementLiveCount();
                    areaRepository.save(smokingArea);
                    student.setSelectedSmokingArea(smokingAreaName);
                    return studentRepository.save(student);
                }
                else if (!student.getSelectedSmokingArea().equals(smokingAreaName)) {
                    SmokingArea oldSmokingArea = (SmokingArea) areaRepository.findById(student.getSelectedSmokingArea()).get();
                    oldSmokingArea.decrementLiveCount();
                    areaRepository.save(oldSmokingArea);
                    SmokingArea newSmokingArea = (SmokingArea) areaRepository.findById(smokingAreaName).get();
                    newSmokingArea.incrementLiveCount();
                    areaRepository.save(newSmokingArea);
                    student.setSelectedSmokingArea(smokingAreaName);
                    return studentRepository.save(student);
                }
                else {
                    throw new StudentAlreadyInAreaException("Student's smoking area is already " + smokingAreaName + ".");
                }
            }
            else {
                throw new AreaNotFoundException("Smoking area with name " + smokingAreaName + " does not exist.");
            }
        }
        else {
            throw new StudentNotFoundException("Student with id " + id + " does not exist.");
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
            Student student = studentRepository.findById(id).get();
            student.setHesCode(hesCode);
            return studentRepository.save(student);
        }
        else {
            throw new StudentNotFoundException("Student with id " + id + " does not exist.");
        }
    }

    public Student addCourse(Long id, String courseCode) {
        if (studentRepository.existsById(id)) {
            Student student = studentRepository.findById(id).get();
            if (courseRepository.existsByCourseCode(courseCode)) {
                if (student.getCoursesTaken().contains(courseRepository.findByCourseCode(courseCode).iterator().next())) {
                    throw new StudentAlreadyEnrolledToTheCourseException(
                            "Student with id" + id + "is already enrolled to the course with code " + courseCode + ".");
                }
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
            Student student = studentRepository.findById(id).get();
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
