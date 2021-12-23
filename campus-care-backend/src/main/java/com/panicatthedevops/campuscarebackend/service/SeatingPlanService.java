package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.SeatingPlan;
import com.panicatthedevops.campuscarebackend.exception.SeatingPlanNotFoundException;
import com.panicatthedevops.campuscarebackend.repository.CourseRepository;
import com.panicatthedevops.campuscarebackend.repository.SeatingPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatingPlanService {
    SeatingPlanRepository seatingPlanRepository;
    CourseRepository courseRepository;

    @Autowired
    public SeatingPlanService(SeatingPlanRepository seatingPlanRepository, CourseRepository courseRepository) {
        this.seatingPlanRepository = seatingPlanRepository;
        this.courseRepository = courseRepository;
    }

    public SeatingPlan save(int rowNo, int columnNo){
        return seatingPlanRepository.save(new SeatingPlan(0, rowNo, columnNo, null, null));
    }

    public SeatingPlan getSeatingPlan(Long id){
        if(!seatingPlanRepository.existsById(id)){
            throw new SeatingPlanNotFoundException("Seating plan with id " + id + " does not exist.");
        }
        else{
            return seatingPlanRepository.findById(id).get();
        }
    }

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
