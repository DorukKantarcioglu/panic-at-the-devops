package com.panicatthedevops.campuscarebackend.controller;

import com.panicatthedevops.campuscarebackend.entity.Reservation;
import com.panicatthedevops.campuscarebackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations(){
        return ResponseEntity.ok(reservationService.getReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long id){
        return ResponseEntity.ok(reservationService.getReservation(id));
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestHeader String date, @RequestHeader String timeSlot, @RequestHeader String type, @RequestHeader Long userId, @RequestHeader String place){
        return ResponseEntity.ok(reservationService.save(userId, date, timeSlot,place, type));
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id){
        reservationService.deleteReservation(id);
    }

    @GetMapping("/timeSlots")
    public ResponseEntity<List<String>> getAvailableTimes(@RequestHeader String type, @RequestHeader String date, @RequestHeader String place){
        return ResponseEntity.ok(reservationService.getAvailableTimes(date, place, type));
    }

    @GetMapping("/places")
    public ResponseEntity<List<String>> getPlaces(@RequestHeader String type){
        return ResponseEntity.ok(reservationService.getPlaces(type));
    }
}
