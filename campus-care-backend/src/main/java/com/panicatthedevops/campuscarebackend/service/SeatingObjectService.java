package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.SeatingObject;
import com.panicatthedevops.campuscarebackend.entity.SeatingPlan;
import com.panicatthedevops.campuscarebackend.entity.Student;
import com.panicatthedevops.campuscarebackend.exception.*;
import com.panicatthedevops.campuscarebackend.repository.SeatingObjectRepository;
import com.panicatthedevops.campuscarebackend.repository.SeatingPlanRepository;
import com.panicatthedevops.campuscarebackend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * methods related to seating objects
 * @version 1.0
 */
@Service
public class SeatingObjectService {
    SeatingObjectRepository seatingObjectRepository;
    StudentRepository studentRepository;
    SeatingPlanRepository seatingPlanRepository;

    /**
     * @param seatingObjectRepository seating object repository
     * @param studentRepository student repository
     * @param seatingPlanRepository seating plan repository
     */
    @Autowired
    public SeatingObjectService(SeatingObjectRepository seatingObjectRepository, StudentRepository studentRepository, SeatingPlanRepository seatingPlanRepository) {
        this.seatingObjectRepository = seatingObjectRepository;
        this.studentRepository = studentRepository;
        this.seatingPlanRepository = seatingPlanRepository;
    }

    /**
     * removes seating object from seating plans and students seating object list
     * @param id seating object id
     * @throws SeatingObjectNotFoundException seating object with id couldnt be found
     */
    public void remove(Long id){
        if(!seatingObjectRepository.existsById(id)){
            throw new SeatingObjectNotFoundException("Seating object with id " + id + " does not exist.");
        }
        else{
            SeatingObject seatingObject = seatingObjectRepository.findById(id).get();
            Student student = seatingObject.getStudent();
            SeatingPlan seatingPlan = seatingObject.getSeatingPlan();
            seatingPlan.getSeatingSet().remove(seatingObject);
            student.getSeatings().remove(seatingObject);
            studentRepository.save(student);
            seatingPlanRepository.save(seatingPlan);
            seatingObjectRepository.delete(seatingObject);
        }
    }

    /**
     * gets seating object with id from repository
     * @param id seating object id
     * @throws SeatingObjectNotFoundException seating object with id couldnt be found in the repository
     * @return seating object instance
     */
    public SeatingObject getSeatingObject(Long id){
        if(!seatingObjectRepository.existsById(id)){
            throw new SeatingObjectNotFoundException("Seating object with id " + id + " does not exist.");
        }
        else{
            return seatingObjectRepository.findById(id).get();
        }
    }

    /**
     * returns the list of seating objects that are assigned to the seating plan with id seating plan id
     * @param seatingPlanId seating plan id
     * @throws SeatingObjectNotFoundException seating object with seating plan wşth id doesnt exist in the repository
     * @return seating object list
     */
    public List<SeatingObject> getSeatingObjectsByPlanId(Long seatingPlanId){
        if(!seatingObjectRepository.existsBySeatingPlanId(seatingPlanId)){
            throw new SeatingObjectNotFoundException("Seating objects with seating plan id " + seatingPlanId + " do not exist.");
        }
        else {
            return seatingObjectRepository.findAllBySeatingPlanId(seatingPlanId);
        }
    }

    /**
     * saves the seating object of the student id to the seating plan
     * @param rowNo row number
     * @param columnNo column number
     * @param seatingPlanId seating plan id
     * @param studentId student id
     * @throws StudentNotFoundException student with id couldnt be found in the database
     * @throws SeatingPlanNotFoundException seating plan with id couldnt be found in the database
     * @throws SeatAlreadyOccupiedException seat with row and column no in seating plan is already occupied
     * @throws StudentAlreadySeatedElsewhereInSeatingPlan student is alreadt seated elsewhere in this seating plan
     * @return created seating object
     */
    public SeatingObject save(int rowNo, int columnNo, Long seatingPlanId, Long studentId){
         if(!studentRepository.existsById(studentId)){
             throw new StudentNotFoundException("Student with id " + studentId + " does not exist.");
         }
         else if(!seatingPlanRepository.existsById(seatingPlanId)){
             throw new SeatingPlanNotFoundException("Seating plan with id " + seatingPlanId + " does not exist.");
         }
         else{
             Student student = studentRepository.findById(studentId).get();
             SeatingPlan seatingPlan = seatingPlanRepository.findById(seatingPlanId).get();
             if(seatingObjectRepository.existsByRowNoAndColumnNoAndSeatingPlanId(rowNo, columnNo, seatingPlanId)){
                throw new SeatAlreadyOccupiedException("Seat in seating plan with id " + seatingPlanId + " with row " + rowNo + " and column " + columnNo + " is already occupied");
             }
             else if(seatingObjectRepository.existsByStudentIdAndSeatingPlanId(studentId, seatingPlanId)){
                 throw new StudentAlreadySeatedElsewhereInSeatingPlan("Student with id " + studentId + " is already seated elsewhere in seating plan with id " + seatingPlanId );
             }
             else if(rowNo > seatingPlan.getRowNumber() || rowNo < 0){
                 throw new StudentSeatingPositionInvalidException("Row number " + rowNo + " is invalid for seating plan with id " + seatingPlanId + " with " + seatingPlan.getRowNumber() + " rows");
             }
             else if(columnNo > seatingPlan.getColumnNumber() || columnNo < 0){
                 throw new StudentSeatingPositionInvalidException("Column number" + columnNo + " is invalid for seating plan with id " + seatingPlanId + " with " + seatingPlan.getColumnNumber() + " columns");
             }
             else{
                 SeatingObject seatingObject = new SeatingObject(0, null, null, rowNo, columnNo);
                 seatingObject.setSeatingPlan(seatingPlan);
                 seatingObject.setStudent(student);
                 student.getSeatings().add(seatingObject);
                 seatingPlan.getSeatingSet().add(seatingObject);
                 studentRepository.save(student);
                 seatingPlanRepository.save(seatingPlan);
                 return seatingObjectRepository.save(seatingObject);
             }
         }
    }
}
