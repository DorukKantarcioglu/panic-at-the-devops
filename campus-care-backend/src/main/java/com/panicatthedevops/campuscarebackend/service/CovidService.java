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
import org.springframework.stereotype.Service;

import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer regarding COVID status of the campus and statistics.
 * @version 1.0
 */

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

    /**
     * Gets the students which are not allowed on the campus
     * @return List of students with not allowed status
     */
    public List<Student> getNotAllowedStudents() {
        return studentRepository.findAll().stream().filter(s -> !s.isAllowedOnCampus()).collect(Collectors.toList());
    }

    /**
     * Gets the instructors which are not allowed on the campus
     * @return List of instructors with not allowed status
     */
    public List<Instructor> getNotAllowedInstructors() {
        return instructorRepository.findAll().stream().filter(s -> !s.isAllowedOnCampus()).collect(Collectors.toList());
    }

    /**
     * Gets the staffs which are not allowed on the campus
     * @return List of staff with not allowed status
     */
    public List<Staff> getNotAllowedStaffs() {
        return staffRepository.findAll().stream().filter(s -> !s.isAllowedOnCampus()).collect(Collectors.toList());
    }

    /**
     * Gets the students with not allowed status inside a certain course
     * @param courseCode Code of the course to look for
     * @return List of not allowed students inside the course with code
     * @throws CourseNotFoundException Course does not exist in the database
     */
    public List<Student> getNotAllowedStudentsInCourse(String courseCode) {
        if (courseRepository.existsById(courseCode)) {
            Course course = courseRepository.findById(courseCode).get();
            return course.getStudentList().stream().filter(s -> !s.isAllowedOnCampus()).collect(Collectors.toList());
        }
        else {
            throw new CourseNotFoundException("Course with code " + courseCode + " does not exist.");
        }
    }

    /**
     * Gets the students with not allowed status inside a certain cafeteria.
     * @param cafeteriaName Name of the cafeteria with the given name
     * @return  List of students in a cafeteria who are not allowed.
     * @throws AreaNotFoundException Area does not exist in the database
     */
    public List<Student> getNotAllowedStudentsInCafeteria(String cafeteriaName) {
        if (areaRepository.existsById(cafeteriaName)) {
            return studentRepository.findAll().stream().filter(s -> s.getSelectedCafeteria() != null && s.getSelectedCafeteria().equals(cafeteriaName) && !s.isAllowedOnCampus()).collect(Collectors.toList());
        }
        else {
            throw new AreaNotFoundException("Cafeteria " + cafeteriaName + " does not exist.");
        }
    }

    /**
     * Gets the students with not allowed status inside a certain smoking area.
     * @param smokingAreaName Name of the smoking area with the given name
     * @return  List of students in a smoking area who are not allowed.
     * @throws AreaNotFoundException Area does not exist in the database
     */
    public List<Student> getNotAllowedStudentsInSmokingArea(String smokingAreaName) {
        if (areaRepository.existsById(smokingAreaName)) {
            return studentRepository.findAll().stream().filter(s -> s.getSelectedSmokingArea() != null && s.getSelectedSmokingArea().equals(smokingAreaName) && !s.isAllowedOnCampus()).collect(Collectors.toList());
        }
        else {
            throw new AreaNotFoundException("Smoking area " + smokingAreaName + " does not exist.");
        }
    }

    /**
     * Routine method to validate HES codes daily. Updates the database for each user's HES code.
     * Authentication is required for E-Government with valid national id and password
     * @param trIdNumber E-Government ID number
     * @param eGovernmentPassword E-Government password
     * @return List of all users
     */
    public List<User> validateHesCodes(String trIdNumber, String eGovernmentPassword) {
        for (User user : userRepository.findAll()) {
            validateHesCode(user.getHesCode(), trIdNumber, eGovernmentPassword);
        }
        return userRepository.findAll();
    }

    /**
     * To get the HES status of user, this method authenticates to E-Devlet services
     * with the help of Selenium WebDriver. Hes code, TR Id number and E-Government passwords need to be valid.
     * @param hesCode HES code of the user to be validated.
     * @param trIdNumber TR Id number for E-Government
     * @param eGovernmentPassword Password to be used in E-Devlet authentication
     * @return User which the HES code belongs to
     * @throws EGovernmentAuthenticationFailedException Failed authentication to E-Devlet
     * @throws HesCodeNotValidException HES code is not valid/expired
     * @throws UserNotFoundException User with the given HES code does not exist
     */
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

    /**
     * Gets the number of not allowed users in the database
     * @return integer value of not allowed user count
     */
    public int getNotAllowedStatistics() {
        return (int) userRepository.findAll().stream().filter(user -> !user.isAllowedOnCampus()).count();
    }

    /**
     * Gets the number of vaccinated users in the database
     * @return integer value of vaccinated user count
     */
    public int getVaccinatedStatistics() {
        return (int) userRepository.findAll().stream().filter(User::isVaccinated).count();
    }

    /**
     * Gets the number of not vaccinated users in the database
     * @return integer value of not vaccinated user count
     */
    public int getNotVaccinatedStatistics() {
        return (int) userRepository.findAll().stream().filter(user -> !user.isVaccinated()).count();
    }

    /**
     * Gets the number of tested users in the last 48 hours in the database
     * @return integer value of tested users in the last 48 hours count
     */
    public int getTestedStatistics() {
        return (int) userRepository.findAll().stream().filter(User::isTested).count();
    }
}
