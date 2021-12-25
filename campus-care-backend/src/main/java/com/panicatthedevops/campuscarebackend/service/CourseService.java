package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.Course;
import com.panicatthedevops.campuscarebackend.entity.SeatingObject;
import com.panicatthedevops.campuscarebackend.entity.SeatingPlan;
import com.panicatthedevops.campuscarebackend.entity.Student;
import com.panicatthedevops.campuscarebackend.exception.CourseAlreadyExistsException;
import com.panicatthedevops.campuscarebackend.exception.CourseNotFoundException;
import com.panicatthedevops.campuscarebackend.exception.SeatingObjectNotFoundException;
import com.panicatthedevops.campuscarebackend.exception.SeatingPlanNotFoundException;
import com.panicatthedevops.campuscarebackend.repository.CourseRepository;
import com.panicatthedevops.campuscarebackend.repository.SeatingObjectRepository;
import com.panicatthedevops.campuscarebackend.repository.SeatingPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Course entity object related functionalities
 * @version 1.0
 */
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final SeatingPlanRepository seatingPlanRepository;
    private final SeatingObjectRepository seatingObjectRepository;

    /**
     * Creates a course service instance
     * @param courseRepository course repository
     * @param seatingPlanRepository seating plan repository
     * @param seatingObjectRepository seating object repository
     */
    @Autowired
    public CourseService(CourseRepository courseRepository, SeatingPlanRepository seatingPlanRepository, SeatingObjectRepository seatingObjectRepository) {
        this.courseRepository = courseRepository;
        this.seatingPlanRepository = seatingPlanRepository;
        this.seatingObjectRepository = seatingObjectRepository;
    }


    /**
     * finds all courses in repository
     * @return list of all courses
     */
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    /**
     * finds and returns the course that has the course code
     * @param courseCode course code
     * @throws CourseNotFoundException course wasnt found on repository
     * @return course instance
     */
    public Course findByCourseCode(String courseCode) {
        if (courseRepository.findByCourseCode(courseCode).isEmpty()) {
            throw new CourseNotFoundException("Course with code " + courseCode + " does not exist.");
        }
        else {
            return courseRepository.findByCourseCode(courseCode).iterator().next();
        }
    }

    /**
     * finds and returns the course that has the course name
     * @param courseName course name
     * @throws CourseNotFoundException course wasnt found on repository
     * @return course instance
     */
    public Course findByCourseName(String courseName) {
        if (courseRepository.findByCourseName(courseName).isEmpty()) {
            throw new CourseNotFoundException("Course with name " + courseName + " does not exist.");
        }
        else {
            return courseRepository.findByCourseName(courseName).iterator().next();
        }
    }

    /**
     * returns all seating objects of a course
     * @param courseCode course code
     * @throws SeatingPlanNotFoundException seating plan of course couldnt be found
     * @throws SeatingObjectNotFoundException no seating object of seating plan could be found
     * @return list of seating objects
     */
    public List<SeatingObject> getSeatingObjects(String courseCode){
        Course course = findByCourseCode(courseCode);

        if(course.getSeatingPlan() == null)
            throw new SeatingPlanNotFoundException("Seating plan of course " + courseCode + " was not initialized" );

        Long seatingPlanId = course.getSeatingPlan().getId();

        if(!seatingObjectRepository.existsBySeatingPlanId(seatingPlanId)){
            throw new SeatingObjectNotFoundException("Seating objects with seating plan id " + seatingPlanId + " do not exist.");
        }
        else {
            return seatingObjectRepository.findAllBySeatingPlanId(seatingPlanId);
        }
    }

    /**
     * saves a course object to the repository
     * @param course course
     * @throws CourseAlreadyExistsException a course with the same course coude already exists in the repository
     * @return course instance
     */
    public Course save(Course course) {
        if (courseRepository.existsByCourseCode(course.getCourseCode())) {
            throw new CourseAlreadyExistsException("Course with code " + course.getCourseCode() + " already exists.");
        }
        else {
            return courseRepository.save(course);
        }
    }


    /**
     * deletes course from repository
     * @param courseCode course code
     * @throws CourseNotFoundException course with course code couldnt be found in the repository
     */
    public void deleteByCourseCode(String courseCode) {
        if (courseRepository.existsByCourseCode(courseCode)) {
            courseRepository.deleteByCourseCode(courseCode);
        }
        else {
            throw new CourseNotFoundException("Course with code " + courseCode + " does not exist.");
        }
    }

    /**
     * get list of students enrolled in a course
     * @param courseCode course code
     * @throws CourseNotFoundException course couldnt be found in the repository
     * @return list of students
     */
    public List<Student> getStudentList(String courseCode){
        if (!courseRepository.existsByCourseCode(courseCode)) {
            throw new CourseNotFoundException("Course with code " + courseCode + " does not exist.");
        }
        else {
            Course course = courseRepository.findByCourseCode(courseCode).iterator().next();
            return course.getStudentList();
        }
    }


    /**
     * assigns seating plan to a course
     * @param seatingPlanId seating plan id
     * @param courseCode course code
     * @return course instance
     */
    public Course addSeatingPlan(Long seatingPlanId, String courseCode){
        if(!courseRepository.existsByCourseCode(courseCode)){
            throw new CourseNotFoundException("Course with code " + courseCode + " does not exist.");
        }
        else{
            Course course = courseRepository.findByCourseCode(courseCode).iterator().next();
            if(!seatingPlanRepository.existsById(seatingPlanId)){
                throw new SeatingPlanNotFoundException("Seating plan with id " + seatingPlanId + " does not exist.");
            }
            else{
                SeatingPlan seatingPlan = seatingPlanRepository.findById(seatingPlanId).get();
                course.setSeatingPlan(seatingPlan);
                seatingPlan.setCourse(course);
                seatingPlanRepository.save(seatingPlan);
                courseRepository.save(course);
                return course;
            }
        }
    }
}
