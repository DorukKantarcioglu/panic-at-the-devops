package com.panicatthedevops.campuscarebackend.util;

import com.panicatthedevops.campuscarebackend.entity.Reservation;
import com.panicatthedevops.campuscarebackend.entity.User;
import com.panicatthedevops.campuscarebackend.repository.ReservationRepository;

public class LibraryReservationBehavior implements ReservationBehavior{
    ReservationRepository reservationRepository;

    public LibraryReservationBehavior(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation reserve(String date, String timeSlot, String place, User user) {
        return reservationRepository.save(new Reservation(0, date, timeSlot, place, ReservationType.LIBRARY_RESERVATION, user));
    }
}