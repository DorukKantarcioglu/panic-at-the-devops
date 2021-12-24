package com.panicatthedevops.campuscarebackend.repository;

import com.panicatthedevops.campuscarebackend.entity.SeatingObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatingObjectRepository extends JpaRepository<SeatingObject, Long> {
    List<SeatingObject> findAllBySeatingPlanId(Long id);
    Boolean existsBySeatingPlanId(Long id);
    SeatingObject findByRowNoAndColumnNoAndSeatingPlanId(int rowNo, int columnNo, Long seatingPlanId);
    Boolean existsByRowNoAndColumnNoAndSeatingPlanId(int rowNo, int columnNo, Long seatingPlanId);
    Boolean existsByStudentIdAndSeatingPlanId(Long studentId, Long seatingPlanId);
}
