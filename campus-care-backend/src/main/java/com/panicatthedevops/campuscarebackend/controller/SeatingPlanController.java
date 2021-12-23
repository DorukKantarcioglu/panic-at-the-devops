package com.panicatthedevops.campuscarebackend.controller;

import com.panicatthedevops.campuscarebackend.entity.SeatingObject;
import com.panicatthedevops.campuscarebackend.entity.SeatingPlan;
import com.panicatthedevops.campuscarebackend.service.SeatingObjectService;
import com.panicatthedevops.campuscarebackend.service.SeatingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/seatingPlans")
public class SeatingPlanController {
    private final SeatingPlanService seatingPlanService;
    private final SeatingObjectService seatingObjectService;

    @Autowired
    public SeatingPlanController(SeatingPlanService seatingPlanService, SeatingObjectService seatingObjectService) {
        this.seatingPlanService = seatingPlanService;
        this.seatingObjectService = seatingObjectService;
    }

    @PostMapping
    public ResponseEntity<SeatingPlan> save(@RequestHeader int rowNo, @RequestHeader int columnNo){
        return ResponseEntity.ok(seatingPlanService.save(rowNo, columnNo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatingPlan> getSeatingPlan(@PathVariable Long id){
        return ResponseEntity.ok(seatingPlanService.getSeatingPlan(id));
    }

    @GetMapping
    public ResponseEntity<List<SeatingPlan>> getSeatingPlans(){
        return ResponseEntity.ok(seatingPlanService.getAll());
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestHeader Long id){
        seatingPlanService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{seatingPlanId}/seatings")
    public ResponseEntity<SeatingObject> addSeating(@PathVariable Long seatingPlanId, @RequestHeader Long studentId, @RequestHeader int rowNo, @RequestHeader int columnNo){
        return ResponseEntity.ok(seatingObjectService.save(rowNo, columnNo, seatingPlanId, studentId));
    }

    @GetMapping("{seatingPlanId}/seatings")
    public ResponseEntity<List<SeatingObject>> getSeatings(@PathVariable Long seatingPlanId){
        return ResponseEntity.ok(seatingObjectService.getSeatingObjectsByPlanId(seatingPlanId));
    }

    @DeleteMapping("{seatingPlanId}/seatings/{id}")
    public void removeSeating(@PathVariable Long id){
        seatingObjectService.remove(id);
    }

}
