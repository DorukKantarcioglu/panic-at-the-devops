package com.panicatthedevops.pandemicmanager.service;

import com.panicatthedevops.pandemicmanager.entity.Student;
import com.panicatthedevops.pandemicmanager.exception.*;
import com.panicatthedevops.pandemicmanager.repository.StudentRepository;
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

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
