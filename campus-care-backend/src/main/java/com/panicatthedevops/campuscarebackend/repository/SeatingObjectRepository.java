package com.panicatthedevops.campuscarebackend.repository;

import com.panicatthedevops.campuscarebackend.entity.SeatingObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatingObjectRepository extends JpaRepository<SeatingObject, Long> {
    List<SeatingObject> findAllBySeatingPlanId(Long id);
    Boolean existsBySeatingPlanId(Long id);
}
