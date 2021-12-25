package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.SeatingPlan;
import com.panicatthedevops.campuscarebackend.exception.SeatingPlanNotFoundException;
import com.panicatthedevops.campuscarebackend.repository.CourseRepository;
import com.panicatthedevops.campuscarebackend.repository.SeatingPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Methods related to seating plan entities
 * @version 1.0
 */
@Service
public class SeatingPlanService {
    SeatingPlanRepository seatingPlanRepository;
    CourseRepository courseRepository;

    /**
     * creates instance
     * @param seatingPlanRepository seating plan repository
     * @param courseRepository course repository
     */
    @Autowired
    public SeatingPlanService(SeatingPlanRepository seatingPlanRepository, CourseRepository courseRepository) {
        this.seatingPlanRepository = seatingPlanRepository;
        this.courseRepository = courseRepository;
    }

    /**
     * creates seating plan with given dimensions
     * @param rowNo row number of seating plan
     * @param columnNo column number of seaing plan
     * @return
     */
    public SeatingPlan save(int rowNo, int columnNo){
        return seatingPlanRepository.save(new SeatingPlan(0, rowNo, columnNo, null, null));
    }

    /**
     * finds seating plan with id form repository
     * @param id seating plan id
     * @throws SeatingPlanNotFoundException seating plan with id couldnt be found in the repository
     * @return found seating plan
     */
    public SeatingPlan getSeatingPlan(Long id){
        if(!seatingPlanRepository.existsById(id)){
            throw new SeatingPlanNotFoundException("Seating plan with id " + id + " does not exist.");
        }
        else{
            return seatingPlanRepository.findById(id).get();
        }
    }

    /**
     * gets all of the seating plans in the repository
     * @throws SeatingPlanNotFoundException seating plan with id couldnt be found in the repository
     * @return list of seating plans
     */
    public List<SeatingPlan> getAll(){
        return seatingPlanRepository.findAll();
    }

    public void deleteById(Long id){
        if(seatingPlanRepository.existsById(id)){
            deleteById(id);
        }
        else{
            throw new SeatingPlanNotFoundException("Seating plan with id " + id + " does not exist.");
        }
    }


}
