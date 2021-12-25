package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.*;
import com.panicatthedevops.campuscarebackend.exception.*;
import com.panicatthedevops.campuscarebackend.repository.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CovidService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final StaffRepository staffRepository;
    private final CourseRepository courseRepository;
    private final AreaRepository areaRepository;

    @Autowired
    public CovidService(UserRepository userRepository, StudentRepository studentRepository, InstructorRepository instructorRepository, StaffRepository staffRepository, CourseRepository courseRepository, AreaRepository areaRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
        this.staffRepository = staffRepository;
        this.courseRepository = courseRepository;
        this.areaRepository = areaRepository;
    }

    public List<Student> getNotAllowedStudents() {
        return studentRepository.findAll().stream().filter(s -> !s.isAllowedOnCampus()).collect(Collectors.toList());
    }

    public List<Instructor> getNotAllowedInstructors() {
        return instructorRepository.findAll().stream().filter(s -> !s.isAllowedOnCampus()).collect(Collectors.toList());
    }

    public List<Staff> getNotAllowedStaffs() {
        return staffRepository.findAll().stream().filter(s -> !s.isAllowedOnCampus()).collect(Collectors.toList());
    }

    public List<Student> getNotAllowedStudentsInCourse(String courseCode) {
        if (courseRepository.existsById(courseCode)) {
            Course course = courseRepository.findById(courseCode).get();
            return course.getStudentList().stream().filter(s -> !s.isAllowedOnCampus()).collect(Collectors.toList());
        }
        else {
            throw new CourseNotFoundException("Course with code " + courseCode + " does not exist.");
        }
    }

    public List<Student> getNotAllowedStudentsInCafeteria(String cafeteriaName) {
        if (areaRepository.existsById(cafeteriaName)) {
            return studentRepository.findAll().stream().filter(s -> s.getSelectedCafeteria() != null && s.getSelectedCafeteria().equals(cafeteriaName) && !s.isAllowedOnCampus()).collect(Collectors.toList());
        }
        else {
            throw new AreaNotFoundException("Cafeteria " + cafeteriaName + " does not exist.");
        }
    }

    public List<Student> getNotAllowedStudentsInSmokingArea(String smokingAreaName) {
        if (areaRepository.existsById(smokingAreaName)) {
            return studentRepository.findAll().stream().filter(s -> s.getSelectedSmokingArea() != null && s.getSelectedSmokingArea().equals(smokingAreaName) && !s.isAllowedOnCampus()).collect(Collectors.toList());
        }
        else {
            throw new AreaNotFoundException("Smoking area " + smokingAreaName + " does not exist.");
        }
    }

    public List<User> validateHesCodes(String trIdNumber, String eGovernmentPassword) {
        for (User user : userRepository.findAll()) {
            validateHesCode(user.getHesCode(), trIdNumber, eGovernmentPassword);
        }
        return userRepository.findAll();
    }

    public User validateHesCode(String hesCode, String trIdNumber, String eGovernmentPassword) {
        if (userRepository.existsByHesCode(hesCode)) {
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
            String query = webElements.iterator().next().getText();
            LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(query));
            User user = userRepository.findByHesCode(hesCode).iterator().next();
            try {
                String line = lineNumberReader.readLine();
                while (line != null) {
                    if (lineNumberReader.getLineNumber() == 10) {
                        user.setAllowedOnCampus(line.equals("Riskli Değil"));
                    }
                    else if (lineNumberReader.getLineNumber() == 12) {
                        user.setVaccinated(line.equals("Evet"));
                    }
                    else if (lineNumberReader.getLineNumber() == 16) {
                        user.setTested(line.equals("Evet"));
                    }
                    line = lineNumberReader.readLine();
                }
                lineNumberReader.close();
            }
            catch (Exception e) {
                throw new RuntimeException("Something went wrong with LineNumberReader.");
            }
            driver.quit();
            return userRepository.save(user);
        }
        else {
            throw new UserNotFoundException("User with HES code " + hesCode + " does not exist.");
        }
    }

    public int getNotAllowedStatistics() {
        return (int) userRepository.findAll().stream().filter(user -> !user.isAllowedOnCampus()).count();
    }

    public int getVaccinatedStatistics() {
        return (int) userRepository.findAll().stream().filter(User::isVaccinated).count();
    }

    public int getNotVaccinatedStatistics() {
        return (int) userRepository.findAll().stream().filter(user -> !user.isVaccinated()).count();
    }

    public int getTestedStatistics() {
        return (int) userRepository.findAll().stream().filter(User::isTested).count();
    }
}
