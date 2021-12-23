package com.panicatthedevops.campuscarebackend.repository;

import com.panicatthedevops.campuscarebackend.entity.SeatingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatingPlanRepository extends JpaRepository<SeatingPlan, Long> {
}
