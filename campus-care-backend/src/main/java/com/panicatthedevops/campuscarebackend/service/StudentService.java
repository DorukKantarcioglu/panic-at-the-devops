package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.Cafeteria;
import com.panicatthedevops.campuscarebackend.entity.SeatingObject;
import com.panicatthedevops.campuscarebackend.entity.SmokingArea;
import com.panicatthedevops.campuscarebackend.entity.Student;
import com.panicatthedevops.campuscarebackend.exception.*;
import com.panicatthedevops.campuscarebackend.repository.*;
import com.panicatthedevops.campuscarebackend.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer regarding Student-related operations.
 * @version 1.0
 */

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final AreaRepository areaRepository;
    private final SeatingObjectRepository seatingObjectRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository,
                          AreaRepository areaRepository, SeatingObjectRepository seatingObjectRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.areaRepository = areaRepository;
        this.seatingObjectRepository = seatingObjectRepository;
    }

    /**
     * Used to get all registered students in the database.
     * @return all students in the system.
     */
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    /**
     * Searches the database for the student with given id
     * @param id Id of the student
     * @return Student with the given id
     * @throws StudentNotFoundException When the student is not on the database
     */
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(()
                -> new StudentNotFoundException("Student with id " + id + " does not exist."));
    }

    /**
     * Searches the database for the student with given hes code
     * @param hesCode Hes code of the student
     * @return Student with the given hes code
     * @throws StudentNotFoundException When the student is not on the database
     */
    public Student findByHesCode(String hesCode) {
        if (studentRepository.findByHesCode(hesCode).isEmpty()) {
            throw new StudentNotFoundException("Student with HES code " + hesCode + " does not exist.");
        }
        else {
            return studentRepository.findByHesCode(hesCode).iterator().next();
        }
    }

    /**
     * finds the students sitting next to, behind and in front of the student with the specified id in all seating plans and returns the list without duplicates
     * @param studentId id of student
     * @throws StudentNotFoundException student wasnt found in the repository
     * @return list of students
     */
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

    /**
     * updates the selected cafeteria attribute of student managing the live count of the area
     * @param id student id
     * @param cafeteriaName cafeteria name
     * @throws StudentAlreadyInAreaException student is already in are that is trying to be updated
     * @throws AreaNotFoundException area was not found in the repository
     * @throws StudentNotFoundException student was not found in the repository
     * @return student object with id id
     */
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

    /**
     * updates the selected smoking area attribute of student managing the live count of the area
     * @param id student id
     * @param smokingAreaName cafeteria name
     * @throws StudentAlreadyInAreaException student is already in are that is trying to be updated
     * @throws AreaNotFoundException area was not found in the repository
     * @throws StudentNotFoundException student was not found in the repository
     * @return student object with id id
     */
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

    /**
     * removes the selected cafeteria from users attributes and also decreases live count
     * @param id  student id
     * @throws StudentNotInAnyAreaException student isnt in an area so it cannot be removed
     * @throws StudentNotFoundException student wasnt found in the repository
     * @return student instance with id id
     */
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

    /**
     * removes the selected smoking area from users attributes and also decreases live count
     * @param id  student id
     * @throws StudentNotInAnyAreaException student isnt in an area so it cannot be removed
     * @throws StudentNotFoundException student wasnt found in the repository
     * @return student instance with id id
     */
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

    /**
     * saves a student object to the repository
     * @param student student that is to be saved in the repository
     * @throws StudentAlreadyExistsException student with id already exists in repository
     * @return student isntance that was saved
     */
    public Student save(Student student) {
        if (studentRepository.existsById(student.getId())) {
            throw new StudentAlreadyExistsException("Student with id " + student.getId() + " already exists.");
        }
        else {
            student.setPassword(WebSecurityConfig.passwordEncoder().encode(student.getPassword()));
            return studentRepository.save(student);
        }
    }

    /**
     * changes the hes code of the student
     * @param id student id
     * @param hesCode hes code
     * @throws HesCodeAlreadyExistsException a student with the same hes code already exists in the repository
     * @throws StudentNotFoundException student wasnt found in the repository
     * @return student instance
     */
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


    /**
     * @param id student id
     * @param phoneNumber student phone number
     * @throws StudentNotFoundException student with id already exists in the repository
     * @return student instance
     */
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

    /**
     * enrolling a student to a course
     * @param id student id
     * @param courseCode course code
     * @throws StudentAlreadyEnrolledToTheCourseException student is already enrolled to the course
     * @throws StudentNotFoundException student wasnt found in the repository
     * @throws CourseNotFoundException course wasnt found in the repository
     * @return student instance
     */
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

    /**
     * removes a course from student
     * @param id student id
     * @param courseCode course code
     * @throws CourseNotFoundException course wasnt found in the repository
     * @throws StudentNotFoundException student wasnt found in the repository
     * @return student instance
     */
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


    /**
     * deletes student with id id from repository
     * @param id student id
     * @throws StudentNotFoundException student wasnt found in the repository
     */
    public void deleteById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        }
        else {
            throw new StudentNotFoundException("Student with id " + id + " does not exist.");
        }
    }
}
