package com.panicatthedevops.campuscarebackend.repository;

import com.panicatthedevops.campuscarebackend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    public List<Reservation> findByType(String type);
    public List<Reservation> findAllByUserId(Long id);
    public Boolean existsByUserId(Long id);
}
