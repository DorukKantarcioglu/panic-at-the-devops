package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.*;
import com.panicatthedevops.campuscarebackend.exception.*;
import com.panicatthedevops.campuscarebackend.repository.*;
import com.panicatthedevops.campuscarebackend.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final AreaRepository areaRepository;
    private final SeatingObjectRepository seatingObjectRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository,
                          AreaRepository areaRepository, SeatingObjectRepository seatingObjectRepository, SeatingPlanRepository seatingPlanRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.areaRepository = areaRepository;
        this.seatingObjectRepository = seatingObjectRepository;
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

    public List<Student> findNearbyStudents(Long studentId){
        if(!studentRepository.existsById(studentId)){
            throw new StudentNotFoundException("Student with id " + studentId + " does not exist.");
        }

        Student student = findById(studentId);
        List<Student> nearbyStudents = new ArrayList<>();
        for( SeatingObject seatingObject : student.getSeatings()){
            Student nearbyStudent;
            if(seatingObject.getRowNo() > 0 && seatingObjectRepository.existsByRowNoAndColumnNoAndSeatingPlanId(seatingObject.getRowNo()-1, seatingObject.getColumnNo(), seatingObject.getSeatingPlan().getId())){
                nearbyStudent = seatingObjectRepository.findByRowNoAndColumnNoAndSeatingPlanId(seatingObject.getRowNo()-1, seatingObject.getColumnNo(), seatingObject.getSeatingPlan().getId()).getStudent();
                if(!nearbyStudents.contains(nearbyStudent))
                    nearbyStudents.add(nearbyStudent);
            }
            if(seatingObject.getRowNo() < seatingObject.getSeatingPlan().getRowNumber() && seatingObjectRepository.existsByRowNoAndColumnNoAndSeatingPlanId(seatingObject.getRowNo()+1, seatingObject.getColumnNo(), seatingObject.getSeatingPlan().getId())){
                nearbyStudent = seatingObjectRepository.findByRowNoAndColumnNoAndSeatingPlanId(seatingObject.getRowNo()+1, seatingObject.getColumnNo(), seatingObject.getSeatingPlan().getId()).getStudent();
                if(!nearbyStudents.contains(nearbyStudent))
                    nearbyStudents.add(nearbyStudent);
            }
            if(seatingObject.getColumnNo() > 0 && seatingObjectRepository.existsByRowNoAndColumnNoAndSeatingPlanId(seatingObject.getRowNo(), seatingObject.getColumnNo()-1, seatingObject.getSeatingPlan().getId())){
                nearbyStudent = seatingObjectRepository.findByRowNoAndColumnNoAndSeatingPlanId(seatingObject.getRowNo(), seatingObject.getColumnNo() - 1, seatingObject.getSeatingPlan().getId()).getStudent();
                if(!nearbyStudents.contains(nearbyStudent))
                    nearbyStudents.add(nearbyStudent);
            }
            if(seatingObject.getColumnNo() < seatingObject.getSeatingPlan().getColumnNumber() && seatingObjectRepository.existsByRowNoAndColumnNoAndSeatingPlanId(seatingObject.getRowNo(), seatingObject.getColumnNo()+1, seatingObject.getSeatingPlan().getId()) ){
                nearbyStudent = seatingObjectRepository.findByRowNoAndColumnNoAndSeatingPlanId(seatingObject.getRowNo(), seatingObject.getColumnNo() + 1, seatingObject.getSeatingPlan().getId()).getStudent();
                if(!nearbyStudents.contains(nearbyStudent))
                    nearbyStudents.add(nearbyStudent);
            }
        }
        return nearbyStudents;
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

    public Student removeSelectedCafeteria(Long id) {
        if (studentRepository.existsById(id)) {
            Student student = studentRepository.findById(id).get();
            if (student.getSelectedCafeteria() != null) {
                Cafeteria cafeteria = (Cafeteria) areaRepository.findById(student.getSelectedCafeteria()).get();
                cafeteria.decrementLiveCount();
                areaRepository.save(cafeteria);
                student.setSelectedCafeteria(null);
                return studentRepository.save(student);
            }
            else {
                throw new StudentNotInAnyAreaException("Student with id " + id + " is not in any cafeteria.");
            }
        }
        else {
            throw new StudentNotFoundException("Student with id " + id + " does not exist.");
        }
    }

    public Student removeSelectedSmokingArea(Long id) {
        if (studentRepository.existsById(id)) {
            Student student = studentRepository.findById(id).get();
            if (student.getSelectedSmokingArea() != null) {
                SmokingArea smokingArea = (SmokingArea) areaRepository.findById(student.getSelectedSmokingArea()).get();
                smokingArea.decrementLiveCount();
                areaRepository.save(smokingArea);
                student.setSelectedSmokingArea(null);
                return studentRepository.save(student);
            }
            else {
                throw new StudentNotInAnyAreaException("Student with id " + id + " is not in any smoking area.");
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
            student.setPassword(WebSecurityConfig.passwordEncoder().encode(student.getPassword()));
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

    public Student updatePhoneNumber(Long id, String phoneNumber) {
        if (studentRepository.existsById(id)) {
            Student student = studentRepository.findById(id).get();
            student.setPhoneNumber(phoneNumber);
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
}
