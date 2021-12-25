package com.panicatthedevops.campuscarebackend.repository;

import com.panicatthedevops.campuscarebackend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByType(String type);
    List<Reservation> findAllByUserId(Long id);
    boolean existsByUserId(Long id);
    boolean existsByPlaceAndTimeSlotAndDateAndType(String place, String timeSlot, String date, String type);
    List<Reservation> findAllByPlaceAndDateAndType(String place, String date, String type);
}
